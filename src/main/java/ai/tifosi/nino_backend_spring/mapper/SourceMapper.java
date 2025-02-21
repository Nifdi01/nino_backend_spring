package ai.tifosi.nino_backend_spring.mapper;

import ai.tifosi.nino_backend_spring.dto.news.sourceDto.GetSourceDto;
import ai.tifosi.nino_backend_spring.model.news.Source;

public class SourceMapper {
    public static GetSourceDto toDto(Source source) {
        return new GetSourceDto(
                source.getId(),
                source.getLink(),
                PlatformMapper.toDto(source.getPlatform()),
                source.getName(),
                source.getActivity()
        );
    }
}
