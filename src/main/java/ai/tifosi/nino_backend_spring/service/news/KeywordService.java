package ai.tifosi.nino_backend_spring.service.news;

import ai.tifosi.nino_backend_spring.dto.news.keywordDto.GetKeywordDto;
import ai.tifosi.nino_backend_spring.dto.news.keywordDto.PostKeywordDto;
import ai.tifosi.nino_backend_spring.model.news.Keyword;
import ai.tifosi.nino_backend_spring.repository.news.KeywordRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KeywordService {
    private final KeywordRepository keywordRepository;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public Page<GetKeywordDto> getAllKeywords(String search, Pageable pageable) {
        // Apply the search query and pagination parameters
        Page<Keyword> keywords = keywordRepository.findByNameContaining(search, pageable);

        // Convert the Page of Keyword entities to a Page of KeywordDto
        return keywords.map(this::convertToDto);
    }

    private GetKeywordDto convertToDto(Keyword keyword) {
        return new GetKeywordDto(
                keyword.getId(),
                keyword.getName(),
                keyword.getFrequency());
    }

    public void deleteKeyword(Long id) {
        Keyword keyword = keywordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Keyword not found"));

        keywordRepository.delete(keyword);
    }

    public void createKeyword(PostKeywordDto request) {
        Optional<Keyword> existingKeyword = keywordRepository.findByName(request.name());

        if (existingKeyword.isPresent()) {
            throw new IllegalArgumentException("Keyword already exists: " + request.name());
        }

        Keyword keyword = new Keyword(request.name());
        keywordRepository.save(keyword);
    }
}
