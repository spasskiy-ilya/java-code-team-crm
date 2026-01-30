package jc.team.crm.service;

import jc.team.crm.dto.AgentStatusUpdateDto;
import jc.team.crm.entity.AgentEntity;
import jc.team.crm.entity.history.AgentHistoryEntity;
import jc.team.crm.entity.status.CurrentStatusEntity;
import jc.team.crm.repository.AgentRepository;
import jc.team.crm.utils.StageHistoryUtils;
import jc.team.crm.utils.StageModuleUtils;
import jc.team.crm.utils.StageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AgentStatusService {

    private final AgentRepository agentRepository;

    @Transactional
    public void updateStatus(AgentStatusUpdateDto dto) {
        AgentEntity agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new IllegalArgumentException("Агент не найден"));

        CurrentStatusEntity status = agent.getCurrentStatus();
        AgentHistoryEntity agentHistory = agent.getAgentHistory();

        String currentStage = status.getCurrentStage();
        String currentSubStage = status.getCurrentSubStage();

        if (dto.getStage() != null && !dto.getStage().equals(currentStage)) {
            processStageChange(agentHistory, status, dto.getStage(), dto.getSubStage());
        }

        String dtoSubStage = dto.getSubStage();
        if (dtoSubStage != null && !dtoSubStage.trim().isEmpty() && !dtoSubStage.equals(currentSubStage)) {
            boolean stageChanged = dto.getStage() != null && !dto.getStage().equals(currentStage);
            if (!stageChanged) {
                processSubStageChange(agentHistory, status, dtoSubStage);
            }
        }

        agentRepository.save(agent);
    }

    private void processStageChange(AgentHistoryEntity agentHistory, CurrentStatusEntity status,
                                   String newStage, String newSubStage) {
        String previousStage = status.getCurrentStage();

        if (previousStage != null) {
            StageHistoryUtils.closePreviousStageHistory(agentHistory, previousStage);
        }

        String subStage = (newSubStage != null && !newSubStage.trim().isEmpty()) 
                ? newSubStage 
                : StageUtils.getFirstSubStage(newStage);

        StageHistoryUtils.createNewStageHistory(agentHistory, newStage, subStage);

        updateCurrentStatusForStage(status, newStage, subStage);
    }

    private void processSubStageChange(AgentHistoryEntity agentHistory, CurrentStatusEntity status,
                                      String newSubStage) {
        String currentStage = status.getCurrentStage();
        String previousSubStage = status.getCurrentSubStage();

        if (previousSubStage != null) {
            StageModuleUtils.closePreviousSubStageModule(agentHistory, currentStage, previousSubStage);
        }

        StageHistoryUtils.createNewSubStageModule(agentHistory, currentStage, newSubStage);

        updateCurrentStatusForSubStage(status, newSubStage);
    }

    private void updateCurrentStatusForStage(CurrentStatusEntity status, String stage, String subStage) {
        LocalDate now = LocalDate.now();
        status.setCurrentStage(stage);
        status.setCurrentStageStart(now);
        status.setCurrentSubStage(subStage);
        status.setCurrentSubStageStart(now);
    }

    private void updateCurrentStatusForSubStage(CurrentStatusEntity status, String subStage) {
        LocalDate now = LocalDate.now();
        status.setCurrentSubStage(subStage);
        status.setCurrentSubStageStart(now);
    }
}
