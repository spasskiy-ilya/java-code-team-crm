package jc.team.crm.entity.history.education.first;

import jakarta.persistence.*;
import jc.team.crm.entity.history.AgentHistoryEntity;
import jc.team.crm.entity.abs.BaseDatedEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "education_first_part_history")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EducationFirstPartHistoryEntity extends BaseDatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "agent_history_id")
    private AgentHistoryEntity agentHistory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "educationHistory")
    private List<EducationFirstPartModuleEntity> modules;
}

