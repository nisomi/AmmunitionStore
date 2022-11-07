package Menu.Commands;

import Ammunition.AmmunitionItem;
import Menu.Command;
import Logger.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowAllAmmunition implements Command {
    private final List<AmmunitionItem> ammunitionItems;
    private static final Logger logger = Logger.getLogger(ShowAllAmmunition.class.getName());

    public ShowAllAmmunition(List<AmmunitionItem> ammunitionItems) {
        this.ammunitionItems = ammunitionItems;
    }

    public void execute() {
        Log.setupLogger(logger);
        Scanner scanner = new Scanner(System.in);
        ShowActions();
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    for (AmmunitionItem ammunition : ammunitionItems) {
                        System.out.println(ammunition);
                    }
                    logger.log(Level.INFO, "showed all items");
                    break;
                }
                case 2: {
                    new SortItemsByCost(ammunitionItems).execute();
                    break;
                }
                case 3: {
                    new SortItemsByWeight(ammunitionItems).execute();
                    break;
                }
                case 4: {
                    new SearchItemsByCost(ammunitionItems).execute();
                    break;
                }
                case 5: {
                    new SearchItemsByWeight(ammunitionItems).execute();
                    break;
                }
                default: {
                    System.out.println("Введіть коректне значення");
                }
            }
        }catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Хибний ввід");
        }
    }
    private void ShowActions() {
        System.out.println("1. Просто показати амуніцію");
        System.out.println("2. Відсортувати обрану амуніцію за вартістю");
        System.out.println("3. Відсортувати обрану амуніцію за вагою");
        System.out.println("4. Знайти елементи амуніції, які відповідають заданому діапазону цін");
        System.out.println("5. Знайти елементи амуніції, які відповідають заданому діапазону ваги");
    }
}
