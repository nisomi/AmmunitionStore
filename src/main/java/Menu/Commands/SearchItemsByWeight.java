package Menu.Commands;

import Ammunition.AmmunitionItem;
import Menu.Command;
import Logger.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchItemsByWeight implements Command {
    private final List<AmmunitionItem> ammunitionItemList;
    private static final Logger logger = Logger.getLogger(SearchItemsByWeight.class.getName());

    public SearchItemsByWeight(List<AmmunitionItem> ammunitionItemList) {
        this.ammunitionItemList = ammunitionItemList;
    }

    public void execute() {
        Log.setupLogger(logger);

        ammunitionItemList.sort(new AmmunitionItem.WeightComparator());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть мінімальне та максимальне значення ваги:");
        for (AmmunitionItem ammunition : searchAmmunitionByWeight(scanner.nextInt(), scanner.nextInt())) {
            System.out.println(ammunition);
        }
        logger.log(Level.INFO, "searched items by weight");
    }

    public List<AmmunitionItem> searchAmmunitionByWeight(int start, int end){
        List<AmmunitionItem> AmmunitionInRange = new ArrayList<>();
        ammunitionItemList.sort(new AmmunitionItem.CostComparator());
        for (AmmunitionItem ammunition : ammunitionItemList) {
            if(ammunition.getWeight() >= start && ammunition.getWeight() <= end){
                AmmunitionInRange.add(ammunition);
            }
        }
        return AmmunitionInRange;
    }
}
