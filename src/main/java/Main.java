import Accounts.Account;
import Menu.Menu;
import Logger.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        Log.setupLogger(logger);
        List<Account> accounts = new ArrayList<>();
        Menu menu = new Menu(accounts);
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO, "Showed start menu");
        while (true) {
            menu.printOptions();
            System.out.print("Ваш вибір:");
            try {
                int choice = scanner.nextInt();
                if (choice <= menu.getSize())
                    menu.makeChoice(choice);
                else
                    System.out.println("Такої команди немає");
            } catch (InputMismatchException e) {
                System.out.println("Хибний ввід");
                scanner.nextLine();
            }
        }

    }
}
