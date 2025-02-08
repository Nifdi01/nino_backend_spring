package ai.tifosi.nino_backend_spring.dto.news;

import java.io.Serializable;

/**
 * DTO for {@link ai.tifosi.nino_backend_spring.model.news.Source}
 */
public class SourceDto implements Serializable {
    private final Long id;
    private final String link;
    private final PlatformDto platform;
    private final String name;
    private final int activity;

    public SourceDto(Long id, String link, PlatformDto platform, String name, int activity) {
        this.id = id;
        this.link = link;
        this.platform = platform;
        this.name = name;
        this.activity = activity;
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public PlatformDto getPlatform() {
        return platform;
    }

    public String getName() {
        return name;
    }

    public int getActivity() {
        return activity;
    }
}