package jc.team.crm.entity.history.education.practice;

import jakarta.persistence.*;
import jc.team.crm.entity.abs.BaseDatedEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "practice_modules")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PracticeModuleEntity extends BaseDatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "practice_history_id")
    private PracticeHistoryEntity practiceHistory;

    private String stageName;
}
