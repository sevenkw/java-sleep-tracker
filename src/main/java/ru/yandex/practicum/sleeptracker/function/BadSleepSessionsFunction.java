package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.model.SleepQuality;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.util.List;

public class BadSleepSessionsFunction implements SleepFunction {
    private static final String DESCRIPTION = "Сессий с плохим качеством сна ";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long countBadSessions = sessions.stream().filter(s -> s.getQuality() == SleepQuality.BAD)
                .count();
        return new SleepAnalysisResult(DESCRIPTION, countBadSessions);
    }
}