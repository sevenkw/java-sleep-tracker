package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SleeplessNightsFunction implements SleepFunction {
    private static final String DESCRIPTION = "Количество бессонных ночей ";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION, 0);
        }

        SleepingSession firstSession = sessions.stream()
                .min((s1, s2) -> s1.getStartSleep().compareTo(s2.getStartSleep()))
                .get();

        SleepingSession lastSession = sessions.stream()
                .max((s1, s2) -> s1.getEndSleep().compareTo(s2.getEndSleep()))
                .get();

        LocalDate firstNight = (firstSession.getStartSleep().getHour() >= 12)
                ? firstSession.getStartSleep().toLocalDate().plusDays(1)
                : firstSession.getStartSleep().toLocalDate();

        LocalDate lastNight = lastSession.getEndSleep().toLocalDate();

        List<LocalDate> allNights = firstNight.datesUntil(lastNight.plusDays(1))
                .collect(Collectors.toList());

        long sleeplessCount = allNights.stream()
                .filter(night -> sessions.stream().noneMatch(s -> intersectsNight(s, night)))
                .count();

        return new SleepAnalysisResult(DESCRIPTION, sleeplessCount);
    }

    private static boolean intersectsNight(SleepingSession s, LocalDate nightDate) {
        LocalDateTime nightStart = nightDate.atStartOfDay();
        LocalDateTime nightEnd = nightStart.plusHours(6);
        return !s.getEndSleep().isBefore(nightStart) && !s.getStartSleep().isAfter(nightEnd);
    }
}
