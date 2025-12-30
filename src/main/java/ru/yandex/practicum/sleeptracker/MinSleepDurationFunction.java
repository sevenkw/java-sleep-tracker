package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class MinSleepDurationFunction implements SleepFunction {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long min = sessions.stream().mapToLong(s -> s.getDurationMinutes()).min().orElse(0);
        return new SleepAnalysisResult("Минимальная длительность сессии сна (мин) ", min);
    }
}