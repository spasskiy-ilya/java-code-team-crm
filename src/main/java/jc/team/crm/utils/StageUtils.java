package jc.team.crm.utils;

import jc.team.crm.enums.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class StageUtils {

    public static String getFirstSubStage(String stageName) {
        if (stageName == null) {
            return null;
        }

        return switch (stageName) {
            case "FIRST_PART_EDUCATION" -> SubFirstPartEducation.values()[0].name();
            case "SIGN" -> SubSign.values()[0].name();
            case "SECOND_PART_EDUCATION" -> SubSecondPartEducation.values()[0].name();
            case "CV" -> SubCv.values()[0].name();
            case "PRACTICE" -> SubPractice.values()[0].name();
            case "SEARCH" -> SubSearch.values()[0].name();
            case "WORK" -> SubWork.values().length > 0 ? SubWork.values()[0].name() : null;
            case "DONE" -> SubDone.values()[0].name();
            case "ARCHIVE" -> SubArchive.values()[0].name();
            default -> null;
        };
    }

    public static String getSubStageTitle(String subStageName) {
        if (subStageName == null) {
            return null;
        }

        try {
            for (SubFirstPartEducation e : SubFirstPartEducation.values()) {
                if (e.name().equals(subStageName)) {
                    return e.getTitle();
                }
            }
            for (SubSign e : SubSign.values()) {
                if (e.name().equals(subStageName)) {
                    return e.getTitle();
                }
            }
            for (SubSecondPartEducation e : SubSecondPartEducation.values()) {
                if (e.name().equals(subStageName)) {
                    return e.getTitle();
                }
            }
            for (SubCv e : SubCv.values()) {
                if (e.name().equals(subStageName)) {
                    return e.getTitle();
                }
            }
            for (SubPractice e : SubPractice.values()) {
                if (e.name().equals(subStageName)) {
                    return e.getTitle();
                }
            }
            for (SubSearch e : SubSearch.values()) {
                if (e.name().equals(subStageName)) {
                    return e.getTitle();
                }
            }
            for (SubWork e : SubWork.values()) {
                if (e.name().equals(subStageName)) {
                    return e.getTitle();
                }
            }
            for (SubDone e : SubDone.values()) {
                if (e.name().equals(subStageName)) {
                    return e.getTitle();
                }
            }
            for (SubArchive e : SubArchive.values()) {
                if (e.name().equals(subStageName)) {
                    return e.getTitle();
                }
            }
        } catch (Exception e) {
        }

        return subStageName;
    }

    public static int getStageOrder(String stageName) {
        if (stageName == null) {
            return Integer.MAX_VALUE;
        }
        try {
            HistoryStage stage = HistoryStage.valueOf(stageName);
            return stage.getOrder();
        } catch (IllegalArgumentException e) {
            return Integer.MAX_VALUE;
        }
    }

    public static int getSubStageOrder(String subStageName, String stageName) {
        if (subStageName == null || stageName == null) {
            return Integer.MAX_VALUE;
        }
        try {
            return switch (stageName) {
                case "FIRST_PART_EDUCATION" -> {
                    SubFirstPartEducation subStage = SubFirstPartEducation.valueOf(subStageName);
                    yield subStage.getOrder();
                }
                case "SIGN" -> {
                    SubSign subStage = SubSign.valueOf(subStageName);
                    yield subStage.getOrder();
                }
                case "SECOND_PART_EDUCATION" -> {
                    SubSecondPartEducation subStage = SubSecondPartEducation.valueOf(subStageName);
                    yield subStage.getOrder();
                }
                case "CV" -> {
                    SubCv subStage = SubCv.valueOf(subStageName);
                    yield subStage.getOrder();
                }
                case "PRACTICE" -> {
                    SubPractice subStage = SubPractice.valueOf(subStageName);
                    yield subStage.getOrder();
                }
                case "SEARCH" -> {
                    SubSearch subStage = SubSearch.valueOf(subStageName);
                    yield subStage.getOrder();
                }
                case "WORK" -> {
                    SubWork subStage = SubWork.valueOf(subStageName);
                    yield subStage.getOrder();
                }
                case "DONE" -> {
                    SubDone subStage = SubDone.valueOf(subStageName);
                    yield subStage.getOrder();
                }
                case "ARCHIVE" -> {
                    SubArchive subStage = SubArchive.valueOf(subStageName);
                    yield subStage.getOrder();
                }
                default -> Integer.MAX_VALUE;
            };
        } catch (IllegalArgumentException e) {
            return Integer.MAX_VALUE;
        }
    }

    public static String[] getSubStages(String stageName) {
        if (stageName == null) {
            return new String[0];
        }

        return switch (stageName) {
            case "FIRST_PART_EDUCATION" -> {
                String[] result = new String[SubFirstPartEducation.values().length];
                for (int i = 0; i < SubFirstPartEducation.values().length; i++) {
                    result[i] = SubFirstPartEducation.values()[i].name();
                }
                yield result;
            }
            case "SIGN" -> {
                String[] result = new String[SubSign.values().length];
                for (int i = 0; i < SubSign.values().length; i++) {
                    result[i] = SubSign.values()[i].name();
                }
                yield result;
            }
            case "SECOND_PART_EDUCATION" -> {
                String[] result = new String[SubSecondPartEducation.values().length];
                for (int i = 0; i < SubSecondPartEducation.values().length; i++) {
                    result[i] = SubSecondPartEducation.values()[i].name();
                }
                yield result;
            }
            case "CV" -> {
                String[] result = new String[SubCv.values().length];
                for (int i = 0; i < SubCv.values().length; i++) {
                    result[i] = SubCv.values()[i].name();
                }
                yield result;
            }
            case "PRACTICE" -> {
                String[] result = new String[SubPractice.values().length];
                for (int i = 0; i < SubPractice.values().length; i++) {
                    result[i] = SubPractice.values()[i].name();
                }
                yield result;
            }
            case "SEARCH" -> {
                String[] result = new String[SubSearch.values().length];
                for (int i = 0; i < SubSearch.values().length; i++) {
                    result[i] = SubSearch.values()[i].name();
                }
                yield result;
            }
            case "WORK" -> {
                String[] result = new String[SubWork.values().length];
                for (int i = 0; i < SubWork.values().length; i++) {
                    result[i] = SubWork.values()[i].name();
                }
                yield result;
            }
            case "DONE" -> {
                String[] result = new String[SubDone.values().length];
                for (int i = 0; i < SubDone.values().length; i++) {
                    result[i] = SubDone.values()[i].name();
                }
                yield result;
            }
            case "ARCHIVE" -> {
                String[] result = new String[SubArchive.values().length];
                for (int i = 0; i < SubArchive.values().length; i++) {
                    result[i] = SubArchive.values()[i].name();
                }
                yield result;
            }
            default -> new String[0];
        };
    }
}
