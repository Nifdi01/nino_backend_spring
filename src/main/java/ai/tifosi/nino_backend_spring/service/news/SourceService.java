package ai.tifosi.nino_backend_spring.service.news;

import ai.tifosi.nino_backend_spring.dto.news.platformDto.GetPlatformDto;
import ai.tifosi.nino_backend_spring.dto.news.sourceDto.GetSourceDto;
import ai.tifosi.nino_backend_spring.dto.news.sourceDto.PostSourceDto;
import ai.tifosi.nino_backend_spring.model.news.Platform;
import ai.tifosi.nino_backend_spring.model.news.Source;
    import ai.tifosi.nino_backend_spring.repository.news.PlatformRepository;
import ai.tifosi.nino_backend_spring.repository.news.SourceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SourceService {
    private final SourceRepository sourceRepository;
    private final PlatformRepository platformRepository;

    public SourceService(SourceRepository sourceRepository, PlatformRepository platformRepository) {
        this.sourceRepository = sourceRepository;
        this.platformRepository = platformRepository;
    }

    public Page<GetSourceDto> getAllSources(String search, Pageable pageable) {
        Page<Source> sources = sourceRepository.findByNameContaining(search, pageable);

        return sources.map(this::convertToDto);
    }

    private GetSourceDto convertToDto(Source source) {
        return new GetSourceDto(
                source.getId(),
                source.getLink(),
                new GetPlatformDto(source.getPlatform().getId(), source.getPlatform().getName(), source.getPlatform().getFrequency()),
                source.getName(),
                source.getFrequency()
        );
    }

    public void deleteSource(Long id) {
        System.out.println(id);
        Source source = sourceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Source not found"));
        sourceRepository.delete(source);
    }

    public Source createSource(PostSourceDto request) {
        Optional<Source> existingSource = sourceRepository.findByLink(request.link());

        if(existingSource.isPresent()){
            throw new IllegalArgumentException("Source already exists: " + request.name());
        }

        Source source = new Source();
        source.setLink(request.link());
        source.setName(request.name());

        // Fetch the existing Platform using its id from the request
        Platform platform = platformRepository.findById(request.platform())
                .orElseThrow(() -> new EntityNotFoundException("Platform not found"));
        source.setPlatform(platform);

        sourceRepository.save(source);

        return source;
    }
}
