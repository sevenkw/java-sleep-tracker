package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class MaxSleepDurationFunction implements SleepFunction {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long max = sessions.stream().mapToLong(s -> s.getDurationMinutes()).max().orElse(0);
        return new SleepAnalysisResult("Максимальая длительность сессии сна (мин) ", max);
    }
}