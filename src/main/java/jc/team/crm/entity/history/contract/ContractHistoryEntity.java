package jc.team.crm.entity.history.contract;

import jakarta.persistence.*;
import jc.team.crm.entity.history.AgentHistoryEntity;
import jc.team.crm.entity.abs.BaseDatedEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "contract_history")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ContractHistoryEntity extends BaseDatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "agent_history_id")
    private AgentHistoryEntity agentHistory;

    private boolean signed;

    private LocalDate signDate;

    private LocalDate reSignDate;

    private LocalDate brokeDate;
}

