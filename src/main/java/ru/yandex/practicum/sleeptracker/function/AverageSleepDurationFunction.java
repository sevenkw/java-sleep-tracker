package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.util.List;

public class AverageSleepDurationFunction implements SleepFunction {
    private static final String DESCRIPTION = "Средняя продолжительность сессий сна (мин) ";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        double avg = sessions.stream().mapToLong(s -> s.getDurationMinutes()).average().orElse(0.0);
        return new SleepAnalysisResult(DESCRIPTION, avg);
    }
}