package jc.team.crm.mapper;

import jc.team.crm.dto.AgentListDto;
import jc.team.crm.entity.AgentEntity;
import jc.team.crm.utils.AgentUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = AgentUtils.class)
public interface AgentMapper {

    @Mapping(target = "agentId", source = "id")
    @Mapping(target = "fullName", expression = "java(AgentUtils.buildFullName(agent))")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "telegram", source = "info.telegramNikName")
    @Mapping(target = "currentStage", source = "currentStatus.currentStage")
    @Mapping(target = "stageDuration", expression = "java(AgentUtils.calculateDuration(agent.getCurrentStatus() != null ? agent.getCurrentStatus().getCurrentStageStart() : null))")
    @Mapping(target = "currentSubStage", source = "currentStatus.currentSubStage")
    @Mapping(target = "subStageDuration", expression = "java(AgentUtils.calculateDuration(agent.getCurrentStatus() != null ? agent.getCurrentStatus().getCurrentSubStageStart() : null))")
    @Mapping(target = "registrationDate", source = "info.registrationDate")
    @Mapping(target = "contractSignDate", expression = "java(agent.getAgentHistory() != null && agent.getAgentHistory().getContractHistory() != null ? agent.getAgentHistory().getContractHistory().getSignDate() : null)")
    AgentListDto toAgentListDto(AgentEntity agent);
}
