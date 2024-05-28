package ee.levin.filters.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class FilterDto {

    private Long id;
    private String name;
    private List<CriteriaDto> criteria;
    private OffsetDateTime createdDtime;

}
