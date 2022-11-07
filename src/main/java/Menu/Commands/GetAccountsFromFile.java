package Menu.Commands;

import Accounts.Account;
import Accounts.Knight;
import Accounts.Seller;
import Menu.Command;
import Logger.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Logger.ViaMail.sendMessage;

public class GetAccountsFromFile implements Command {
    List<Account> accounts;
    private static final Logger logger = Logger.getLogger(GetAccountsFromFile.class.getName());

    public GetAccountsFromFile(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void execute() {
        Log.setupLogger(logger);
        try {
            String FilePath = "E:\\2 курс\\1 сем\\ПП\\Accounts.txt";
            File file = new File(FilePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("_");
                String type = data[0];
                String login = data[1];
                String password = data[2];
                int money = Integer.parseInt(data[3]);
                switch (type) {
                    case "Лицар": {
                        accounts.add(new Knight(login,password,money));
                        break;
                    }
                    case "Торговець": {
                        accounts.add(new Seller(login,password,money));
                        break;
                    }

                }
            }
            scanner.close();
            logger.log(Level.INFO,"Added all accounts");
        }
        catch (FileNotFoundException e){
            System.out.println("Файл не знайдено");
            logger.log(Level.SEVERE,"File with accounts not found",e);
            sendMessage("Critical error occurred: " + e + "\nFile with accounts not found");
            new Exit().execute();
        }
    }
}
