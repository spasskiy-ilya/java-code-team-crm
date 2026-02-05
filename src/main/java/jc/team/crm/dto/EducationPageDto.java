package jc.team.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationPageDto {
    private List<AgentListDto> allAgents;
    private int totalCount;
    private List<AgentListDto> firstPartAgents;
    private int firstPartCount;
    private List<AgentListDto> signAgents;
    private int signCount;
    private List<AgentListDto> secondPartAgents;
    private int secondPartCount;
}
