package jc.team.crm.entity.abs;

import jakarta.persistence.*;
import jc.team.crm.entity.AgentEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseNoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private AgentEntity agent;

    private LocalDate date;

    private String note;
}

