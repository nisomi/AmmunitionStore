package Menu.Commands;

import Ammunition.AmmunitionItem;
import Menu.Command;
import Logger.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortItemsByCost implements Command {
    private final List<AmmunitionItem> ammunitionItemList;
    private static final Logger logger = Logger.getLogger(SortItemsByCost.class.getName());

    public SortItemsByCost(List<AmmunitionItem> ammunitionItemList) {
        this.ammunitionItemList = ammunitionItemList;
    }

    public void execute() {
        Log.setupLogger(logger);
        ammunitionItemList.sort(new AmmunitionItem.CostComparator());
        for (AmmunitionItem ammunition : ammunitionItemList) {
            System.out.println(ammunition);
        }
        logger.log(Level.INFO, "sorted items by cost");

    }
}
