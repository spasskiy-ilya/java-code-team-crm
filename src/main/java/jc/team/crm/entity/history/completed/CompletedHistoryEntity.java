package jc.team.crm.entity.history.completed;

import jakarta.persistence.*;
import jc.team.crm.entity.history.AgentHistoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "completed_history")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletedHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "agent_history_id")
    private AgentHistoryEntity agentHistory;

    private LocalDate completedDate;
}

