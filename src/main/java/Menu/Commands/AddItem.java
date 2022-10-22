package Menu.Commands;

import Accounts.Knight;
import Accounts.Seller;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.*;
import Ammunition.Weapons.*;
import Menu.Command;

import java.util.List;
import java.util.Scanner;

public class AddItem implements Command {
    private Seller seller;
    private final List<AmmunitionItem> ammunitionItems;
    Scanner scanner = new Scanner(System.in);

    public AddItem(Seller seller, List<AmmunitionItem> ammunitionItems) {
        this.seller = seller;
        this.ammunitionItems = ammunitionItems;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        ShowTypes();
        System.out.print("Ваш вибір:");
        int choice = scanner.nextInt();
        String name = GetName();
        int weight = GetWeight();
        int cost = GetCost();
        int protection = 0;
        int damage = 0;
        switch (choice) {
            case 1, 2, 3, 4, 5, 6, 7: {
                protection = GetProtection();
                break;
            }
            case 8, 9, 10, 11: {
                damage = GetDamage();
                break;
            }
        }
        String description = GetDescription();
        if (cost < seller.getWallet()) {
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
        } else {
            System.out.println("Недостатньо коштів.");
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

    private String GetName(){
        System.out.println("Назва:");
        return scanner.nextLine();
    }

    private int GetWeight(){
        System.out.println("Вага:");
        return Integer.parseInt(scanner.nextLine());
    }

    private int GetCost(){
        System.out.println("Ціна:");
        return Integer.parseInt(scanner.nextLine());
    }

    private String GetDescription(){
        System.out.println("Короткий опис:");
        return scanner.nextLine();
    }

    private int GetProtection(){
        System.out.println("Захист:");
        return Integer.parseInt(scanner.nextLine());
    }

    private int GetDamage(){
        System.out.println("Сила удару:");
        return Integer.parseInt(scanner.nextLine());
    }

}
