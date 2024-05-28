package ee.levin.filters.api;

import ee.levin.filters.dto.FilterDto;
import ee.levin.filters.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/filters")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class FiltersApi {

    private final FilterService filterService;

    @GetMapping("findAllFilters")
    public List<FilterDto> findAllFilters() {
        return filterService.findAllFilters();
    }

    @PostMapping("insertFilter")
    public void insertFilter(@RequestBody FilterDto filter) {
        filterService.insertFilter(filter);
    }

    @DeleteMapping("/{id}")
    public void deleteFilter(@PathVariable Long id) {
        filterService.deleteFilterById(id);
    }

}
