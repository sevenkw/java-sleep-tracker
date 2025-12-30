package ru.yandex.practicum.sleeptracker;

import java.util.List;

public interface SleepFunction {
    SleepAnalysisResult apply(List<SleepingSession> sessions);

}