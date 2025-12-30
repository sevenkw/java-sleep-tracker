
package ru.yandex.practicum.sleeptracker.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class SleepingSession {
    private final LocalDateTime startSleep;
    private final LocalDateTime endSleep;
    private final SleepQuality quality;

    public SleepingSession(LocalDateTime startSleep, LocalDateTime endSleep, SleepQuality quality) {
        this.startSleep = startSleep;
        this.endSleep = endSleep;
        this.quality = quality;
    }

    public LocalDateTime getStartSleep() {
        return startSleep;
    }

    public LocalDateTime getEndSleep() {
        return endSleep;
    }

    public SleepQuality getQuality() {
        return quality;
    }

    public long getDurationMinutes() {
        Duration timeSleep = Duration.between(startSleep, endSleep);
        return timeSleep.toMinutes();
    }
}