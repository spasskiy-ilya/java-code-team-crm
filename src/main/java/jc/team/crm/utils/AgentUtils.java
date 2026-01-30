package jc.team.crm.utils;

import jc.team.crm.entity.AgentEntity;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
public final class AgentUtils {

    public static String buildFullName(AgentEntity agent) {
        String secondName = agent.getInfo() != null ? agent.getInfo().getSecondName() : null;
        String name = agent.getInfo() != null ? agent.getInfo().getName() : null;
        return (secondName != null ? secondName + " " : "") + (name != null ? name : "");
    }

    public static Long calculateDuration(LocalDate startDate) {
        if (startDate == null) {
            return 0L;
        }
        return ChronoUnit.DAYS.between(startDate, LocalDate.now());
    }

    public static Long calculateDuration(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            return 0L;
        }
        LocalDate end = endDate != null ? endDate : LocalDate.now();
        return ChronoUnit.DAYS.between(startDate, end);
    }
}
