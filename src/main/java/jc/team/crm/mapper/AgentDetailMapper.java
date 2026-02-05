package jc.team.crm.mapper;

import jc.team.crm.dto.AgentDetailDto;
import jc.team.crm.dto.ArchiveDto;
import jc.team.crm.dto.ContractDto;
import jc.team.crm.dto.HistoryStageDto;
import jc.team.crm.dto.ModuleDto;
import jc.team.crm.dto.NoteDto;
import jc.team.crm.dto.WorkHistoryDto;
import jc.team.crm.dto.WorkPlaceDto;
import jc.team.crm.entity.AgentEntity;
import jc.team.crm.entity.history.AgentHistoryEntity;
import jc.team.crm.entity.history.education.cv.CvModuleEntity;
import jc.team.crm.entity.history.education.first.EducationFirstPartModuleEntity;
import jc.team.crm.entity.history.education.practice.PracticeModuleEntity;
import jc.team.crm.entity.history.education.second.EducationSecondPartModuleEntity;
import jc.team.crm.entity.history.work.WorkPlaceEntity;
import jc.team.crm.utils.AgentUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = AgentUtils.class)
public interface AgentDetailMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "fullName", expression = "java(AgentUtils.buildFullName(agent))")
    @Mapping(target = "firstName", source = "info.name")
    @Mapping(target = "lastName", source = "info.secondName")
    @Mapping(target = "fatherName", source = "info.fatherName")
    @Mapping(target = "birthdayDate", source = "info.birthdayDate")
    @Mapping(target = "country", source = "info.country")
    @Mapping(target = "address", source = "info.address")
    @Mapping(target = "phoneNumber", source = "info.phoneNumber")
    @Mapping(target = "telegram", source = "info.telegramNikName")
    @Mapping(target = "email", source = "info.email")
    @Mapping(target = "portalLogin", source = "info.portalLogin")
    @Mapping(target = "portalPassword", source = "info.portalPassword")
    @Mapping(target = "discordLogin", source = "info.discordLogin")
    @Mapping(target = "registrationDate", source = "info.registrationDate")
    @Mapping(target = "contractInfo", ignore = true)
    @Mapping(target = "archiveInfo", ignore = true)
    @Mapping(target = "workHistoryInfo", ignore = true)
    @Mapping(target = "historyStages", ignore = true)
    @Mapping(target = "notes", ignore = true)
    AgentDetailDto toAgentDetailDto(AgentEntity agent);

    @AfterMapping
    default void mapHistoryStages(AgentEntity agent, @MappingTarget AgentDetailDto dto) {
        if (agent.getAgentHistory() != null) {
            dto.setHistoryStages(buildHistoryStages(agent.getAgentHistory()));
            
            if (agent.getAgentHistory().getContractHistory() != null) {
                var contract = agent.getAgentHistory().getContractHistory();
                dto.setContractInfo(new ContractDto(
                    contract.isSigned(),
                    contract.getSignDate(),
                    contract.getReSignDate(),
                    contract.getBrokeDate(),
                    contract.getStartDate(),
                    contract.getEndDate()
                ));
            }
            
            if (agent.getAgentHistory().getArchiveHistory() != null) {
                var archive = agent.getAgentHistory().getArchiveHistory();
                dto.setArchiveInfo(new ArchiveDto(
                    archive.getArchiveDate(),
                    archive.getReason()
                ));
            }
            
            if (agent.getAgentHistory().getWorkHistory() != null) {
                var workHistory = agent.getAgentHistory().getWorkHistory();
                List<WorkPlaceDto> workPlaces = null;
                if (workHistory.getWorkPlaces() != null) {
                    workPlaces = workHistory.getWorkPlaces().stream()
                            .map(wp -> new WorkPlaceDto(
                                    wp.getId(),
                                    wp.getCompanyName(),
                                    wp.getSalary(),
                                    wp.getDeviceType(),
                                    wp.getStartDate(),
                                    wp.getEndDate()
                            ))
                            .collect(Collectors.toList());
                }
                dto.setWorkHistoryInfo(new WorkHistoryDto(
                        workHistory.getId(),
                        workHistory.getStartDate(),
                        workHistory.getEndDate(),
                        workPlaces
                ));
            }
        }
        if (agent.getNotes() != null) {
            dto.setNotes(agent.getNotes().stream()
                    .map(note -> new NoteDto(note.getId(), note.getDate(), note.getNote()))
                    .sorted((n1, n2) -> {
                        if (n1.getDate() == null && n2.getDate() == null) return 0;
                        if (n1.getDate() == null) return 1;
                        if (n2.getDate() == null) return -1;
                        return n2.getDate().compareTo(n1.getDate());
                    })
                    .collect(Collectors.toList()));
        }
    }

    default List<HistoryStageDto> buildHistoryStages(AgentHistoryEntity history) {
        List<HistoryStageDto> stages = new ArrayList<>();

        if (history.getEducationFirstPartHistory() != null) {
            stages.add(createHistoryStage("FIRST_PART_EDUCATION",
                    history.getEducationFirstPartHistory().getStartDate(),
                    history.getEducationFirstPartHistory().getEndDate(),
                    history.getEducationFirstPartHistory().getModules()));
        }

        if (history.getContractHistory() != null) {
            stages.add(createHistoryStage("SIGN",
                    history.getContractHistory().getStartDate(),
                    history.getContractHistory().getEndDate(),
                    null));
        }

        if (history.getEducationSecondPartHistory() != null) {
            stages.add(createHistoryStage("SECOND_PART_EDUCATION",
                    history.getEducationSecondPartHistory().getStartDate(),
                    history.getEducationSecondPartHistory().getEndDate(),
                    history.getEducationSecondPartHistory().getModules()));
        }

        if (history.getCvHistory() != null) {
            stages.add(createHistoryStage("CV",
                    history.getCvHistory().getStartDate(),
                    history.getCvHistory().getEndDate(),
                    history.getCvHistory().getModules()));
        }

        if (history.getPracticeHistory() != null) {
            stages.add(createHistoryStage("PRACTICE",
                    history.getPracticeHistory().getStartDate(),
                    history.getPracticeHistory().getEndDate(),
                    history.getPracticeHistory().getModules()));
        }

        if (history.getSearchHistory() != null) {
            stages.add(createHistoryStage("SEARCH",
                    history.getSearchHistory().getStartDate(),
                    history.getSearchHistory().getEndDate(),
                    null));
        }

        if (history.getWorkHistory() != null) {
            stages.add(createHistoryStage("WORK",
                    history.getWorkHistory().getStartDate(),
                    history.getWorkHistory().getEndDate(),
                    null));
        }

        if (history.getCompletedHistory() != null) {
            stages.add(createHistoryStage("DONE",
                    history.getCompletedHistory().getCompletedDate(),
                    null,
                    null));
        }

        if (history.getArchiveHistory() != null) {
            stages.add(createHistoryStage("ARCHIVE",
                    history.getArchiveHistory().getArchiveDate(),
                    null,
                    null));
        }

        return stages;
    }

    default HistoryStageDto createHistoryStage(String stage, LocalDate startDate, LocalDate endDate, List<?> modules) {
        List<ModuleDto> moduleDtos = null;
        if (modules != null) {
            moduleDtos = modules.stream()
                    .map(m -> {
                        ModuleDto dto = new ModuleDto();
                        if (m instanceof EducationFirstPartModuleEntity first) {
                            dto.setStageName(first.getStageName());
                            dto.setStartDate(first.getStartDate());
                            dto.setEndDate(first.getEndDate());
                        } else if (m instanceof EducationSecondPartModuleEntity second) {
                            dto.setStageName(second.getStageName());
                            dto.setStartDate(second.getStartDate());
                            dto.setEndDate(second.getEndDate());
                        } else if (m instanceof CvModuleEntity cv) {
                            dto.setStageName(cv.getStageName());
                            dto.setStartDate(cv.getStartDate());
                            dto.setEndDate(cv.getEndDate());
                        } else if (m instanceof PracticeModuleEntity practice) {
                            dto.setStageName(practice.getStageName());
                            dto.setStartDate(practice.getStartDate());
                            dto.setEndDate(practice.getEndDate());
                        }
                        return dto;
                    })
                    .collect(Collectors.toList());
        }

        return new HistoryStageDto(stage, startDate, endDate, moduleDtos);
    }
}
