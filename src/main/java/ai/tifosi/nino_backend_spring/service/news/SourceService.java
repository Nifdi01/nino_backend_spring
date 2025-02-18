package ai.tifosi.nino_backend_spring.service.news;

import ai.tifosi.nino_backend_spring.dto.news.platformDto.PlatformDto;
import ai.tifosi.nino_backend_spring.dto.news.sourceDto.SourceDto;
import ai.tifosi.nino_backend_spring.model.news.Source;
import ai.tifosi.nino_backend_spring.repository.news.SourceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SourceService {
    private final SourceRepository sourceRepository;

    public SourceService(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    public Page<SourceDto> getAllSources(String search, Pageable pageable) {
        Page<Source> sources = sourceRepository.findByNameContaining(search, pageable);

        return sources.map(this::convertToDto);
    }

    private SourceDto convertToDto(Source source) {
        return new SourceDto(
                source.getId(),
                source.getLink(),
                new PlatformDto(source.getPlatform().getId(), source.getPlatform().getName(), source.getPlatform().getFrequency()),
                source.getName(),
                source.getActivity()
        );
    }
}
