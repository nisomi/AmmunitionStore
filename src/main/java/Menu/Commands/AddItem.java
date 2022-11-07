package Menu.Commands;

import Logger.*;
import Accounts.Seller;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.*;
import Ammunition.Weapons.*;
import Menu.Command;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddItem implements Command {
    private static final Logger logger = Logger.getLogger(AddItem.class.getName());

    private Seller seller;
    private final List<AmmunitionItem> ammunitionItems;
    Scanner scanner = new Scanner(System.in);

    public AddItem(Seller seller, List<AmmunitionItem> ammunitionItems) {
        this.seller = seller;
        this.ammunitionItems = ammunitionItems;
    }

    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);
            Log.setupLogger(logger);
            ShowTypes();
            System.out.print("Ваш вибір:");
            int choice = Integer.parseInt(scanner.nextLine());
            System.out.print("Назва:");
            String name = scanner.nextLine();
            System.out.print("Вага:");
            int weight = Integer.parseInt(scanner.nextLine());
            System.out.print("Ціна:");
            int cost = Integer.parseInt(scanner.nextLine());
            int protection = 0;
            int damage = 0;
            switch (choice) {
                case 1, 2, 3, 4, 5, 6, 7: {
                    System.out.print("Захист:");
                    protection = Integer.parseInt(scanner.nextLine());
                    break;
                }
                case 8, 9, 10, 11: {
                    System.out.print("Сила удару:");
                    damage = Integer.parseInt(scanner.nextLine());
                    break;
                }
            }
            System.out.print("Короткий опис:");
            String description = scanner.nextLine();
            if (cost < seller.getWallet()) {
                seller.setWallet(seller.getWallet() - cost);
                switch (choice) {
                    case 1: {
                        ammunitionItems.add(new Boots(name, weight, cost, protection, description));
                        break;
                    }
                    case 2: {
                        ammunitionItems.add(new Breastplate(name, weight, cost, protection, description));
                        break;
                    }
                    case 3: {
                        ammunitionItems.add(new ChainMail(name, weight, cost, protection, description));
                        break;
                    }
                    case 4: {
                        ammunitionItems.add(new Cloak(name, weight, cost, protection, description));
                        break;
                    }
                    case 5: {
                        ammunitionItems.add(new Gauntlets(name, weight, cost, protection, description));
                        break;
                    }
                    case 6: {
                        ammunitionItems.add(new Helmet(name, weight, cost, protection, description));
                        break;
                    }
                    case 7: {
                        ammunitionItems.add(new Shield(name, weight, cost, protection, description));
                        break;
                    }
                    case 8: {
                        ammunitionItems.add(new Dagger(name, weight, cost, damage, description));
                        break;
                    }
                    case 9: {
                        ammunitionItems.add(new Longbow(name, weight, cost, damage, description));
                        break;
                    }
                    case 10: {
                        ammunitionItems.add(new Staff(name, weight, cost, damage, description));
                        break;
                    }
                    case 11: {
                        ammunitionItems.add(new Sword(name, weight, cost, damage, description));
                        break;
                    }
                    default: {
                        System.out.println("Введіть коректне значення.");
                    }
                }
                logger.log(Level.INFO, "Added new ammunition item");
            } else {
                System.out.println("Недостатньо коштів.");
                logger.log(Level.INFO, "Not enough money to add new ammunition item");
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Хибний ввід");
        }
    }

    private void ShowTypes() {
        System.out.println("Оберіть тип амуніції, яку хочете додати:");
        System.out.println("\t1. Чоботи");
        System.out.println("\t2. Нагрудник");
        System.out.println("\t3. Кольчуга");
        System.out.println("\t4. Плащ");
        System.out.println("\t5. Рукавиці");
        System.out.println("\t6. Шолом");
        System.out.println("\t7. Щит");
        System.out.println("\t8. Кинджал");
        System.out.println("\t9. Довгий лук");
        System.out.println("\t10. Посох");
        System.out.println("\t11. Меч");
    }
}
