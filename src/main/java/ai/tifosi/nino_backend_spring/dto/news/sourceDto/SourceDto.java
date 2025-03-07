package ai.tifosi.nino_backend_spring.dto.news.sourceDto;

import ai.tifosi.nino_backend_spring.model.news.Source;

import java.io.Serializable;

public record SourceDto(String name) implements Serializable {
    public SourceDto(Source source) {
        this(
                source != null ? source.getName() : null
        );
    }
}
