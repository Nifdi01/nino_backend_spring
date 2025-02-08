package ai.tifosi.nino_backend_spring.dto.news;

import java.io.Serializable;

/**
 * DTO for {@link ai.tifosi.nino_backend_spring.model.news.Platform}
 */
public class PlatformDto implements Serializable {
    private final Long id;
    private final String name;
    private final int frequency;

    public PlatformDto(Long id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }
}