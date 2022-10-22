package Menu.Commands;

import Accounts.Account;
import Menu.Command;

public class ShowWalletBalance implements Command {
    private Account account;

    public ShowWalletBalance(Account account) {
        this.account = account;
    }

    public void execute() {
        System.out.println("Ваш баланс: "+ account.getWallet());
    }
}
