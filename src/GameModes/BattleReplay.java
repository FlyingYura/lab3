package GameModes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BattleReplay {
    public static void replayBattle() {
        String fileName = "C:/Users/proko/Desktop/replay.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }
}
