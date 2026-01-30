package jc.team.crm.service;

import jc.team.crm.dto.ArchiveAgentDto;
import jc.team.crm.mapper.ArchiveAgentMapper;
import jc.team.crm.repository.AgentRepository;
import jc.team.crm.utils.StageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArchivePageService {

    private final AgentRepository agentRepository;
    private final ArchiveAgentMapper archiveAgentMapper;

    public List<ArchiveAgentDto> getArchiveAgents() {
        return agentRepository.findArchiveAgents().stream()
                .map(archiveAgentMapper::toArchiveAgentDto)
                .sorted(Comparator.comparing((ArchiveAgentDto dto) -> StageUtils.getStageOrder(dto.getCurrentStage())))
                .collect(Collectors.toList());
    }
}
