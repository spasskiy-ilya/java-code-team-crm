package jc.team.crm.mapper;

import jc.team.crm.dto.WorkAgentDto;
import jc.team.crm.entity.AgentEntity;
import jc.team.crm.utils.AgentUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", imports = AgentUtils.class)
public interface WorkAgentMapper {

    @Mapping(target = "agentId", source = "id")
    @Mapping(target = "fullName", expression = "java(AgentUtils.buildFullName(agent))")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "telegram", source = "info.telegramNikName")
    @Mapping(target = "currentStage", source = "currentStatus.currentStage")
    @Mapping(target = "stageDuration", expression = "java(agent.getCurrentStatus() != null && agent.getCurrentStatus().getCurrentStageStart() != null ? AgentUtils.calculateDuration(agent.getCurrentStatus().getCurrentStageStart()) : 0L)")
    @Mapping(target = "workStartDate", source = "agentHistory.workHistory.startDate")
    @Mapping(target = "companyName", ignore = true)
    @Mapping(target = "deviceType", ignore = true)
    WorkAgentDto toWorkAgentDto(AgentEntity agent);

    @AfterMapping
    default void mapWorkPlaceInfo(AgentEntity agent, @MappingTarget WorkAgentDto dto) {
        if (agent.getAgentHistory() != null 
                && agent.getAgentHistory().getWorkHistory() != null
                && agent.getAgentHistory().getWorkHistory().getWorkPlaces() != null
                && !agent.getAgentHistory().getWorkHistory().getWorkPlaces().isEmpty()) {
            var firstWorkPlace = agent.getAgentHistory().getWorkHistory().getWorkPlaces().get(0);
            dto.setCompanyName(firstWorkPlace.getCompanyName());
            dto.setDeviceType(firstWorkPlace.getDeviceType());
        }
    }
}
