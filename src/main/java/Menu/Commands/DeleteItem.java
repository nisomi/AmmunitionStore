package Menu.Commands;

import Ammunition.AmmunitionItem;
import Menu.Command;

import java.util.List;
import java.util.Scanner;

public class DeleteItem implements Command {
    private List<AmmunitionItem> ammunitionItemList;

    public DeleteItem(List<AmmunitionItem> ammunitionItemList ) {
        this.ammunitionItemList = ammunitionItemList;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (int i = 0; i < ammunitionItemList.size(); i++) {
                System.out.println(i+1 + "." + ammunitionItemList.get(i));
            }
            System.out.println("Введіть номер елементу амуніції, який хочете видалити:");
            int key = scanner.nextInt();
            if(key >= 0 && key <= ammunitionItemList.size()) {
                ammunitionItemList.remove(key - 1);
                break;
            }
            else System.out.println("Введіть коректне значення");
        }
    }
}
