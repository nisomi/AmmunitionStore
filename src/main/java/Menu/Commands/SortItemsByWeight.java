package Menu.Commands;

import Ammunition.AmmunitionItem;
import Menu.Command;
import Logger.*;
import javafx.scene.control.TextArea;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortItemsByWeight implements Command {
    private final List<AmmunitionItem> ammunitionItemList;
    private static final Logger logger = Logger.getLogger(SortItemsByWeight.class.getName());
    private TextArea textArea;
    public SortItemsByWeight(List<AmmunitionItem> ammunitionItemList, TextArea textArea) {
        this.ammunitionItemList = ammunitionItemList;
        this.textArea = textArea;
    }

    public void execute() {
        Log.setupLogger(logger);
        ammunitionItemList.sort(new AmmunitionItem.WeightComparator());
        for (AmmunitionItem ammunition : ammunitionItemList) {
            if (textArea!=null){
                textArea.appendText(ammunition+"\n");
            }
        }
        logger.log(Level.INFO, "sorted items by weight");
    }
}
