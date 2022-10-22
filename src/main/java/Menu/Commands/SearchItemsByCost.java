package Menu.Commands;

import Accounts.Account;
import Ammunition.AmmunitionItem;
import Menu.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchItemsByCost implements Command {
    private Account account;
    private final List<AmmunitionItem> ammunitionItemList;

    public SearchItemsByCost(List<AmmunitionItem> ammunitionItemList) {
        this.ammunitionItemList = ammunitionItemList;
    }

    public void execute() {
        ammunitionItemList.sort(new AmmunitionItem.CostComparator());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть мінімальне та максимальне значення вартості:");
        for (AmmunitionItem ammunition: searchAmmunitionByCost(scanner.nextInt(),scanner.nextInt())) {
            System.out.println(ammunition);
        }
    }

    public List<AmmunitionItem> searchAmmunitionByCost(int start, int end){
        List<AmmunitionItem> AmmunitionInRange = new ArrayList<>();
        ammunitionItemList.sort(new AmmunitionItem.CostComparator());
        for (AmmunitionItem ammunition : ammunitionItemList) {
            if(ammunition.getCost() >= start && ammunition.getCost() <= end){
                AmmunitionInRange.add(ammunition);
            }
        }
        return AmmunitionInRange;
    }
}
