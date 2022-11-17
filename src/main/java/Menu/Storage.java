package Menu;

import Accounts.Account;
import Ammunition.AmmunitionItem;

import java.util.List;


public class Storage {
    public static Account account = null;
    public static List<AmmunitionItem> ammunitionItems = null;

    public Storage(Account account, List<AmmunitionItem> ammunitionItems) {
        Storage.account = account;
        Storage.ammunitionItems = ammunitionItems;
    }

    public static Account getAccount() {
        return account;
    }

    public static List<AmmunitionItem> getAmmunitionItems() {
        return ammunitionItems;
    }
}
