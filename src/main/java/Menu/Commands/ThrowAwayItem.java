package Menu.Commands;

import Accounts.Account;
import Accounts.Knight;
import Menu.Command;
import Logger.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThrowAwayItem implements Command {
    private Knight knight;;
    private static final Logger logger = Logger.getLogger(ReturnItem.class.getName());

    public ThrowAwayItem(Knight knight) {
        this.knight = knight;
    }

    public void execute() {
        Log.setupLogger(logger);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (int i = 0; i < knight.getAmmunitionItems().size(); i++) {
                System.out.println(i+1 + "." + knight.getAmmunitionItems().get(i));
            }
            System.out.println("Введіть номер елементу амуніції, який хочете викинути:");
            try {
                int key = scanner.nextInt();
                if (key >= 0 && key <= knight.getAmmunitionItems().size()) {
                    knight.deleteItem(key - 1);
                    logger.log(Level.INFO, "Knight throw away ammunition item");
                    break;
                } else System.out.println("Введіть коректне значення");
            }catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Хибний ввід");
            }
        }
    }
}
