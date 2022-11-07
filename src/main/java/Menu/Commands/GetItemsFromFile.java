package Menu.Commands;

import Accounts.*;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.*;
import Ammunition.Weapons.Dagger;
import Ammunition.Weapons.Longbow;
import Ammunition.Weapons.Staff;
import Ammunition.Weapons.Sword;
import Menu.Command;
import Logger.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Logger.ViaMail.sendMessage;

public class GetItemsFromFile implements Command {
    List<AmmunitionItem> ammunitionItems;
    Account account;
    private static final Logger logger = Logger.getLogger(GetItemsFromFile.class.getName());

    public GetItemsFromFile(List<AmmunitionItem> ammunitionItems, Account account) {
        this.ammunitionItems = ammunitionItems;
        this.account = account;
    }

    public GetItemsFromFile(List<AmmunitionItem> ammunitionItems) {
        this.ammunitionItems = ammunitionItems;
    }

    public void execute() {
        Log.setupLogger(logger);
        try {
            String FilePath;
            if (account instanceof Knight) {
                FilePath = "E:\\2 курс\\1 сем\\ПП\\Ammunition\\ammunitionFor" + account.getLogin() + ".txt";
            }
            else {
                FilePath = "E:\\2 курс\\1 сем\\ПП\\Knight.txt";
            }
            File file = new File(FilePath);
            if(!file.exists()){
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("_");
                String type = data[0];
                String name = data[1];
                int weight = Integer.parseInt(data[2]);
                int cost = Integer.parseInt(data[3]);
                int protection = Integer.parseInt(data[4]);
                int damage = Integer.parseInt(data[4]);
                String description = data[5];
                switch (type) {
                    case "Чоботи": {
                        ammunitionItems.add(new Boots(name, weight, cost, protection, description));
                        break;
                    }
                    case "Нагрудник": {
                        ammunitionItems.add(new Breastplate(name, weight, cost, protection, description));
                        break;
                    }
                    case "Кольчуга": {
                        ammunitionItems.add(new ChainMail(name, weight, cost, protection, description));
                        break;
                    }
                    case "Плащ": {
                        ammunitionItems.add(new Cloak(name, weight, cost, protection, description));
                        break;
                    }
                    case "Рукавиці": {
                        ammunitionItems.add(new Gauntlets(name, weight, cost, protection, description));
                        break;
                    }
                    case "Шолом": {
                        ammunitionItems.add(new Helmet(name, weight, cost, protection, description));
                        break;
                    }
                    case "Щит": {
                        ammunitionItems.add(new Shield(name, weight, cost, protection, description));
                        break;
                    }
                    case "Кинджал": {
                        ammunitionItems.add(new Dagger(name, weight, cost, damage, description));
                        break;
                    }
                    case "Довгий лук": {
                        ammunitionItems.add(new Longbow(name, weight, cost, damage, description));
                        break;
                    }
                    case "Посох": {
                        ammunitionItems.add(new Staff(name, weight, cost, damage, description));
                        break;
                    }
                    case "Меч": {
                        ammunitionItems.add(new Sword(name, weight, cost, damage, description));
                        break;
                    }
                }
            }
            scanner.close();
            logger.log(Level.INFO,"Added all items");
        }
        catch (IOException e){
            System.out.println("Файл не знайдено");
            logger.log(Level.SEVERE,"File with ammunition not found",e);
            sendMessage("Critical error occurred: " + e + "\nFile with ammunition not found");
            new Exit().execute();
        }
    }
}
