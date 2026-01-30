package jc.team.crm.controller;

import jc.team.crm.dto.CreateNoteDto;
import jc.team.crm.dto.UpdateAgentInfoDto;
import jc.team.crm.dto.UpdateArchiveDto;
import jc.team.crm.dto.UpdateContractDto;
import jc.team.crm.service.StudentNoteService;
import jc.team.crm.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final AgentService agentService;
    private final StudentNoteService studentNoteService;

    @GetMapping("/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        model.addAttribute("agent", agentService.getAgentDetail(id));
        model.addAttribute("createNoteDto", new CreateNoteDto());

        return "agents/detail";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        var agentDetail = agentService.getAgentDetail(id);
        model.addAttribute("agent", agentDetail);
        model.addAttribute("updateDto", agentService.prepareUpdateAgentInfoDto(id));

        return "agents/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateInfo(@PathVariable Long id, @ModelAttribute UpdateAgentInfoDto dto) {
        dto.setAgentId(id);
        agentService.updateAgentInfo(dto);

        return "redirect:/students/" + id;
    }

    @PostMapping("/{id}/notes")
    public String addNote(@PathVariable Long id, @ModelAttribute CreateNoteDto dto) {
        dto.setAgentId(id);
        studentNoteService.addNote(dto);

        return "redirect:/students/" + id;
    }

    @GetMapping("/{id}/contract/edit")
    public String showContractEditForm(@PathVariable Long id, Model model) {
        var agentDetail = agentService.getAgentDetail(id);
        model.addAttribute("agent", agentDetail);
        model.addAttribute("updateDto", agentService.prepareUpdateContractDto(id));

        return "agents/contract-edit";
    }

    @PostMapping("/{id}/contract/edit")
    public String updateContract(@PathVariable Long id, @ModelAttribute UpdateContractDto dto) {
        dto.setAgentId(id);
        agentService.updateContract(dto);

        return "redirect:/students/" + id;
    }

    @GetMapping("/{id}/archive/edit")
    public String showArchiveEditForm(@PathVariable Long id, Model model) {
        var agentDetail = agentService.getAgentDetail(id);
        model.addAttribute("agent", agentDetail);
        model.addAttribute("updateDto", agentService.prepareUpdateArchiveDto(id));

        return "agents/archive-edit";
    }

    @PostMapping("/{id}/archive/edit")
    public String updateArchive(@PathVariable Long id, @ModelAttribute UpdateArchiveDto dto) {
        dto.setAgentId(id);
        agentService.updateArchiveInfo(dto);

        return "redirect:/students/" + id;
    }
}
