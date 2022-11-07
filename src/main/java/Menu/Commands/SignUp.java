package Menu.Commands;

import Accounts.*;
import Ammunition.AmmunitionItem;
import Menu.Command;
import Menu.Menu;
import Logger.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUp implements Command {
    List<Account> accounts;
    private static final Logger logger = Logger.getLogger(SignUp.class.getName());

    public SignUp(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void execute() {
        Log.setupLogger(logger);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть логін:");
        try {
            String login = scanner.nextLine();
            if (!CheckIfExist(login)) {
                System.out.println("Введіть пароль:");
                String password = scanner.nextLine();
                System.out.println("Доступні кошти:");
                int money = scanner.nextInt();
                accounts.add(new Knight(login, password, money));
                List<AmmunitionItem> ammunition = new ArrayList<>();
                Menu menu = new Menu(accounts.get(accounts.size() - 1), ammunition, accounts);
                logger.log(Level.INFO, "Signed up successfully as "+accounts.get(accounts.size() - 1).getType(), accounts.get(accounts.size() - 1).getClass());
                while (true) {
                    menu.printOptions();
                    System.out.print("Ваш вибір:");
                    int choice = scanner.nextInt();
                    if (choice <= menu.getSize())
                        menu.makeChoice(choice);
                    if (choice == menu.getSize()) {
                        return;
                    }
                }
            } else {
                System.out.println("Такий користувач вже існує, спробуйте ще раз");
            }
        }catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Хибний ввід");
        }

    }
    private boolean CheckIfExist(String login){
        for (Account account : accounts) {
            if (account.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }
}
