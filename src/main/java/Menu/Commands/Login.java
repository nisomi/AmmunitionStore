package Menu.Commands;

import Accounts.Account;
import Ammunition.AmmunitionItem;
import Menu.Command;
import Menu.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login implements Command {
    List<Account> accounts;

    public Login(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть логін:");
        String login = scanner.nextLine();
        System.out.println("Введіть пароль:");
        String password = scanner.nextLine();
        for (Account account: accounts) {
            if (account.getLogin().equals(login) && account.getPassword().equals(password)) {
                List<AmmunitionItem> ammunition = new ArrayList<>();
                Menu menu = new Menu(account, ammunition, accounts);
                while (true) {
                    menu.printOptions();
                    System.out.print("Ваш вибір:");
                    int choice = scanner.nextInt();
                    if (choice <= menu.getSize())
                        menu.makeChoice(choice);
                }
            }
        }
        System.out.println("Такого користувача немає");
    }
}
