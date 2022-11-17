package Menu.Commands;

import Accounts.*;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.*;
import Ammunition.Weapons.Dagger;
import Ammunition.Weapons.Longbow;
import Ammunition.Weapons.Staff;
import Ammunition.Weapons.Sword;
import DataBase.DataBase;
import Menu.Command;
import Logger.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Logger.ViaMail.sendMessage;

public class GetItems implements Command {
    List<AmmunitionItem> ammunitionItems;
    Account account;
    private static final Logger logger = Logger.getLogger(GetItems.class.getName());

    public GetItems(List<AmmunitionItem> ammunitionItems, Account account) {
        this.ammunitionItems = ammunitionItems;
        this.account = account;
    }

    public GetItems(List<AmmunitionItem> ammunitionItems) {
        this.ammunitionItems = ammunitionItems;
    }

    public void execute() {
        PreparedStatement statement;
        ResultSet resultSet;

        Log.setupLogger(logger);
        try {
            if (account instanceof Knight) {
                String query = "SELECT * FROM ammunition where userID = (SELECT userID FROM users where login = '"+account.getLogin()+"' )";
                statement = DataBase.getInstance().prepareStatement(query);
                try {
                    resultSet = statement.executeQuery(query);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                String query = "SELECT * FROM ammunition where userID is null";
                statement = DataBase.getInstance().prepareStatement(query);
                try {
                    resultSet = statement.executeQuery(query);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            while (resultSet.next()){
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                int weight = resultSet.getInt("weight");
                int cost = resultSet.getInt("cost");
                int protection = resultSet.getInt("protection");
                int damage = resultSet.getInt("damage");
                String description = resultSet.getString("description");
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
            logger.log(Level.INFO,"Added all items");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"database with accounts not found",e);
            sendMessage("Critical error occurred: " + e + "\nDB with accounts not found");
            new Exit().execute();
        }
    }

}
