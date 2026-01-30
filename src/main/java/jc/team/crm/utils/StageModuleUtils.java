package jc.team.crm.utils;

import jc.team.crm.entity.history.AgentHistoryEntity;
import jc.team.crm.entity.history.education.cv.CvHistoryEntity;
import jc.team.crm.entity.history.education.cv.CvModuleEntity;
import jc.team.crm.entity.history.education.first.EducationFirstPartHistoryEntity;
import jc.team.crm.entity.history.education.first.EducationFirstPartModuleEntity;
import jc.team.crm.entity.history.education.practice.PracticeHistoryEntity;
import jc.team.crm.entity.history.education.practice.PracticeModuleEntity;
import jc.team.crm.entity.history.education.second.EducationSecondPartHistoryEntity;
import jc.team.crm.entity.history.education.second.EducationSecondPartModuleEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public final class StageModuleUtils {

    public static void closePreviousSubStageModule(AgentHistoryEntity agentHistory, String stage, String subStage) {
        LocalDate now = LocalDate.now();
        switch (stage) {
            case "FIRST_PART_EDUCATION" -> {
                EducationFirstPartHistoryEntity history = agentHistory.getEducationFirstPartHistory();
                if (history != null && history.getModules() != null) {
                    history.getModules().stream()
                            .filter(m -> subStage.equals(m.getStageName()) && m.getEndDate() == null)
                            .findFirst()
                            .ifPresent(m -> m.setEndDate(now));
                }
            }
            case "SECOND_PART_EDUCATION" -> {
                EducationSecondPartHistoryEntity history = agentHistory.getEducationSecondPartHistory();
                if (history != null && history.getModules() != null) {
                    history.getModules().stream()
                            .filter(m -> subStage.equals(m.getStageName()) && m.getEndDate() == null)
                            .findFirst()
                            .ifPresent(m -> m.setEndDate(now));
                }
            }
            case "CV" -> {
                CvHistoryEntity history = agentHistory.getCvHistory();
                if (history != null && history.getModules() != null) {
                    history.getModules().stream()
                            .filter(m -> subStage.equals(m.getStageName()) && m.getEndDate() == null)
                            .findFirst()
                            .ifPresent(m -> m.setEndDate(now));
                }
            }
            case "PRACTICE" -> {
                PracticeHistoryEntity history = agentHistory.getPracticeHistory();
                if (history != null && history.getModules() != null) {
                    history.getModules().stream()
                            .filter(m -> subStage.equals(m.getStageName()) && m.getEndDate() == null)
                            .findFirst()
                            .ifPresent(m -> m.setEndDate(now));
                }
            }
        }
    }

    public static void createFirstPartModule(EducationFirstPartHistoryEntity history, String subStage, LocalDate now) {
        EducationFirstPartModuleEntity module = EducationFirstPartModuleEntity.builder()
                .stageName(subStage)
                .startDate(now)
                .educationHistory(history)
                .build();
        List<EducationFirstPartModuleEntity> modules = history.getModules();
        if (modules == null) {
            modules = new ArrayList<>();
            history.setModules(modules);
        }
        modules.add(module);
    }

    public static void createSecondPartModule(EducationSecondPartHistoryEntity history, String subStage, LocalDate now) {
        EducationSecondPartModuleEntity module = EducationSecondPartModuleEntity.builder()
                .stageName(subStage)
                .startDate(now)
                .educationHistory(history)
                .build();
        List<EducationSecondPartModuleEntity> modules = history.getModules();
        if (modules == null) {
            modules = new ArrayList<>();
            history.setModules(modules);
        }
        modules.add(module);
    }

    public static void createCvModule(CvHistoryEntity history, String subStage, LocalDate now) {
        CvModuleEntity module = CvModuleEntity.builder()
                .stageName(subStage)
                .startDate(now)
                .cvHistory(history)
                .build();
        List<CvModuleEntity> modules = history.getModules();
        if (modules == null) {
            modules = new ArrayList<>();
            history.setModules(modules);
        }
        modules.add(module);
    }

    public static void createPracticeModule(PracticeHistoryEntity history, String subStage, LocalDate now) {
        PracticeModuleEntity module = PracticeModuleEntity.builder()
                .stageName(subStage)
                .startDate(now)
                .practiceHistory(history)
                .build();
        List<PracticeModuleEntity> modules = history.getModules();
        if (modules == null) {
            modules = new ArrayList<>();
            history.setModules(modules);
        }
        modules.add(module);
    }

    public static void closeAllActiveModules(AgentHistoryEntity agentHistory, String stage) {
        LocalDate now = LocalDate.now();
        switch (stage) {
            case "FIRST_PART_EDUCATION" -> {
                EducationFirstPartHistoryEntity history = agentHistory.getEducationFirstPartHistory();
                if (history != null && history.getModules() != null) {
                    history.getModules().stream()
                            .filter(m -> m.getEndDate() == null)
                            .forEach(m -> m.setEndDate(now));
                }
            }
            case "SECOND_PART_EDUCATION" -> {
                EducationSecondPartHistoryEntity history = agentHistory.getEducationSecondPartHistory();
                if (history != null && history.getModules() != null) {
                    history.getModules().stream()
                            .filter(m -> m.getEndDate() == null)
                            .forEach(m -> m.setEndDate(now));
                }
            }
            case "CV" -> {
                CvHistoryEntity history = agentHistory.getCvHistory();
                if (history != null && history.getModules() != null) {
                    history.getModules().stream()
                            .filter(m -> m.getEndDate() == null)
                            .forEach(m -> m.setEndDate(now));
                }
            }
            case "PRACTICE" -> {
                PracticeHistoryEntity history = agentHistory.getPracticeHistory();
                if (history != null && history.getModules() != null) {
                    history.getModules().stream()
                            .filter(m -> m.getEndDate() == null)
                            .forEach(m -> m.setEndDate(now));
                }
            }
        }
    }
}
