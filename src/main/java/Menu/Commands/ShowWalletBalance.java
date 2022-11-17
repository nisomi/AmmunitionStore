package Menu.Commands;

import Accounts.Account;
import Menu.Command;
import Logger.*;
import Menu.Storage;
import javafx.scene.control.TextArea;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowWalletBalance implements Command {
    private Account account;
    private TextArea textArea;
    private static final Logger logger = Logger.getLogger(ShowWalletBalance.class.getName());

    public ShowWalletBalance(Account account, TextArea textArea) {
        this.account = account;
        this.textArea = textArea;
    }

    public void execute() {
        Log.setupLogger(logger);
        if (textArea != null)
            textArea.setText("Ваш баланс : " + Storage.getAccount().getWallet());
        logger.log(Level.INFO, "showed wallet balance");

    }
}
