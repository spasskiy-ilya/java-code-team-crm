package jc.team.crm.service;

import jc.team.crm.dto.AgentListDto;
import jc.team.crm.enums.HistoryStage;
import jc.team.crm.mapper.AgentMapper;
import jc.team.crm.repository.AgentRepository;
import jc.team.crm.utils.StageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationPageService {

    private final AgentRepository agentRepository;
    private final AgentMapper agentMapper;

    private final static List<String> EXCLUDED_STAGES = List.of(
            HistoryStage.SEARCH.name(),
            HistoryStage.WORK.name(),
            HistoryStage.DONE.name(),
            HistoryStage.ARCHIVE.name()
    );

    public List<AgentListDto> getActiveAgents() {
        return agentRepository.findActiveAgents(EXCLUDED_STAGES).stream()
                .map(agentMapper::toAgentListDto)
                .sorted(Comparator
                        .comparing((AgentListDto dto) -> StageUtils.getStageOrder(dto.getCurrentStage()))
                        .thenComparing((AgentListDto dto) -> StageUtils.getSubStageOrder(dto.getCurrentSubStage(), dto.getCurrentStage())))
                .collect(Collectors.toList());
    }
}
