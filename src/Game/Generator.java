package Game;

import java.io.*;
import java.util.Scanner;

public class Generator {
    public String readMapData(int map) {
        String str = "blank";
        boolean error = false;
        do {
            try {
                Scanner read = new Scanner(new File("src/Saves/" + map + ".txt"));
                str = read.nextLine();
                error = false;
            } catch (IOException E) {
                map = 0;
                System.out.println("Map Not Found");
                error = true;
            }
        } while (error);
        return str;
    }
}
