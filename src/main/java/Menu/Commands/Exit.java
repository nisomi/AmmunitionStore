package Menu.Commands;

import Accounts.Account;
import Accounts.Knight;
import Ammunition.AmmunitionItem;
import Menu.Command;

import java.util.List;

public class Exit implements Command {
    private List<AmmunitionItem> ammunitionItems;
    private List<Account> accounts;
    Knight knight;

    public Exit(){
        this.knight = null;
        this.ammunitionItems = null;
        this.accounts=null;
    }

    public Exit(List<AmmunitionItem> ammunitionItems, Knight knight,List<Account> accounts) {
        this.knight = knight;
        this.ammunitionItems = ammunitionItems;
        this.accounts=accounts;
    }

    public Exit(List<AmmunitionItem> ammunitionItems,List<Account> accounts) {
        this.ammunitionItems = ammunitionItems;
        this.accounts=accounts;
    }

    public void execute() {
        if (knight != null){
            new SaveBoughtItems(knight).execute();
        }
        if (ammunitionItems !=null) {
            new SaveItemsToFile(ammunitionItems).execute();
            new SaveAccountsToFile(accounts).execute();
        }
        System.exit(0);
    }
}
