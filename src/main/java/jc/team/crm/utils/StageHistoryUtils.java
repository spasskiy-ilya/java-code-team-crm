package jc.team.crm.utils;

import jc.team.crm.entity.history.AgentHistoryEntity;
import jc.team.crm.entity.history.archive.ArchiveHistoryEntity;
import jc.team.crm.entity.history.completed.CompletedHistoryEntity;
import jc.team.crm.entity.history.contract.ContractHistoryEntity;
import jc.team.crm.entity.history.education.cv.CvHistoryEntity;
import jc.team.crm.entity.history.education.first.EducationFirstPartHistoryEntity;
import jc.team.crm.entity.history.education.practice.PracticeHistoryEntity;
import jc.team.crm.entity.history.education.second.EducationSecondPartHistoryEntity;
import jc.team.crm.entity.history.search.SearchHistoryEntity;
import jc.team.crm.entity.history.work.WorkHistoryEntity;

import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public final class StageHistoryUtils {

    public static void closePreviousStageHistory(AgentHistoryEntity agentHistory, String stage) {
        LocalDate now = LocalDate.now();
        
        StageModuleUtils.closeAllActiveModules(agentHistory, stage);
        
        switch (stage) {
            case "FIRST_PART_EDUCATION" -> {
                if (agentHistory.getEducationFirstPartHistory() != null
                        && agentHistory.getEducationFirstPartHistory().getEndDate() == null) {
                    agentHistory.getEducationFirstPartHistory().setEndDate(now);
                }
            }
            case "SIGN" -> {
                if (agentHistory.getContractHistory() != null
                        && agentHistory.getContractHistory().getEndDate() == null) {
                    agentHistory.getContractHistory().setEndDate(now);
                }
            }
            case "SECOND_PART_EDUCATION" -> {
                if (agentHistory.getEducationSecondPartHistory() != null
                        && agentHistory.getEducationSecondPartHistory().getEndDate() == null) {
                    agentHistory.getEducationSecondPartHistory().setEndDate(now);
                }
            }
            case "CV" -> {
                if (agentHistory.getCvHistory() != null
                        && agentHistory.getCvHistory().getEndDate() == null) {
                    agentHistory.getCvHistory().setEndDate(now);
                }
            }
            case "PRACTICE" -> {
                if (agentHistory.getPracticeHistory() != null
                        && agentHistory.getPracticeHistory().getEndDate() == null) {
                    agentHistory.getPracticeHistory().setEndDate(now);
                }
            }
            case "SEARCH" -> {
                if (agentHistory.getSearchHistory() != null
                        && agentHistory.getSearchHistory().getEndDate() == null) {
                    agentHistory.getSearchHistory().setEndDate(now);
                }
            }
            case "WORK" -> {
                if (agentHistory.getWorkHistory() != null
                        && agentHistory.getWorkHistory().getEndDate() == null) {
                    agentHistory.getWorkHistory().setEndDate(now);
                }
            }
        }
    }

    public static void createNewStageHistory(AgentHistoryEntity agentHistory, String stage, String subStage) {
        LocalDate now = LocalDate.now();
        switch (stage) {
            case "FIRST_PART_EDUCATION" -> {
                EducationFirstPartHistoryEntity history = agentHistory.getEducationFirstPartHistory();
                if (history == null) {
                    history = EducationFirstPartHistoryEntity.builder()
                            .startDate(now)
                            .agentHistory(agentHistory)
                            .build();
                    agentHistory.setEducationFirstPartHistory(history);
                } else {
                    history.setStartDate(now);
                    history.setEndDate(null);
                }
                if (subStage != null) {
                    StageModuleUtils.createFirstPartModule(history, subStage, now);
                }
            }
            case "SIGN" -> {
                ContractHistoryEntity history = agentHistory.getContractHistory();
                if (history == null) {
                    history = ContractHistoryEntity.builder()
                            .startDate(now)
                            .agentHistory(agentHistory)
                            .build();
                    agentHistory.setContractHistory(history);
                } else {
                    history.setStartDate(now);
                    history.setEndDate(null);
                }
            }
            case "SECOND_PART_EDUCATION" -> {
                EducationSecondPartHistoryEntity history = agentHistory.getEducationSecondPartHistory();
                if (history == null) {
                    history = EducationSecondPartHistoryEntity.builder()
                            .startDate(now)
                            .agentHistory(agentHistory)
                            .build();
                    agentHistory.setEducationSecondPartHistory(history);
                } else {
                    history.setStartDate(now);
                    history.setEndDate(null);
                }
                if (subStage != null) {
                    StageModuleUtils.createSecondPartModule(history, subStage, now);
                }
            }
            case "CV" -> {
                CvHistoryEntity history = agentHistory.getCvHistory();
                if (history == null) {
                    history = CvHistoryEntity.builder()
                            .startDate(now)
                            .agentHistory(agentHistory)
                            .build();
                    agentHistory.setCvHistory(history);
                } else {
                    history.setStartDate(now);
                    history.setEndDate(null);
                }
                if (subStage != null) {
                    StageModuleUtils.createCvModule(history, subStage, now);
                }
            }
            case "PRACTICE" -> {
                PracticeHistoryEntity history = agentHistory.getPracticeHistory();
                if (history == null) {
                    history = PracticeHistoryEntity.builder()
                            .startDate(now)
                            .agentHistory(agentHistory)
                            .build();
                    agentHistory.setPracticeHistory(history);
                } else {
                    history.setStartDate(now);
                    history.setEndDate(null);
                }
                if (subStage != null) {
                    StageModuleUtils.createPracticeModule(history, subStage, now);
                }
            }
            case "SEARCH" -> {
                SearchHistoryEntity history = agentHistory.getSearchHistory();
                if (history == null) {
                    history = SearchHistoryEntity.builder()
                            .startDate(now)
                            .agentHistory(agentHistory)
                            .build();
                    agentHistory.setSearchHistory(history);
                } else {
                    history.setStartDate(now);
                    history.setEndDate(null);
                }
            }
            case "WORK" -> {
                WorkHistoryEntity history = agentHistory.getWorkHistory();
                if (history == null) {
                    history = WorkHistoryEntity.builder()
                            .startDate(now)
                            .agentHistory(agentHistory)
                            .build();
                    agentHistory.setWorkHistory(history);
                } else {
                    history.setStartDate(now);
                    history.setEndDate(null);
                }
            }
            case "DONE" -> {
                CompletedHistoryEntity history = agentHistory.getCompletedHistory();
                if (history == null) {
                    history = CompletedHistoryEntity.builder()
                            .completedDate(now)
                            .agentHistory(agentHistory)
                            .build();
                    agentHistory.setCompletedHistory(history);
                } else {
                    history.setCompletedDate(now);
                }
            }
            case "ARCHIVE" -> {
                ArchiveHistoryEntity history = agentHistory.getArchiveHistory();
                if (history == null) {
                    history = ArchiveHistoryEntity.builder()
                            .archiveDate(now)
                            .agentHistory(agentHistory)
                            .build();
                    agentHistory.setArchiveHistory(history);
                } else {
                    history.setArchiveDate(now);
                }
            }
        }
    }

    public static void createNewSubStageModule(AgentHistoryEntity agentHistory, String stage, String subStage) {
        LocalDate now = LocalDate.now();
        switch (stage) {
            case "FIRST_PART_EDUCATION" -> {
                EducationFirstPartHistoryEntity history = agentHistory.getEducationFirstPartHistory();
                if (history != null) {
                    StageModuleUtils.createFirstPartModule(history, subStage, now);
                }
            }
            case "SECOND_PART_EDUCATION" -> {
                EducationSecondPartHistoryEntity history = agentHistory.getEducationSecondPartHistory();
                if (history != null) {
                    StageModuleUtils.createSecondPartModule(history, subStage, now);
                }
            }
            case "CV" -> {
                CvHistoryEntity history = agentHistory.getCvHistory();
                if (history != null) {
                    StageModuleUtils.createCvModule(history, subStage, now);
                }
            }
            case "PRACTICE" -> {
                PracticeHistoryEntity history = agentHistory.getPracticeHistory();
                if (history != null) {
                    StageModuleUtils.createPracticeModule(history, subStage, now);
                }
            }
        }
    }
}
