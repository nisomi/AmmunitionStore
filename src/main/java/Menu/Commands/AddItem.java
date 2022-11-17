package Menu.Commands;

import DataBase.DataBase;
import Logger.*;
import Accounts.Seller;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.*;
import Ammunition.Weapons.*;
import Menu.Command;
import javafx.scene.control.TextArea;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddItem implements Command {
    private static final Logger logger = Logger.getLogger(AddItem.class.getName());
    private Seller seller;
    private final List<AmmunitionItem> ammunitionItems;
    private String name;
    private int weight;
    private int cost;
    private int protection;
    private int damage;
    private String description;

    private int choice;
    private TextArea textArea;
    public AddItem(Seller seller, List<AmmunitionItem> ammunitionItems, String name,
                   int weight, int cost, int protection, int damage, String description, int choice, TextArea textArea) {
        this.seller = seller;
        this.ammunitionItems = ammunitionItems;
        this.name = name;
        this.weight = weight;
        this.cost = cost;
        this.protection = protection;
        this.damage = damage;
        this.description = description;
        this.choice=choice;
        this.textArea= textArea;
    }

    public void execute() {
        PreparedStatement statement1, statement2;
        Log.setupLogger(logger);
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
            }
            String query1 = "INSERT INTO ammunition (name,weight,cost,description,type,damage,protection, userID) VALUES ('"+name+"' ,"+weight+" ,"+cost+" ,'"+description+"' ,'"+ammunitionItems.get(ammunitionItems.size()-1).getType()+"' ,"+damage+" ,"+protection+", "+null+")";
            statement1 = DataBase.getInstance().prepareStatement(query1);
            String query2 = "UPDATE users SET wallet = wallet - "+cost+" WHERE user_type = 'Торговець' ";
            statement2 = DataBase.getInstance().prepareStatement(query2);
            try {
                statement1.executeUpdate(query1);
                statement2.executeUpdate(query2);
                statement1.close();
                statement2.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (textArea!=null)
            {
                textArea.setText("Успішно додано.");
            }
            logger.log(Level.INFO, "Added new ammunition item");
        } else {
            if (textArea!=null)
            {
                textArea.setText("Недостатньо коштів.");
            }

            logger.log(Level.INFO, "Not enough money to add new ammunition item");
        }
    }

}
