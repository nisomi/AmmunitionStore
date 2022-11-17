package Menu.Commands;

import Ammunition.AmmunitionItem;
import Menu.Command;
import Logger.*;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchItemsByWeight implements Command {
    private final List<AmmunitionItem> ammunitionItemList;
    private static final Logger logger = Logger.getLogger(SearchItemsByWeight.class.getName());
    int min,max;
    private TextArea textArea;
    public SearchItemsByWeight(List<AmmunitionItem> ammunitionItemList, int min, int max, TextArea textArea) {
        this.ammunitionItemList = ammunitionItemList;
        this.min= min;
        this.max=max;
        this.textArea = textArea;
    }

    public void execute() {
        Log.setupLogger(logger);

        ammunitionItemList.sort(new AmmunitionItem.WeightComparator());

        for (AmmunitionItem ammunition : searchAmmunitionByWeight(min, max)) {
            if (textArea!=null){
                textArea.appendText(ammunition+"\n");
            }
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
