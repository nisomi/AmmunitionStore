package Menu.Commands;

import Accounts.Account;
import Ammunition.AmmunitionItem;
import Menu.Command;
import Logger.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchItemsByCost implements Command {
    private Account account;
    private static final Logger logger = Logger.getLogger(SearchItemsByCost.class.getName());
    private final List<AmmunitionItem> ammunitionItemList;

    public SearchItemsByCost(List<AmmunitionItem> ammunitionItemList) {
        this.ammunitionItemList = ammunitionItemList;
    }

    public void execute() {
        Log.setupLogger(logger);
        ammunitionItemList.sort(new AmmunitionItem.CostComparator());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть мінімальне та максимальне значення вартості:");
        for (AmmunitionItem ammunition: searchAmmunitionByCost(scanner.nextInt(),scanner.nextInt())) {
            System.out.println(ammunition);
        }
        logger.log(Level.INFO, "searched items by cost");
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
