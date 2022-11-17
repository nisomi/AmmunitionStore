package Menu.Commands;

import Accounts.Account;
import Accounts.Knight;
import Accounts.Seller;
import Ammunition.AmmunitionItem;
import DataBase.DataBase;
import Menu.Command;
import Logger.*;
import javafx.scene.control.TextArea;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReturnItem implements Command {
    private List<Account> accounts;
    private Knight knight;
    private List<AmmunitionItem> ammunitionItemList;
    int key;
    private TextArea textArea;
    private static final Logger logger = Logger.getLogger(ReturnItem.class.getName());

    public ReturnItem(Knight knight, List<AmmunitionItem> ammunitionItemList, List<Account> accounts, int key, TextArea textArea) {
        this.knight = knight;
        this.ammunitionItemList = ammunitionItemList;
        this.accounts = accounts;
        this.key = key;
        this.textArea = textArea;
    }

    public void execute() {
        Log.setupLogger(logger);
        PreparedStatement statement1, statement2,statement3, statement4;
        int itemCost = knight.getAmmunitionItems().get(key-1).getCost()/2;
        for (Account account: accounts) {
            if (account instanceof Seller) {
                if (account.getWallet() >= itemCost) {

                    String query1 = "UPDATE ammunition SET userID = null WHERE name = '"+knight.getAmmunitionItems().get(key-1).getName()+
                            "' AND weight = "+knight.getAmmunitionItems().get(key-1).getWeight()+
                            " AND cost = "+knight.getAmmunitionItems().get(key-1).getCost()+
                            " AND description = '"+knight.getAmmunitionItems().get(key-1).getDescription()+
                            "' AND type = '"+knight.getAmmunitionItems().get(key-1).getType()+"'";
                    String query2 ="UPDATE ammunition SET cost = " + itemCost+ " WHERE" +
                           " name = '"+knight.getAmmunitionItems().get(key-1).getName()+
                            "' AND weight = "+knight.getAmmunitionItems().get(key-1).getWeight()+
                            " AND cost = "+knight.getAmmunitionItems().get(key-1).getCost()+
                            " AND description = '"+knight.getAmmunitionItems().get(key-1).getDescription()+
                            "' AND type = '"+knight.getAmmunitionItems().get(key-1).getType()+"'";
                    ;
                    String query3 = "UPDATE users SET wallet = wallet + "+knight.getAmmunitionItems().get(key-1).getCost()+" WHERE login = '"+knight.getLogin()+"'";
                    String query4 = "UPDATE users SET wallet = wallet - "+knight.getAmmunitionItems().get(key-1).getCost()+" WHERE login = '"+account.getLogin()+"'";
                    statement1 = DataBase.getInstance().prepareStatement(query1);
                    statement2 = DataBase.getInstance().prepareStatement(query2);
                    statement3 = DataBase.getInstance().prepareStatement(query3);
                    statement4 = DataBase.getInstance().prepareStatement(query4);

                    try {
                        statement1.executeUpdate(query1);
                        statement2.executeUpdate(query2);
                        statement3.executeUpdate(query3);
                        statement4.executeUpdate(query4);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    account.setWallet(account.getWallet() - itemCost);
                    knight.setWallet(knight.getWallet() + itemCost);

                    knight.getAmmunitionItems().get(key-1).setCost(itemCost);
                    ammunitionItemList.add(knight.getAmmunitionItems().get(key-1));
                    knight.deleteItem(key - 1);
                    logger.log(Level.INFO, "Knight return ammunition item");
                    if (textArea!=null){
                        textArea.setText("Успішно повернуто.");
                    }
                }
                else{
                    if (textArea!=null){
                        textArea.setText("Повернути неможливо.");
                    }
                    logger.log(Level.INFO, "ammunition item cannot be returned");
                }
            }
        }
    }


}
