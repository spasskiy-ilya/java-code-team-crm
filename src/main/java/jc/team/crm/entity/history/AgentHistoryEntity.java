package jc.team.crm.entity.history;

import jakarta.persistence.*;
import jc.team.crm.entity.AgentEntity;
import jc.team.crm.entity.abs.BaseDatedEntity;
import jc.team.crm.entity.history.archive.ArchiveHistoryEntity;
import jc.team.crm.entity.history.completed.CompletedHistoryEntity;
import jc.team.crm.entity.history.contract.ContractHistoryEntity;
import jc.team.crm.entity.history.work.WorkHistoryEntity;
import jc.team.crm.entity.history.education.cv.CvHistoryEntity;
import jc.team.crm.entity.history.education.first.EducationFirstPartHistoryEntity;
import jc.team.crm.entity.history.education.second.EducationSecondPartHistoryEntity;
import jc.team.crm.entity.history.education.practice.PracticeHistoryEntity;
import jc.team.crm.entity.history.search.SearchHistoryEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "agent_history")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AgentHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "agent_id")
    private AgentEntity agent;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agentHistory")
    private EducationFirstPartHistoryEntity educationFirstPartHistory;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agentHistory")
    private ContractHistoryEntity contractHistory;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agentHistory")
    private EducationSecondPartHistoryEntity educationSecondPartHistory;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agentHistory")
    private CvHistoryEntity cvHistory;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agentHistory")
    private PracticeHistoryEntity practiceHistory;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agentHistory")
    private SearchHistoryEntity searchHistory;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agentHistory")
    private WorkHistoryEntity workHistory;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agentHistory")
    private CompletedHistoryEntity completedHistory;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agentHistory")
    private ArchiveHistoryEntity archiveHistory;
}
