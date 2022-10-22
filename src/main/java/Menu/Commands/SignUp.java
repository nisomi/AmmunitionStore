package Menu.Commands;

import Accounts.*;
import Ammunition.AmmunitionItem;
import Menu.Command;
import Menu.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SignUp implements Command {
    List<Account> accounts;

    public SignUp(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть логін:");
        String login = scanner.nextLine();
        if (!CheckIfExist(login)) {
            System.out.println("Введіть пароль:");
            String password = scanner.nextLine();

            System.out.println("Доступні кошти:");
            int money = Integer.parseInt(scanner.nextLine());

            accounts.add(new Seller(login, password, money));
            new SaveAccountsToFile(accounts).execute();
            List<AmmunitionItem> ammunition = new ArrayList<>();
            Menu menu = new Menu(accounts.get(accounts.size() - 1), ammunition, accounts);
            while (true) {
                menu.printOptions();
                System.out.print("Ваш вибір:");
                int choise = scanner.nextInt();
                if (choise <= menu.getSize())
                    menu.makeChoice(choise);
            }
        } else {
            System.out.println("Такий користувач вже існує, спробуйте ще раз");
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
