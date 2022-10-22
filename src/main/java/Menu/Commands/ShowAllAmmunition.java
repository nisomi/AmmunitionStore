package Menu.Commands;

import Ammunition.AmmunitionItem;
import Menu.Command;

import java.util.List;
import java.util.Scanner;

public class ShowAllAmmunition implements Command {
    private final List<AmmunitionItem> ammunitionItems;

    public ShowAllAmmunition(List<AmmunitionItem> ammunitionItems) {
        this.ammunitionItems = ammunitionItems;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        ShowActions();
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                for (AmmunitionItem ammunition: ammunitionItems) {
                    System.out.println(ammunition);
                }
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
