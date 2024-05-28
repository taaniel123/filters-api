package ee.levin.filters.dao;

import ee.levin.filters.dto.FilterDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FilterDao {

    List<FilterDto> findAllFilters();

    void insertFilter(FilterDto filterDto);

    void deleteFilterById(@Param("id") Long id);

}
