package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.util.List;

public class MaxSleepDurationFunction implements SleepFunction {
    private static final String DESCRIPTION = "Максимальая длительность сессии сна (мин) ";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long max = sessions.stream().mapToLong(s -> s.getDurationMinutes()).max().orElse(0);
        return new SleepAnalysisResult(DESCRIPTION, max);
    }
}