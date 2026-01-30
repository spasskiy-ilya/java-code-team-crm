package jc.team.crm.service;

import jc.team.crm.dto.CreateNoteDto;
import jc.team.crm.entity.AgentEntity;
import jc.team.crm.entity.note.NoteEntity;
import jc.team.crm.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StudentNoteService {

    private final AgentRepository agentRepository;

    @Transactional
    public void addNote(CreateNoteDto dto) {
        AgentEntity agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new IllegalArgumentException("Агент не найден"));

        NoteEntity note = new NoteEntity();
        note.setAgent(agent);
        note.setDate(LocalDate.now());
        note.setNote(dto.getNote());

        if (agent.getNotes() == null) {
            agent.setNotes(new java.util.ArrayList<>());
        }
        agent.getNotes().add(note);
        agentRepository.save(agent);
    }
}
