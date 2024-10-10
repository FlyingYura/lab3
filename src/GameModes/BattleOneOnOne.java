package GameModes;

import droids.BaseDroid;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BattleOneOnOne {
    private BaseDroid droid1;
    private BaseDroid droid2;
    private StringBuilder battleLog; // Лог бою

    public BattleOneOnOne(BaseDroid droid1, BaseDroid droid2) {
        this.droid1 = droid1;
        this.droid2 = droid2;
        this.battleLog = new StringBuilder();
    }

    public void start() {
        System.out.println("Бій 1 на 1 розпочався між " + droid1.getName() + " та " + droid2.getName());
        battleLog.append("Бій 1 на 1 розпочався між ").append(droid1.getName()).append(" та ").append(droid2.getName()).append("\n");

        while (droid1.isAlive() && droid2.isAlive()) {
            droid1.attack(droid2);
            battleLog.append(droid1.getName()).append(" атакує ").append(droid2.getName()).append(", залишилось здоров'я: ").append(droid2.getHealth()).append("\n");

            if (droid2.isAlive()) {
                droid2.attack(droid1);
                battleLog.append(droid2.getName()).append(" атакує ").append(droid1.getName()).append(", залишилось здоров'я: ").append(droid1.getHealth()).append("\n");
            }
        }

        if (droid1.isAlive()) {
            System.out.println(droid1.getName() + " переміг!");
            battleLog.append(droid1.getName()).append(" переміг!\n");
        } else {
            System.out.println(droid2.getName() + " переміг!");
            battleLog.append(droid2.getName()).append(" переміг!\n");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву файлу для збереження бою: ");
        String fileName = scanner.nextLine(); // Користувач вводить назву файлу
        saveBattleLog(fileName); // Зберегти лог бою у вказаний файл
    }

    // Метод для збереження логів бою у файл
    private void saveBattleLog(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(battleLog.toString());
            System.out.println("Лог бою збережено у файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка при записі файлу: " + e.getMessage());
        }
    }
}
