package ai.tifosi.nino_backend_spring.mapper;

import ai.tifosi.nino_backend_spring.dto.news.SourceDto;
import ai.tifosi.nino_backend_spring.model.news.Source;

public class SourceMapper {
    public static SourceDto toDto(Source source) {
        return new SourceDto(
                source.getId(),
                source.getLink(),
                PlatformMapper.toDto(source.getPlatform()),
                source.getName(),
                source.getActivity()
        );
    }
}
