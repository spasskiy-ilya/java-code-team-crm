package jc.team.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateContractDto {
    private Long agentId;
    private Boolean signed;
    private LocalDate signDate;
    private LocalDate reSignDate;
    private LocalDate brokeDate;
}
