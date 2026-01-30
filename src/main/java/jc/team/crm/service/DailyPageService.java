package jc.team.crm.service;

import jc.team.crm.dto.DailyNoteDto;
import jc.team.crm.dto.FirstPartDailyDto;
import jc.team.crm.entity.AgentEntity;
import jc.team.crm.entity.note.DailyNoteEntity;
import jc.team.crm.repository.AgentRepository;
import jc.team.crm.utils.AgentUtils;
import jc.team.crm.utils.StageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static jc.team.crm.utils.WeekUtils.getMondayOfCurrentWeek;

@Service
@RequiredArgsConstructor
public class DailyPageService {

    private final AgentRepository agentRepository;

    public List<FirstPartDailyDto> getFirstPartDailyAgents(LocalDate weekMonday) {
        LocalDate monday = weekMonday != null ? weekMonday : getMondayOfCurrentWeek();
        
        return agentRepository.findFirstPartEducationAgents().stream()
                .map(agent -> mapToDailyDto(agent, monday))
                .sorted(Comparator
                        .comparing((FirstPartDailyDto dto) -> StageUtils.getStageOrder(dto.getCurrentStage()))
                        .thenComparing((FirstPartDailyDto dto) -> StageUtils.getSubStageOrder(dto.getCurrentSubStage(), dto.getCurrentStage())))
                .collect(Collectors.toList());
    }

    public List<FirstPartDailyDto> getSecondPartDailyAgents(LocalDate weekMonday) {
        LocalDate monday = weekMonday != null ? weekMonday : getMondayOfCurrentWeek();
        
        return agentRepository.findSecondPartEducationAgents().stream()
                .map(agent -> mapToDailyDto(agent, monday))
                .sorted(Comparator
                        .comparing((FirstPartDailyDto dto) -> StageUtils.getStageOrder(dto.getCurrentStage()))
                        .thenComparing((FirstPartDailyDto dto) -> StageUtils.getSubStageOrder(dto.getCurrentSubStage(), dto.getCurrentStage())))
                .collect(Collectors.toList());
    }

    private FirstPartDailyDto mapToDailyDto(AgentEntity agent, LocalDate monday) {
        FirstPartDailyDto dto = new FirstPartDailyDto();
        dto.setAgentId(agent.getId());
        dto.setFullName(AgentUtils.buildFullName(agent));
        dto.setLogin(agent.getLogin());
        dto.setTelegram(agent.getInfo() != null ? agent.getInfo().getTelegramNikName() : null);
        dto.setCurrentStage(agent.getCurrentStatus() != null ? agent.getCurrentStatus().getCurrentStage() : null);
        dto.setCurrentSubStage(agent.getCurrentStatus() != null ? agent.getCurrentStatus().getCurrentSubStage() : null);
        dto.setStageDuration(agent.getCurrentStatus() != null && agent.getCurrentStatus().getCurrentStageStart() != null 
                ? AgentUtils.calculateDuration(agent.getCurrentStatus().getCurrentStageStart()) : 0L);
        dto.setSubStageDuration(agent.getCurrentStatus() != null && agent.getCurrentStatus().getCurrentSubStageStart() != null 
                ? AgentUtils.calculateDuration(agent.getCurrentStatus().getCurrentSubStageStart()) : 0L);
        
        Map<String, String> dailyNotes = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            LocalDate date = monday.plusDays(i);
            String dateStr = date.toString();
            dailyNotes.put(dateStr, "");
            
            if (agent.getDailyNotes() != null) {
                agent.getDailyNotes().stream()
                        .filter(note -> date.equals(note.getDate()))
                        .findFirst()
                        .ifPresent(note -> dailyNotes.put(dateStr, note.getNote() != null ? note.getNote() : ""));
            }
        }
        dto.setDailyNotes(dailyNotes);
        
        return dto;
    }

    public List<FirstPartDailyDto> getAgents(String type, LocalDate weekMonday) {
        LocalDate monday = weekMonday != null ? weekMonday : getMondayOfCurrentWeek();
        
        if ("first".equals(type)) {
            return getFirstPartDailyAgents(monday);
        } else if ("second".equals(type)) {
            return getSecondPartDailyAgents(monday);
        } else {
            return getFirstPartDailyAgents(monday);
        }
    }

    @Transactional
    public void saveDailyNote(DailyNoteDto dto) {
        AgentEntity agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new IllegalArgumentException("Агент не найден"));

        DailyNoteEntity existingNote = null;
        if (agent.getDailyNotes() != null) {
            existingNote = agent.getDailyNotes().stream()
                    .filter(note -> dto.getDate().equals(note.getDate()))
                    .findFirst()
                    .orElse(null);
        }

        if (existingNote != null) {
            existingNote.setNote(dto.getNote() != null ? dto.getNote() : "");
        } else {
            DailyNoteEntity newNote = new DailyNoteEntity();
            newNote.setAgent(agent);
            newNote.setDate(dto.getDate());
            newNote.setNote(dto.getNote() != null ? dto.getNote() : "");
            if (agent.getDailyNotes() == null) {
                agent.setDailyNotes(new java.util.ArrayList<>());
            }
            agent.getDailyNotes().add(newNote);
        }

        agentRepository.save(agent);
    }
}
