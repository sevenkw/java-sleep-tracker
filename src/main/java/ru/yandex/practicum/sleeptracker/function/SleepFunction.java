package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.util.List;

public interface SleepFunction {
    SleepAnalysisResult apply(List<SleepingSession> sessions);

}