package GameModes;

import droids.BaseDroid;
import droids.HealerDroid;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TeamBattle {
    private List<BaseDroid> team1;
    private List<BaseDroid> team2;
    private StringBuilder battleLog; // Лог бою

    public TeamBattle(List<BaseDroid> team1, List<BaseDroid> team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.battleLog = new StringBuilder();
    }

    public void start() {
        System.out.println("Командний бій почався!");
        battleLog.append("Командний бій почався!\n");

        Random random = new Random();
        while (teamIsAlive(team1) && teamIsAlive(team2)) {
            // Вибір випадкових дроїдів
            BaseDroid droid1 = selectAliveDroid(team1);
            BaseDroid droid2 = selectAliveDroid(team2);

            // Лікування або атака
            if (droid1 instanceof HealerDroid) {
                BaseDroid allyToHeal = selectAllyToHeal(team1);
                if (allyToHeal != null) {
                    ((HealerDroid) droid1).heal(allyToHeal);
                    battleLog.append(droid1.getName()).append(" лікує ").append(allyToHeal.getName()).append(", здоров'я: ").append(allyToHeal.getHealth()).append("\n");
                } else {
                    droid1.attack(droid2);
                    battleLog.append(droid1.getName()).append(" атакує ").append(droid2.getName()).append(", залишилось здоров'я: ").append(droid2.getHealth()).append("\n");
                }
            } else {
                droid1.attack(droid2);
                battleLog.append(droid1.getName()).append(" атакує ").append(droid2.getName()).append(", залишилось здоров'я: ").append(droid2.getHealth()).append("\n");
            }

            if (droid2.isAlive()) {
                if (droid2 instanceof HealerDroid) {
                    BaseDroid allyToHeal = selectAllyToHeal(team2);
                    if (allyToHeal != null) {
                        ((HealerDroid) droid2).heal(allyToHeal);
                        battleLog.append(droid2.getName()).append(" лікує ").append(allyToHeal.getName()).append(", здоров'я: ").append(allyToHeal.getHealth()).append("\n");
                    } else {
                        droid2.attack(droid1);
                        battleLog.append(droid2.getName()).append(" атакує ").append(droid1.getName()).append(", залишилось здоров'я: ").append(droid1.getHealth()).append("\n");
                    }
                } else {
                    droid2.attack(droid1);
                    battleLog.append(droid2.getName()).append(" атакує ").append(droid1.getName()).append(", залишилось здоров'я: ").append(droid1.getHealth()).append("\n");
                }
            }
        }

        // Визначаємо переможця
        if (teamIsAlive(team1)) {
            System.out.println("Команда 1 перемогла!");
            battleLog.append("Команда 1 перемогла!\n");
        } else {
            System.out.println("Команда 2 перемогла!");
            battleLog.append("Команда 2 перемогла!\n");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву файлу для збереження командного бою: ");
        String fileName = scanner.nextLine(); // Користувач вводить назву файлу
        saveBattleLog(fileName); // Зберегти лог бою у вказаний файл
    }

    private void saveBattleLog(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(battleLog.toString());
            System.out.println("Лог командного бою збережено у файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка при записі файлу: " + e.getMessage());
        }
    }

    // Інші методи вибору дроїдів та перевірки команди
    private boolean teamIsAlive(List<BaseDroid> team) {
        return team.stream().anyMatch(BaseDroid::isAlive);
    }

    private BaseDroid selectAliveDroid(List<BaseDroid> team) {
        return team.stream().filter(BaseDroid::isAlive).findFirst().orElse(null);
    }

    private BaseDroid selectAllyToHeal(List<BaseDroid> team) {
        return team.stream()
                .filter(droid -> droid.isAlive() && droid.getHealth() < 100)
                .findFirst()
                .orElse(null);
    }
}
