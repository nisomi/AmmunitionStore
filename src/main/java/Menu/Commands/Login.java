package Menu.Commands;

import Accounts.Account;
import Ammunition.AmmunitionItem;
import Menu.Command;
import Menu.Menu;
import Logger.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login implements Command {
    List<Account> accounts;

    public Login(List<Account> accounts) {
        this.accounts = accounts;
    }
    private static final Logger logger = Logger.getLogger(Login.class.getName());

    public void execute() {
        Log.setupLogger(logger);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть логін:");
        String login = scanner.nextLine();
        System.out.println("Введіть пароль:");
        String password = scanner.nextLine();
        for (Account account: accounts) {
            if (account.getLogin().equals(login) && account.getPassword().equals(password)) {
                List<AmmunitionItem> ammunition = new ArrayList<>();
                Menu menu = new Menu(account, ammunition, accounts);
                logger.log(Level.INFO,"Successfully logged in as "+account.getType(), account.getClass());
                while (true) {
                    menu.printOptions();
                    System.out.print("Ваш вибір:");
                    int choice = scanner.nextInt();
                    if (choice <= menu.getSize())
                        menu.makeChoice(choice);
                    if (choice == menu.getSize()){
                        return;
                    }
                }
            }
        }
        System.out.println("Такого користувача немає");
        logger.log(Level.WARNING,"Trying to login with wrong login or password");
    }
}
