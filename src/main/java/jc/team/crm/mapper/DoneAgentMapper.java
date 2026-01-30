package jc.team.crm.mapper;

import jc.team.crm.dto.DoneAgentDto;
import jc.team.crm.entity.AgentEntity;
import jc.team.crm.utils.AgentUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = AgentUtils.class)
public interface DoneAgentMapper {

    @Mapping(target = "agentId", source = "id")
    @Mapping(target = "fullName", expression = "java(AgentUtils.buildFullName(agent))")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "telegram", source = "info.telegramNikName")
    @Mapping(target = "currentStage", source = "currentStatus.currentStage")
    @Mapping(target = "stageDuration", expression = "java(agent.getCurrentStatus() != null && agent.getCurrentStatus().getCurrentStageStart() != null ? AgentUtils.calculateDuration(agent.getCurrentStatus().getCurrentStageStart()) : 0L)")
    @Mapping(target = "completedDate", source = "agentHistory.completedHistory.completedDate")
    DoneAgentDto toDoneAgentDto(AgentEntity agent);
}
