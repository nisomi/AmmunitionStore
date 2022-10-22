import Accounts.Account;
import Menu.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        Menu menu = new Menu(accounts);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            menu.printOptions();
            System.out.print("Ваш вибір:");
            int choice = scanner.nextInt();
            if (choice <= menu.getSize())
                menu.makeChoice(choice);
        }
    }
}
