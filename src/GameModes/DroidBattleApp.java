package GameModes;

import droids.BaseDroid;
import droids.BattleDroid;
import droids.HealerDroid;
import droids.SniperDroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DroidBattleApp {
    private static List<BaseDroid> droids = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Створити дроїда");
            System.out.println("2. Показати список дроїдів");
            System.out.println("3. Бій 1 на 1");
            System.out.println("4. Командний бій");
            System.out.println("5. Відтворити бій з файлу");
            System.out.println("6. Вийти");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createDroid(scanner);
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    battleOneOnOne(scanner);
                    break;
                case 4:
                    teamBattle(scanner);
                    break;
                case 5:
                    replayBattle(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Невірний вибір.");
            }
        }
    }

    private static void createDroid(Scanner scanner) {
        System.out.println("Оберіть тип дроїда:");
        System.out.println("1. Бойовий дроїд");
        System.out.println("2. Лікар");
        System.out.println("3. Снайпер");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Пропуск символу переводу рядка

        System.out.print("Введіть ім'я дроїда: ");
        String name = scanner.nextLine();

        BaseDroid droid = null;
        switch (choice) {
            case 1:
                droid = new BattleDroid(name);
                break;
            case 2:
                droid = new HealerDroid(name);
                break;
            case 3:
                droid = new SniperDroid(name);
                break;
            default:
                System.out.println("Невірний вибір.");
                return;
        }
        droids.add(droid);
        System.out.println("Дроїда створено: " + droid.getName());
    }

    private static void showDroids() {
        if (droids.isEmpty()) {
            System.out.println("Немає створених дроїдів.");
        } else {
            for (int i = 0; i < droids.size(); i++) {
                System.out.println((i + 1) + ". " + droids.get(i).getName() + " (" + droids.get(i).getDroidType() + ")");
            }
        }
    }

    private static void battleOneOnOne(Scanner scanner) {
        if (droids.size() < 2) {
            System.out.println("Необхідно мінімум 2 дроїди для бою.");
            return;
        }

        System.out.println("Оберіть першого дроїда:");
        showDroids();
        int droid1Index = scanner.nextInt() - 1;

        System.out.println("Оберіть другого дроїда:");
        showDroids();
        int droid2Index = scanner.nextInt() - 1;

        BaseDroid droid1 = droids.get(droid1Index);
        BaseDroid droid2 = droids.get(droid2Index);

        BattleOneOnOne battle = new BattleOneOnOne(droid1, droid2);
        battle.start();
    }

    private static void teamBattle(Scanner scanner) {
        if (droids.size() < 4) {
            System.out.println("Необхідно мінімум 4 дроїди для командного бою.");
            return;
        }

        List<BaseDroid> team1 = new ArrayList<>();
        List<BaseDroid> team2 = new ArrayList<>();

        System.out.println("Формування команди 1:");
        for (int i = 0; i < 2; i++) {
            showDroids();
            int index = scanner.nextInt() - 1;
            team1.add(droids.get(index));
        }

        System.out.println("Формування команди 2:");
        for (int i = 0; i < 2; i++) {
            showDroids();
            int index = scanner.nextInt() - 1;
            team2.add(droids.get(index));
        }

        TeamBattle teamBattle = new TeamBattle(team1, team2);
        teamBattle.start();
    }

    private static void replayBattle(Scanner scanner) {
        System.out.print("Введіть ім'я файлу для відтворення бою: ");
        String fileName = scanner.next();
        BattleReplay.replayBattle(fileName);
    }
}
