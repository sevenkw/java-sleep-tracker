package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.*;
import ru.yandex.practicum.sleeptracker.model.SleepQuality;
import ru.yandex.practicum.sleeptracker.model.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SleepTrackerAppTest {

    @Test
    void testAverageSleepNormal() {
        AverageSleepDurationFunction func = new AverageSleepDurationFunction();
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 30, 22, 0),
                        LocalDateTime.of(2025, 12, 31, 6, 0),
                        SleepQuality.GOOD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 31, 23, 0),
                        LocalDateTime.of(2026, 1, 1, 7, 0),
                        SleepQuality.NORMAL
                )
        );
        SleepAnalysisResult result = func.apply(sessions);
        assertEquals(480.0, result.getValue());
    }

    @Test
    void testAverageSleepEmpty() {
        AverageSleepDurationFunction func = new AverageSleepDurationFunction();
        SleepAnalysisResult result = func.apply(List.of());
        assertEquals(0.0, result.getValue());
    }

    @Test
    void testBadSleepCount() {
        BadSleepSessionsFunction func = new BadSleepSessionsFunction();
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusHours(8), SleepQuality.BAD),
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusHours(6), SleepQuality.GOOD)
        );
        SleepAnalysisResult result = func.apply(sessions);
        assertEquals(1L, result.getValue());
    }

    @Test
    void testBadSleepEmpty() {
        BadSleepSessionsFunction func = new BadSleepSessionsFunction();
        SleepAnalysisResult result = func.apply(List.of());
        assertEquals(0L, result.getValue());
    }

    @Test
    void testChronotypeOwl() {
        ChronotypeFunction func = new ChronotypeFunction();
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 12, 30, 23, 30),
                        LocalDateTime.of(2025, 12, 31, 9, 30), SleepQuality.GOOD)
        );
        SleepAnalysisResult result = func.apply(sessions);
        assertEquals("Сова", result.getValue());
    }

    @Test
    void testChronotypeEmpty() {
        ChronotypeFunction func = new ChronotypeFunction();
        SleepAnalysisResult result = func.apply(List.of());
        assertEquals("Нет данных", result.getValue());
    }

    @Test
    void testMaxSleep() {
        MaxSleepDurationFunction func = new MaxSleepDurationFunction();
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusHours(5), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusHours(7), SleepQuality.BAD)
        );
        SleepAnalysisResult result = func.apply(sessions);
        assertEquals(7 * 60, (long) result.getValue());
    }

    @Test
    void testMaxSleepEmpty() {
        MaxSleepDurationFunction func = new MaxSleepDurationFunction();
        SleepAnalysisResult result = func.apply(List.of());
        assertEquals(0L, result.getValue());
    }

    @Test
    void testMinSleep() {
        MinSleepDurationFunction func = new MinSleepDurationFunction();
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusHours(5), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusHours(7), SleepQuality.BAD)
        );
        SleepAnalysisResult result = func.apply(sessions);
        assertEquals(5 * 60, (long) result.getValue());
    }

    @Test
    void testMinSleepEmpty() {
        MinSleepDurationFunction func = new MinSleepDurationFunction();
        SleepAnalysisResult result = func.apply(List.of());
        assertEquals(0L, result.getValue());
    }

    @Test
    void testSleeplessNightsSome() {
        SleeplessNightsFunction func = new SleeplessNightsFunction();
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 12, 30, 23, 0),
                        LocalDateTime.of(2025, 12, 31, 6, 0), SleepQuality.GOOD)
        );
        SleepAnalysisResult result = func.apply(sessions);
        assertEquals(0L, result.getValue());
    }

    @Test
    void testSleeplessNightsEmpty() {
        SleeplessNightsFunction func = new SleeplessNightsFunction();
        SleepAnalysisResult result = func.apply(List.of());
        assertEquals(0, result.getValue());
    }

    @Test
    void testTotalSessionsNormal() {
        TotalSessionsFunction func = new TotalSessionsFunction();
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 12, 30, 22, 0),
                        LocalDateTime.of(2025, 12, 31, 6, 0), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2025, 12, 31, 23, 0),
                        LocalDateTime.of(2026, 1, 1, 7, 0), SleepQuality.NORMAL)
        );

        SleepAnalysisResult result = func.apply(sessions);
        assertEquals(2, result.getValue());
    }

    @Test
    void testTotalSessionsEmpty() {
        TotalSessionsFunction func = new TotalSessionsFunction();
        SleepAnalysisResult result = func.apply(List.of());
        assertEquals(0, result.getValue());
    }
}

