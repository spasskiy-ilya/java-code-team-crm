package jc.team.crm.entity;

import jakarta.persistence.*;
import jc.team.crm.entity.history.AgentHistoryEntity;
import jc.team.crm.entity.info.AgentInfoEntity;
import jc.team.crm.entity.note.DailyNoteEntity;
import jc.team.crm.entity.note.NoteEntity;
import jc.team.crm.entity.status.CurrentStatusEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "agents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String login;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agent")
    private AgentInfoEntity info;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agent")
    private AgentHistoryEntity agentHistory;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agent")
    private CurrentStatusEntity currentStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agent")
    private List<DailyNoteEntity> dailyNotes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agent")
    private List<NoteEntity> notes;
}
