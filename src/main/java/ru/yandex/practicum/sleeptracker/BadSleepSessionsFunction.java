package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class BadSleepSessionsFunction implements SleepFunction {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long countBadSessions = sessions.stream().filter(s -> s.getQuality() == SleepQuality.BAD)
                .count();
        return new SleepAnalysisResult("Сессий с плохим качеством сна ", countBadSessions);
    }
}