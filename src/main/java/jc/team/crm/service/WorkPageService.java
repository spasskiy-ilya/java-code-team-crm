package jc.team.crm.service;

import jc.team.crm.dto.WorkAgentDto;
import jc.team.crm.mapper.WorkAgentMapper;
import jc.team.crm.repository.AgentRepository;
import jc.team.crm.utils.StageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkPageService {

    private final AgentRepository agentRepository;
    private final WorkAgentMapper workAgentMapper;

    public List<WorkAgentDto> getWorkAgents() {
        return agentRepository.findWorkAgents().stream()
                .map(workAgentMapper::toWorkAgentDto)
                .sorted(Comparator.comparing((WorkAgentDto dto) -> StageUtils.getStageOrder(dto.getCurrentStage())))
                .collect(Collectors.toList());
    }
}
