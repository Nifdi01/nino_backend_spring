package ai.tifosi.nino_backend_spring.service.news;

import ai.tifosi.nino_backend_spring.dto.news.SourceDto;
import ai.tifosi.nino_backend_spring.mapper.SourceMapper;
import ai.tifosi.nino_backend_spring.model.news.Source;
import ai.tifosi.nino_backend_spring.repository.news.SourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SourceService {
    private final SourceRepository sourceRepository;

    public SourceService(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    public List<SourceDto> getAllSources() {
        List<Source> sources = sourceRepository.findAll();
        return sources.stream().map(SourceMapper::toDto).collect(Collectors.toList());
    }
}
