package Menu.Commands;

import Accounts.Account;
import Menu.Command;
import Logger.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowWalletBalance implements Command {
    private Account account;
    private static final Logger logger = Logger.getLogger(ShowWalletBalance.class.getName());

    public ShowWalletBalance(Account account) {
        this.account = account;
    }

    public void execute() {
        Log.setupLogger(logger);
        System.out.println("Ваш баланс: "+ account.getWallet());
        logger.log(Level.INFO, "showed wallet balance");

    }
}
