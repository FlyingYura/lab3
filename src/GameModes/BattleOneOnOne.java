package GameModes;

import droids.BaseDroid;
import droids.HealerDroid;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BattleOneOnOne {
    private BaseDroid droid1;
    private BaseDroid droid2;
    private StringBuilder battleLog;

    public BattleOneOnOne(BaseDroid droid1, BaseDroid droid2) {
        this.droid1 = droid1;
        this.droid2 = droid2;
        this.battleLog = new StringBuilder();
    }

    public void start(Scanner scanner) {
        System.out.println("Бій 1 на 1 розпочався між " + droid1.getName() + " та " + droid2.getName());
        battleLog.append("Бій 1 на 1 розпочався між ").append(droid1.getName()).append(" та ").append(droid2.getName()).append("\n");

        while (droid1.isAlive() && droid2.isAlive()) {
            chooseAttack(scanner, droid1, droid2);

            if (!droid2.isAlive()) {
                break;
            }
            chooseAttack(scanner, droid2, droid1);
        }

        if (droid1.isAlive()) {
            System.out.println(droid1.getName() + " переміг!");
            battleLog.append(droid1.getName()).append(" переміг!\n");
        } else {
            System.out.println(droid2.getName() + " переміг!");
            battleLog.append(droid2.getName()).append(" переміг!\n");
        }

        saveBattleLog();
    }
    private void chooseAttack(Scanner scanner, BaseDroid attacker, BaseDroid opponent) {
        System.out.println(attacker.getName() + " обирає дію: ");
        System.out.println("1. Звичайна атака");
        System.out.println("2. Використати спеціальну здібність");

        int choice = scanner.nextInt();
        if (choice == 1) {
            attacker.attack(opponent);
        } else if (choice == 2) {
            if (attacker instanceof HealerDroid) {
                ((HealerDroid) attacker).useSpecialAbility(attacker);
                battleLog.append(attacker.getName()).append(" лікує себе, здоров'я: ").append(attacker.getHealth()).append("\n");
            } else {
                attacker.useSpecialAbility(opponent);
            }
        } else {
            System.out.println("Невірний вибір! Спробуйте ще раз.");
            chooseAttack(scanner, attacker, opponent);
        }

        battleLog.append(attacker.getName()).append(" атакує ").append(opponent.getName()).append(", залишилось здоров'я: ").append(opponent.getHealth()).append("\n");
    }

    private void saveBattleLog() {
        String fileName = "C:/Users/proko/Desktop/replay.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(battleLog.toString());
            System.out.println("Лог бою збережено у файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка при записі файлу: " + e.getMessage());
        }
    }
}
