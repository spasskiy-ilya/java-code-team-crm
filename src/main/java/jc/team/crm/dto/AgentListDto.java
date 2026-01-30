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
public class AgentListDto {

    private Long agentId;
    private String fullName;
    private String login;
    private String telegram;
    private String currentStage;
    private Long stageDuration;
    private String currentSubStage;
    private Long subStageDuration;
    private LocalDate registrationDate;
    private LocalDate contractSignDate;
}
