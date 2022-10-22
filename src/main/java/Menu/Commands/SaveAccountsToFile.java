package Menu.Commands;

import Accounts.Account;
import Menu.Command;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SaveAccountsToFile implements Command {
    List<Account> accounts;

    public SaveAccountsToFile(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void execute() {
        try {
            String FilePath = "E:\\2 курс\\1 сем\\ПП\\Accounts.txt";
            File file = new File(FilePath);
            PrintWriter pw = new PrintWriter(file);
            for (Account account : accounts) {
                pw.println(account.getType() + "_" + account.getLogin() + "_" + account.getPassword()+"_"+account.getWallet());
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Файл не знайдено");
        }
    }
}
