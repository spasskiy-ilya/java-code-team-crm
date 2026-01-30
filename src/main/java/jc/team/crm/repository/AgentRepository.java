package jc.team.crm.repository;

import jc.team.crm.entity.AgentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<AgentEntity, Long> {

    @Query("SELECT a FROM AgentEntity a WHERE a.currentStatus IS NULL " +
           "OR a.currentStatus.currentStage IS NULL " +
           "OR a.currentStatus.currentStage NOT IN :excludedStages")
    List<AgentEntity> findActiveAgents(@Param("excludedStages") List<String> excludedStages);

    @Query("SELECT a FROM AgentEntity a WHERE a.currentStatus IS NOT NULL " +
           "AND a.currentStatus.currentStage = 'SEARCH'")
    List<AgentEntity> findSearchAgents();

    @Query("SELECT a FROM AgentEntity a WHERE a.currentStatus IS NOT NULL " +
           "AND a.currentStatus.currentStage = 'WORK'")
    List<AgentEntity> findWorkAgents();

    @Query("SELECT a FROM AgentEntity a WHERE a.currentStatus IS NOT NULL " +
           "AND a.currentStatus.currentStage = 'DONE'")
    List<AgentEntity> findDoneAgents();

    @Query("SELECT a FROM AgentEntity a WHERE a.currentStatus IS NOT NULL " +
           "AND a.currentStatus.currentStage = 'ARCHIVE'")
    List<AgentEntity> findArchiveAgents();

    @Query("SELECT a FROM AgentEntity a WHERE a.currentStatus IS NOT NULL " +
           "AND a.currentStatus.currentStage IN ('FIRST_PART_EDUCATION', 'SIGN')")
    List<AgentEntity> findFirstPartEducationAgents();

    @Query("SELECT a FROM AgentEntity a WHERE a.currentStatus IS NOT NULL " +
           "AND a.currentStatus.currentStage IN ('SECOND_PART_EDUCATION', 'CV', 'PRACTICE')")
    List<AgentEntity> findSecondPartEducationAgents();
}

