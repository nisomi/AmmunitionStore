package Menu.Commands;

import Accounts.Account;
import Accounts.Knight;
import Ammunition.AmmunitionItem;
import Menu.Command;
import Menu.Storage;

import java.util.ArrayList;
import java.util.List;

public class Login implements Command {

    private String login;
    private String password;

    public Login(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public void execute() {
        List<Account> accounts = new ArrayList<>();
        List<AmmunitionItem> ammunition = new ArrayList<>();
        new GetAccounts(accounts).execute();
        new GetItems(ammunition).execute();
        for (Account account: accounts) {
            if (account.getLogin().equals(login) && account.getPassword().equals(password)) {
                new Storage(account, ammunition);
                if (account instanceof Knight){
                    new GetItems(((Knight) Storage.getAccount()).getAmmunitionItems(), account).execute();
                }
            }
        }
    }
}
