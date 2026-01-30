package jc.team.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FirstPartDailyDto {
    private Long agentId;
    private String fullName;
    private String login;
    private String telegram;
    private String currentStage;
    private String currentSubStage;
    private Long stageDuration;
    private Long subStageDuration;
    private Map<String, String> dailyNotes; // ключ - дата (строка), значение - заметка
}
