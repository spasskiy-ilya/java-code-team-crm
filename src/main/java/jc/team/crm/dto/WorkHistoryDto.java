package jc.team.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkHistoryDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<WorkPlaceDto> workPlaces;
}
