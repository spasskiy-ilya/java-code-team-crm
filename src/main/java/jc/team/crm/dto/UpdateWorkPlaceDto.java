package jc.team.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWorkPlaceDto {
    private Long agentId;
    private Long workPlaceId;
    private String companyName;
    private BigDecimal salary;
    private String deviceType;
    private LocalDate startDate;
    private LocalDate endDate;
}
