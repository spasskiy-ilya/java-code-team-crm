package jc.team.crm.entity.status;

import jakarta.persistence.*;
import jc.team.crm.entity.AgentEntity;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "current_status")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "agent_id")
    private AgentEntity agent;

    private String currentStage;

    private String currentSubStage;

    private LocalDate currentStageStart;

    private LocalDate currentSubStageStart;
}
