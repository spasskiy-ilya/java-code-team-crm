package jc.team.crm.controller;

import jc.team.crm.dto.AgentRegistrationDto;
import jc.team.crm.service.RegistrationPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationPageService registrationPageService;

    @GetMapping
    public String showPage(Model model) {
        model.addAttribute("dto", new AgentRegistrationDto());

        return "registration/form";
    }

    @PostMapping
    public String register(@ModelAttribute AgentRegistrationDto dto) {
        registrationPageService.registerAgent(dto);

        return "redirect:/education";
    }
}
