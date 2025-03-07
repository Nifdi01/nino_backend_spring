package ai.tifosi.nino_backend_spring.dto.news.keywordDto;

import ai.tifosi.nino_backend_spring.model.news.Keyword;

import java.io.Serializable;


public record KeywordDto(String name) implements Serializable {
  public KeywordDto(Keyword keyword) {
    this(
            keyword != null ? keyword.getName() : null
    );
  }
}