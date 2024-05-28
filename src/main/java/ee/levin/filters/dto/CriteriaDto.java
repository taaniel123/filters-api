package ee.levin.filters.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
public class CriteriaDto {

    private Long id;
    private Long filterId;
    private String type;
    private String condition;
    private BigDecimal valueAmount;
    private String valueTitle;
    private LocalDate valueDate;
    private OffsetDateTime createdDtime;

}
