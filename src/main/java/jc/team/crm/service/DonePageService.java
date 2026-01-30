package jc.team.crm.service;

import jc.team.crm.dto.DoneAgentDto;
import jc.team.crm.mapper.DoneAgentMapper;
import jc.team.crm.repository.AgentRepository;
import jc.team.crm.utils.StageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonePageService {

    private final AgentRepository agentRepository;
    private final DoneAgentMapper doneAgentMapper;

    public List<DoneAgentDto> getDoneAgents() {
        return agentRepository.findDoneAgents().stream()
                .map(doneAgentMapper::toDoneAgentDto)
                .sorted(Comparator.comparing((DoneAgentDto dto) -> StageUtils.getStageOrder(dto.getCurrentStage())))
                .collect(Collectors.toList());
    }
}
