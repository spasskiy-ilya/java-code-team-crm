package jc.team.crm.entity.history.work;

import jakarta.persistence.*;
import jc.team.crm.entity.history.AgentHistoryEntity;
import jc.team.crm.entity.abs.BaseDatedEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "work_history")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WorkHistoryEntity extends BaseDatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "agent_history_id")
    private AgentHistoryEntity agentHistory;
}

