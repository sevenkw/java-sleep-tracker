package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class ChronotypeFunction implements SleepFunction {
    private static final String DESCRIPTION = "Хронотип пользователя ";
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION, "Нет данных");
        }

        LocalDate firstNight = sessions.stream()
                .map(s -> s.getStartSleep().toLocalDate())
                .min(LocalDate::compareTo)
                .get();

        LocalDate lastNight = sessions.stream()
                .map(s -> s.getEndSleep().toLocalDate())
                .max(LocalDate::compareTo)
                .get();

        List<LocalDate> allNights = firstNight.datesUntil(lastNight.plusDays(1))
                .collect(Collectors.toList());

        List<LocalDate> nightsWithSleep = allNights.stream()
                .filter(night -> sessions.stream().anyMatch(s -> intersectsNight(s, night)))
                .collect(Collectors.toList());

        List<SleepingSession> nightSessions = nightsWithSleep.stream()
                .map(night -> sessions.stream()
                        .filter(s -> intersectsNight(s, night))
                        .findFirst()
                        .get())
                .collect(Collectors.toList());

        long countOwl = nightSessions.stream()
                .filter(s -> s.getStartSleep().toLocalTime().isAfter(LocalTime.of(23, 0)) &&
                        s.getEndSleep().toLocalTime().isAfter(LocalTime.of(9, 0)))
                .count();

        long countLark = nightSessions.stream()
                .filter(s -> s.getStartSleep().toLocalTime().isBefore(LocalTime.of(22, 0)) &&
                        s.getEndSleep().toLocalTime().isBefore(LocalTime.of(7, 0)))
                .count();

        long countPigeon = nightSessions.size() - countOwl - countLark;

        String chronoType;

        if (countOwl > countLark && countOwl > countPigeon) {
            chronoType = "Сова";
        } else if (countLark > countOwl && countLark > countPigeon) {
            chronoType = "Жаворонок";
        } else {
            chronoType = "Голубь";
        }

        return new SleepAnalysisResult(DESCRIPTION, chronoType);
    }

    private static boolean intersectsNight(SleepingSession s, LocalDate nightDate) {
        return !s.getEndSleep().isBefore(nightDate.atStartOfDay()) &&
                !s.getStartSleep().isAfter(nightDate.atStartOfDay().plusHours(6));
    }
}
