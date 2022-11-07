package Menu.Commands;

import Ammunition.AmmunitionItem;
import Menu.Command;
import Logger.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteItem implements Command {
    private List<AmmunitionItem> ammunitionItemList;

    public DeleteItem(List<AmmunitionItem> ammunitionItemList ) {
        this.ammunitionItemList = ammunitionItemList;
    }
    private static final Logger logger = Logger.getLogger(DeleteItem.class.getName());

    public void execute() {
        Log.setupLogger(logger);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (int i = 0; i < ammunitionItemList.size(); i++) {
                System.out.println(i+1 + "." + ammunitionItemList.get(i));
            }
            System.out.println("Введіть номер елементу амуніції, який хочете видалити:");
            try {
                int key = scanner.nextInt();
                if (key >= 0 && key <= ammunitionItemList.size()) {
                    ammunitionItemList.remove(key - 1);
                    logger.log(Level.INFO, "Ammunition item was successfully deleted");
                    break;
                }
                else System.out.println("Введіть коректне значення");
            }catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Хибний ввід");
            }
        }
    }
}
