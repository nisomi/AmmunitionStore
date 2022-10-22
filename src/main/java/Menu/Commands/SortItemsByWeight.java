package Menu.Commands;

import Ammunition.AmmunitionItem;
import Menu.Command;

import java.util.List;

public class SortItemsByWeight implements Command {
    private final List<AmmunitionItem> ammunitionItemList;

    public SortItemsByWeight(List<AmmunitionItem> ammunitionItemList) {
        this.ammunitionItemList = ammunitionItemList;
    }

    public void execute() {
        ammunitionItemList.sort(new AmmunitionItem.WeightComparator());
        for (AmmunitionItem ammunition : ammunitionItemList) {
            System.out.println(ammunition);
        }
    }
}
