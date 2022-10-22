package Menu.Commands;

import Ammunition.AmmunitionItem;
import Menu.Command;

import java.util.List;

public class SortItemsByCost implements Command {
    private final List<AmmunitionItem> ammunitionItemList;

    public SortItemsByCost(List<AmmunitionItem> ammunitionItemList) {
        this.ammunitionItemList = ammunitionItemList;
    }

    public void execute() {
        ammunitionItemList.sort(new AmmunitionItem.CostComparator());
        for (AmmunitionItem ammunition : ammunitionItemList) {
            System.out.println(ammunition);
        }
    }
}
