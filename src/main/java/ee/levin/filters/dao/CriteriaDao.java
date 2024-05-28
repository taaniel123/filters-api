package ee.levin.filters.dao;

import ee.levin.filters.dto.CriteriaDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CriteriaDao {

    List<CriteriaDto> findCriteriaByFilterId(Long filterId);

    void insertCriteria(CriteriaDto criteria);

}
