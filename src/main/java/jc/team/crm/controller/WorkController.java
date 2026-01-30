package jc.team.crm.controller;

import jc.team.crm.dto.AgentStatusUpdateDto;
import jc.team.crm.enums.HistoryStage;
import jc.team.crm.service.AgentStatusService;
import jc.team.crm.service.WorkPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/work")
@RequiredArgsConstructor
public class WorkController {

    private final WorkPageService workPageService;
    private final AgentStatusService agentStatusService;

    @GetMapping
    public String showPage(Model model) {
        model.addAttribute("agents", workPageService.getWorkAgents());
        model.addAttribute("stages", HistoryStage.values());

        return "work/list";
    }

    @PostMapping("/status")
    public String updateStatus(@ModelAttribute AgentStatusUpdateDto dto) {
        agentStatusService.updateStatus(dto);

        return "redirect:/work";
    }
}
