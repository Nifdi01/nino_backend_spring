package ai.tifosi.nino_backend_spring.controller.news;

import ai.tifosi.nino_backend_spring.dto.news.sourceDto.GetSourceDto;
import ai.tifosi.nino_backend_spring.dto.news.sourceDto.PostSourceDto;
import ai.tifosi.nino_backend_spring.model.news.Source;
import ai.tifosi.nino_backend_spring.service.news.SourceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sources/")
public class SourceController {
    private final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @GetMapping
    public ResponseEntity<Page<GetSourceDto>> getAllSources(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "20") int pageSize,
            @RequestParam(name = "search", defaultValue = "") String search) {

        Pageable pageable = PageRequest.of(page - 1, pageSize);

        Page<GetSourceDto> sourcePage = sourceService.getAllSources(search, pageable);

        return ResponseEntity.ok(sourcePage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> createSource(@PathVariable Long id){
        sourceService.deleteSource(id);
        return ResponseEntity.status(HttpStatus.OK).body("Source deleted successfully");
    }

    @PostMapping
    public ResponseEntity<Source> createSource(@RequestBody PostSourceDto request){
        try {
            Source source = sourceService.createSource(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(source);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
}
