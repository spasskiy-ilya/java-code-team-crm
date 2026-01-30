package jc.team.crm.controller;

import jc.team.crm.dto.AgentStatusUpdateDto;
import jc.team.crm.dto.DailyNoteDto;
import jc.team.crm.enums.HistoryStage;
import jc.team.crm.service.AgentStatusService;
import jc.team.crm.service.DailyPageService;
import jc.team.crm.utils.WeekUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/daily")
@RequiredArgsConstructor
public class DailyController {

    private final DailyPageService dailyPageService;
    private final AgentStatusService agentStatusService;

    @GetMapping
    public String showFirstPage(@RequestParam(value = "weekOffset", defaultValue = "0") int weekOffset, Model model) {
        return showPage("first", weekOffset, model);
    }

    @GetMapping("/first")
    public String showFirst(@RequestParam(value = "weekOffset", defaultValue = "0") int weekOffset, Model model) {
        return showPage("first", weekOffset, model);
    }

    @GetMapping("/second")
    public String showSecond(@RequestParam(value = "weekOffset", defaultValue = "0") int weekOffset, Model model) {
        return showPage("second", weekOffset, model);
    }

    @PostMapping("/{type}/notes")
    public String saveAllDailyNotes(@PathVariable String type,
                                    @RequestParam(value = "agentIds") List<Long> agentIds,
                                    @RequestParam(value = "dates") List<String> dateStrings,
                                    @RequestParam(value = "notes") List<String> notes,
                                    @RequestParam(value = "weekOffset", defaultValue = "0") int weekOffset) {
        for (int i = 0; i < agentIds.size(); i++) {
            DailyNoteDto dto = new DailyNoteDto();
            dto.setAgentId(agentIds.get(i));
            dto.setDate(LocalDate.parse(dateStrings.get(i)));
            dto.setNote(notes.get(i) != null ? notes.get(i) : "");
            dailyPageService.saveDailyNote(dto);
        }

        return "redirect:/daily/" + type + "?weekOffset=" + weekOffset;
    }

    @PostMapping("/{type}/status")
    public String updateStatus(@PathVariable String type,
                               @ModelAttribute AgentStatusUpdateDto dto,
                               @RequestParam(value = "weekOffset", defaultValue = "0") int weekOffset) {
        agentStatusService.updateStatus(dto);
        return "redirect:/daily/" + type + "?weekOffset=" + weekOffset;
    }

    @PostMapping("/notes")
    public String saveAllDailyNotes(@RequestParam(value = "agentIds") List<Long> agentIds,
                                    @RequestParam(value = "dates") List<String> dateStrings,
                                    @RequestParam(value = "notes") List<String> notes,
                                    @RequestParam(value = "weekOffset", defaultValue = "0") int weekOffset) {
        return saveAllDailyNotes("first", agentIds, dateStrings, notes, weekOffset);
    }

    @PostMapping("/status")
    public String updateStatus(@ModelAttribute AgentStatusUpdateDto dto,
                               @RequestParam(value = "weekOffset", defaultValue = "0") int weekOffset) {
        return updateStatus("first", dto, weekOffset);
    }

    private String showPage(String type, int weekOffset, Model model) {
        LocalDate monday = WeekUtils.getMondayOfCurrentWeek().plusWeeks(weekOffset);
        model.addAttribute("agents", dailyPageService.getAgents(type, monday));
        model.addAttribute("stages", HistoryStage.values());
        model.addAttribute("dailyNoteDto", new DailyNoteDto());
        model.addAttribute("weekDates", WeekUtils.getWeekDates(monday));
        model.addAttribute("weekOffset", weekOffset);
        model.addAttribute("dayNames", WeekUtils.getDayNames());
        model.addAttribute("type", type);
        model.addAttribute("title", "first".equals(type) ? "Первая часть обучения" : "Вторая часть обучения");

        return "daily/list";
    }
}
