package ee.levin.filters;

import ee.levin.filters.dao.CriteriaDao;
import ee.levin.filters.dao.FilterDao;
import ee.levin.filters.dto.CriteriaDto;
import ee.levin.filters.dto.FilterDto;
import ee.levin.filters.service.FilterService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilterServiceTest {

    @Mock
    private FilterDao filterDao;
    @Mock
    private CriteriaDao criteriaDao;
    @InjectMocks
    private FilterService filterService;
    private static List<FilterDto> filters;
    private static List<CriteriaDto> criteria;
    private static List<CriteriaDto> criteria2;

    @BeforeAll
    static void setUp() {
        filters = new ArrayList<>();
        filters.add(createFilter(1L, "Test Filter"));
        filters.add(createFilter(2L, "Test Filter2"));

        criteria = new ArrayList<>();
        criteria.add(createCriteria(1L, 1L, "Amount", "Greater than", new BigDecimal("100.00"), null, null));
        criteria.add(createCriteria(2L, 1L, "Title", "Starts with", null, "Test", null));

        criteria2 = new ArrayList<>();
        criteria2.add(createCriteria(3L, 2L, "Date", "From", null, null, LocalDate.of(2024, 1, 1)));
    }

    @Test
    void findAllFilters() {
        when(filterDao.findAllFilters()).thenReturn(filters);
        when(criteriaDao.findCriteriaByFilterId(1L)).thenReturn(criteria);
        when(criteriaDao.findCriteriaByFilterId(2L)).thenReturn(criteria2);

        List<FilterDto> result = filterService.findAllFilters();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getCriteria()).hasSize(2);
        assertThat(result.get(0).getCriteria().get(0).getValueAmount()).isEqualByComparingTo("100.00");
        assertThat(result.get(1).getCriteria()).hasSize(1);
        assertThat(result.get(1).getCriteria().get(0).getValueDate()).isIn("2024-01-01");
    }

    @Test
    void insertFilter() {
        FilterDto newFilter = createFilter(3L, "Ventilatsioonifilter");
        CriteriaDto newCriteria = createCriteria(4L, 3L, "Amount", "Less than", new BigDecimal("50.00"), null, null);
        newFilter.setCriteria(singletonList(newCriteria));

        filterService.insertFilter(newFilter);

        verify(filterDao, times(1)).insertFilter(newFilter);
        verify(criteriaDao, times(1)).insertCriteria(newCriteria);
    }

    @Test
    void testDeleteFilter() {
        filterService.deleteFilterById(1L);

        verify(filterDao, times(1)).deleteFilterById(1L);
    }

    private static FilterDto createFilter(Long id, String name) {
        FilterDto filter = new FilterDto();
        filter.setId(id);
        filter.setName(name);
        return filter;
    }

    private static CriteriaDto createCriteria(
        Long id,
        Long filterId,
        String type,
        String condition,
        BigDecimal valueAmount,
        String valueTitle,
        LocalDate valueDate
    ) {
        CriteriaDto criteria = new CriteriaDto();
        criteria.setId(id);
        criteria.setFilterId(filterId);
        criteria.setType(type);
        criteria.setCondition(condition);
        criteria.setValueAmount(valueAmount);
        criteria.setValueTitle(valueTitle);
        criteria.setValueDate(valueDate);
        return criteria;
    }

}
