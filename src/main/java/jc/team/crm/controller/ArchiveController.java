package jc.team.crm.controller;

import jc.team.crm.dto.AgentStatusUpdateDto;
import jc.team.crm.enums.HistoryStage;
import jc.team.crm.service.AgentStatusService;
import jc.team.crm.service.ArchivePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/archive")
@RequiredArgsConstructor
public class ArchiveController {

    private final ArchivePageService archivePageService;
    private final AgentStatusService agentStatusService;

    @GetMapping
    public String showPage(Model model) {
        model.addAttribute("agents", archivePageService.getArchiveAgents());
        model.addAttribute("stages", HistoryStage.values());

        return "archive/list";
    }

    @PostMapping("/status")
    public String updateStatus(@ModelAttribute AgentStatusUpdateDto dto) {
        agentStatusService.updateStatus(dto);

        return "redirect:/archive";
    }
}
