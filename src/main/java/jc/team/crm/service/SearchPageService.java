package jc.team.crm.service;

import jc.team.crm.dto.SearchAgentDto;
import jc.team.crm.mapper.SearchAgentMapper;
import jc.team.crm.repository.AgentRepository;
import jc.team.crm.utils.StageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchPageService {

    private final AgentRepository agentRepository;
    private final SearchAgentMapper searchAgentMapper;

    public List<SearchAgentDto> getSearchAgents() {
        return agentRepository.findSearchAgents().stream()
                .map(searchAgentMapper::toSearchAgentDto)
                .sorted(Comparator.comparing((SearchAgentDto dto) -> StageUtils.getStageOrder(dto.getCurrentStage())))
                .collect(Collectors.toList());
    }
}
