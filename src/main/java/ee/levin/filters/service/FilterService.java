package ee.levin.filters.service;

import ee.levin.filters.dao.CriteriaDao;
import ee.levin.filters.dao.FilterDao;
import ee.levin.filters.dto.CriteriaDto;
import ee.levin.filters.dto.FilterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class FilterService {

    private final FilterDao filterDao;
    private final CriteriaDao criteriaDao;

    public List<FilterDto> findAllFilters() {
        log.info("Querying for all filters");

        List<FilterDto> filters = filterDao.findAllFilters();
        for (FilterDto filter : filters) {
            List<CriteriaDto> criteria = criteriaDao.findCriteriaByFilterId(filter.getId());
            filter.setCriteria(criteria);
        }
        return filters;
    }

    public void insertFilter(FilterDto filterDto) {
        log.info("Inserting new filter: {}", filterDto);

        filterDao.insertFilter(filterDto);
        Long filterId = filterDto.getId();

        if (filterDto.getCriteria() != null) {
            List<CriteriaDto> criteriaList = filterDto.getCriteria();
            criteriaList.forEach(criteria -> {
                criteria.setFilterId(filterId);
                criteriaDao.insertCriteria(criteria);
            });
        }
    }

    public void deleteFilterById(Long filterId) {
        log.info("Deleting filter: {}", filterId);
        filterDao.deleteFilterById(filterId);
    }

}
