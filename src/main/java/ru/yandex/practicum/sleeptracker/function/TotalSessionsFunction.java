package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.util.List;

public class TotalSessionsFunction implements SleepFunction {
    private static final String DESCRIPTION = "Общее количество сессий сна ";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        int sessionsConunt = sessions.size();
        return new SleepAnalysisResult(DESCRIPTION, sessionsConunt);
    }
}