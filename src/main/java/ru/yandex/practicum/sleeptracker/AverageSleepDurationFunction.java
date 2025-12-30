package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class AverageSleepDurationFunction implements SleepFunction {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        double avg = sessions.stream().mapToLong(s -> s.getDurationMinutes()).average().orElse(0.0);
        return new SleepAnalysisResult("Средняя продолжительность сессий сна (мин) ", avg);
    }
}