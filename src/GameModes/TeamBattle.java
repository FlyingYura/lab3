package GameModes;

import droids.BaseDroid;
import droids.HealerDroid;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TeamBattle {
    private List<BaseDroid> team1;
    private List<BaseDroid> team2;
    private StringBuilder battleLog;

    public TeamBattle(List<BaseDroid> team1, List<BaseDroid> team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.battleLog = new StringBuilder();
    }

    public void start(Scanner scanner) {
        System.out.println("Командний бій почався!");
        battleLog.append("Командний бій почався!\n");

        while (teamIsAlive(team1) && teamIsAlive(team2)) {
            BaseDroid droid1 = selectAliveDroid(team1);
            BaseDroid droid2 = selectAliveDroid(team2);

            if (droid1 != null) {
                chooseTeamAttack(scanner, droid1, droid2);
            }

            if (droid2 != null && droid2.isAlive()) {
                chooseTeamAttack(scanner, droid2, droid1);
            }
        }

        if (teamIsAlive(team1)) {
            System.out.println("Команда 1 перемогла!");
            battleLog.append("Команда 1 перемогла!\n");
        } else {
            System.out.println("Команда 2 перемогла!");
            battleLog.append("Команда 2 перемогла!\n");
        }

        saveBattleLog();
    }

    private void chooseTeamAttack(Scanner scanner, BaseDroid attacker, BaseDroid opponent) {
        if (attacker instanceof HealerDroid) {
            System.out.println(attacker.getName() + " обирає дію: ");
            System.out.println("1. Лікувати союзника");
            System.out.println("2. Атакувати противника");

            int choice = scanner.nextInt();
            if (choice == 1) {
                BaseDroid allyToHeal = selectAllyToHeal(attacker instanceof HealerDroid ? team1 : team2);
                if (allyToHeal != null) {
                    ((HealerDroid) attacker).useSpecialAbility(allyToHeal);
                    battleLog.append(attacker.getName()).append(" лікує ").append(allyToHeal.getName()).append(", здоров'я: ").append(allyToHeal.getHealth()).append("\n");
                } else {
                    System.out.println("Немає союзників для лікування.");
                }
            } else if (choice == 2) {
                attacker.attack(opponent);
                battleLog.append(attacker.getName()).append(" атакує ").append(opponent.getName()).append(", залишилось здоров'я: ").append(opponent.getHealth()).append("\n");
            }
        } else {
            System.out.println(attacker.getName() + " обирає дію: ");
            System.out.println("1. Звичайна атака");
            System.out.println("2. Використати спеціальну здібність");

            int choice = scanner.nextInt();
            if (choice == 1) {
                attacker.attack(opponent);
            } else if (choice == 2) {
                attacker.useSpecialAbility(opponent);
            }

            battleLog.append(attacker.getName()).append(" атакує ").append(opponent.getName()).append(", залишилось здоров'я: ").append(opponent.getHealth()).append("\n");
        }
    }

    private void saveBattleLog() {
        String fileName = "C:/Users/proko/Desktop/replay.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(battleLog.toString());
            System.out.println("Лог командного бою збережено у файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка при записі файлу: " + e.getMessage());
        }
    }

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
