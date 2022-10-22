package Menu.Commands;

import Accounts.Account;
import Accounts.Knight;
import Accounts.Seller;
import Menu.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class GetAccountsFromFile implements Command {
    List<Account> accounts;

    public GetAccountsFromFile(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void execute() {
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
        }
        catch (FileNotFoundException e){
            System.out.println("Файл не знайдено");
        }
    }
}
