package jc.team.crm.service;

import jc.team.crm.dto.AgentRegistrationDto;
import jc.team.crm.entity.AgentEntity;
import jc.team.crm.entity.history.AgentHistoryEntity;
import jc.team.crm.entity.history.education.first.EducationFirstPartHistoryEntity;
import jc.team.crm.entity.history.education.first.EducationFirstPartModuleEntity;
import jc.team.crm.entity.info.AgentInfoEntity;
import jc.team.crm.entity.status.CurrentStatusEntity;
import jc.team.crm.enums.HistoryStage;
import jc.team.crm.enums.SubFirstPartEducation;
import jc.team.crm.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationPageService {

    private final AgentRepository agentRepository;

    @Transactional
    public AgentEntity registerAgent(AgentRegistrationDto dto) {
        AgentEntity agent = new AgentEntity();
        agent.setLogin(dto.getPortalLogin());

        LocalDate now = LocalDate.now();

        AgentInfoEntity info = AgentInfoEntity
                .builder()
                .secondName(dto.getLastName())
                .name(dto.getFirstName())
                .telegramNikName(dto.getTelegramLogin())
                .portalLogin(dto.getPortalLogin())
                .portalPassword(dto.getPortalPassword())
                .registrationDate(now)
                .agent(agent)
                .build();
        agent.setInfo(info);

        AgentHistoryEntity agentHistory = AgentHistoryEntity.builder()
                .agent(agent)
                .build();
        agent.setAgentHistory(agentHistory);

        EducationFirstPartHistoryEntity firstPartHistory = EducationFirstPartHistoryEntity.builder()
                .startDate(now)
                .agentHistory(agentHistory)
                .build();
        agentHistory.setEducationFirstPartHistory(firstPartHistory);

        EducationFirstPartModuleEntity firstPartModule = EducationFirstPartModuleEntity
                .builder()
                .stageName(SubFirstPartEducation.JAVA_CORE.name())
                .startDate(now)
                .educationHistory(firstPartHistory)
                .build();
        firstPartHistory.setModules(List.of(firstPartModule));

        CurrentStatusEntity currentStatus = CurrentStatusEntity
                .builder()
                .currentStage(HistoryStage.FIRST_PART_EDUCATION.name())
                .currentSubStage(SubFirstPartEducation.JAVA_CORE.name())
                .currentStageStart(now)
                .currentSubStageStart(now)
                .agent(agent)
                .build();
        agent.setCurrentStatus(currentStatus);

        return agentRepository.save(agent);
    }
}
