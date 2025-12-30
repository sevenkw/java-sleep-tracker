package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerApp {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите путь к файлу");
            return;
        }

        String filePath = args[0];
        List<SleepingSession> sessions = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            for (String line : lines) {
                String[] parts = line.split(";");
                LocalDateTime start = LocalDateTime.parse(parts[0], formatter);
                LocalDateTime end = LocalDateTime.parse(parts[1], formatter);
                SleepQuality quality = SleepQuality.valueOf(parts[2]);

                sessions.add(new SleepingSession(start, end, quality));

            }

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        }

        List<SleepFunction> sleepFunctions = List.of(new TotalSessionsFunction(),
                new MinSleepDurationFunction(),
                new MaxSleepDurationFunction(),
                new AverageSleepDurationFunction(),
                new BadSleepSessionsFunction(),
                new SleeplessNightsFunction(),
                new ChronotypeFunction()
        );

        System.out.println("Добро пожаловать в трекер сна!");
        System.out.println();

        sleepFunctions.forEach(f -> {
            SleepAnalysisResult result = f.apply(sessions);
            System.out.println(result.getDescription() + result.getValue());
        });

    }
}