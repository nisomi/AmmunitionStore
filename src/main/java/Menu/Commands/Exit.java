package Menu.Commands;

import Accounts.Account;
import Accounts.Knight;
import Ammunition.AmmunitionItem;
import Menu.Command;
import Logger.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exit implements Command {
    private List<AmmunitionItem> ammunitionItems;
    private List<Account> accounts;
    Account account;
    private static final Logger logger = Logger.getLogger(Exit.class.getName());

    public Exit(){
        this.account = null;
        this.ammunitionItems = null;
        this.accounts=null;
    }

    public Exit(List<AmmunitionItem> ammunitionItems, Account account,List<Account> accounts) {
        this.account = account;
        this.ammunitionItems = ammunitionItems;
        this.accounts=accounts;
    }

    public void execute() {
        Log.setupLogger(logger);
        if (account != null){
            if (account instanceof Knight){
                new SaveBoughtItems((Knight) account).execute();
            }
            new SaveItemsToFile(ammunitionItems).execute();
            new SaveAccountsToFile(accounts).execute();
            logger.log(Level.INFO,"Successfully exited from account "+account.getType(), account.getClass());
        }
        else  {
            logger.log(Level.INFO,"Successfully exited");
            System.exit(0);
        }
    }
}
