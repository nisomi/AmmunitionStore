package Menu.Commands;

import Accounts.Account;
import Menu.Command;
import Logger.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Logger.ViaMail.sendMessage;

public class SaveAccountsToFile implements Command {
    List<Account> accounts;
    private static final Logger logger = Logger.getLogger(SaveAccountsToFile.class.getName());

    public SaveAccountsToFile(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void execute() {
        Log.setupLogger(logger);

        try {
            String FilePath = "E:\\2 курс\\1 сем\\ПП\\Accounts.txt";
            File file = new File(FilePath);
            PrintWriter pw = new PrintWriter(file);
            for (Account account : accounts) {
                pw.println(account.getType() + "_" + account.getLogin() + "_" + account.getPassword()+"_"+account.getWallet());
            }
            logger.log(Level.INFO, "all accounts successfully saved");
            pw.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "file not found");
            System.out.println("Файл не знайдено");
            sendMessage("Critical error occurred: " + e + "\nFile with accounts not found");
            new Exit().execute();
        }
    }
}
