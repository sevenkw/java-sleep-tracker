package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.util.List;

public class MinSleepDurationFunction implements SleepFunction {
    private static final String DESCRIPTION = "Минимальная длительность сессии сна (мин) ";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long min = sessions.stream().mapToLong(s -> s.getDurationMinutes()).min().orElse(0);
        return new SleepAnalysisResult(DESCRIPTION, min);
    }
}