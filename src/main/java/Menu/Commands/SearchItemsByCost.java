package Menu.Commands;

import Accounts.Account;
import Ammunition.AmmunitionItem;
import Menu.Command;
import Logger.*;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchItemsByCost implements Command {
    private Account account;
    private static final Logger logger = Logger.getLogger(SearchItemsByCost.class.getName());
    private final List<AmmunitionItem> ammunitionItemList;
    int min,max;
    private TextArea textArea;

    public SearchItemsByCost(List<AmmunitionItem> ammunitionItemList, int min, int max, TextArea textArea) {
        this.ammunitionItemList = ammunitionItemList;
        this.min=min;
        this.max=max;
        this.textArea = textArea;
    }

    public void execute() {
        Log.setupLogger(logger);
        ammunitionItemList.sort(new AmmunitionItem.CostComparator());
        for (AmmunitionItem ammunition: searchAmmunitionByCost(min,max)) {
            if (textArea!=null){
                textArea.appendText(ammunition+"\n");
            }

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
