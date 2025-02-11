package ai.tifosi.nino_backend_spring.service.news;

import ai.tifosi.nino_backend_spring.dto.news.KeywordDto;
import ai.tifosi.nino_backend_spring.model.news.Keyword;
import ai.tifosi.nino_backend_spring.repository.news.KeywordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class KeywordService {
    private final KeywordRepository keywordRepository;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public Page<KeywordDto> getKeywords(String search, Pageable pageable) {
        // Apply the search query and pagination parameters
        Page<Keyword> keywords = keywordRepository.findByNameContaining(search, pageable);

        // Convert the Page of Keyword entities to a Page of KeywordDto
        return keywords.map(this::convertToDto);
    }

    private KeywordDto convertToDto(Keyword keyword) {
        return new KeywordDto(
                keyword.getId(),
                keyword.getName(),
                keyword.getFrequency());
    }
}
