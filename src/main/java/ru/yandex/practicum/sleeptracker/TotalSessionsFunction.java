package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class TotalSessionsFunction implements SleepFunction {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        int sessionsConunt = sessions.size();
        return new SleepAnalysisResult("Общее количество сессий сна ", sessionsConunt);
    }
}