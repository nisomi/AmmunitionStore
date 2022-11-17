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

public class BuyItem implements Command {
    private static final Logger logger = Logger.getLogger(BuyItem.class.getName());

    private List<Account> accounts;
    private Knight knight;
    private List<AmmunitionItem> ammunitionItemList;
    private TextArea textArea;
    int key;

    public BuyItem(Knight knight, List<AmmunitionItem> ammunitionItemList, List<Account> accounts, int key, TextArea textArea) {
        this.knight = knight;
        this.ammunitionItemList = ammunitionItemList;
        this.accounts = accounts;
        this.key = key;
        this.textArea = textArea;
    }

    public void execute() {
        PreparedStatement statement1, statement2, statement3;
        Log.setupLogger(logger);
        int itemCost = ammunitionItemList.get(key-1).getCost();

        for (Account account: accounts) {
            if (account instanceof Seller){
                if (knight.getWallet()>=itemCost) {
                    if (!knight.checkIfAlreadySelected(ammunitionItemList.get(key - 1))) {
                        String query1 = "UPDATE ammunition SET userID = (SELECT userID FROM users WHERE login = '"+knight.getLogin()+"') " +
                                " WHERE name = '"+ammunitionItemList.get(ammunitionItemList.size()-1).getName()+
                                "' AND weight = "+ammunitionItemList.get(ammunitionItemList.size()-1).getWeight()+
                                " AND cost = "+ammunitionItemList.get(ammunitionItemList.size()-1).getCost()+
                                " AND description = '"+ammunitionItemList.get(ammunitionItemList.size()-1).getDescription()+
                                "' AND type = '"+ammunitionItemList.get(ammunitionItemList.size()-1).getType()+"'";
                        String query2 = "UPDATE users SET wallet = wallet - "+ammunitionItemList.get(ammunitionItemList.size()-1).getCost()+" WHERE login = '"+knight.getLogin()+"'";
                        String query3 = "UPDATE users SET wallet = wallet + "+ammunitionItemList.get(ammunitionItemList.size()-1).getCost()+" WHERE login = '"+account.getLogin()+"'";

                        statement1 = DataBase.getInstance().prepareStatement(query1);
                        statement2 = DataBase.getInstance().prepareStatement(query2);
                        statement3 = DataBase.getInstance().prepareStatement(query3);


                        try {
                            statement1.executeUpdate(query1);
                            statement2.executeUpdate(query2);
                            statement3.executeUpdate(query3);

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        account.setWallet(account.getWallet() + itemCost);
                        knight.setWallet(knight.getWallet() - itemCost);
                        knight.addItem(ammunitionItemList.get(key - 1));
                        ammunitionItemList.remove(key - 1);
                        textArea.setText("Успішно куплено.");
                        logger.log(Level.INFO, "Knight bought ammunition item");
                    }
                    else {
                        textArea.setText("Помилка, елемент з цієї категорії вже обрано");
                        logger.log(Level.INFO, "Knight already bought this item");
                    }

                }
                else {
                    logger.log(Level.INFO, "Knight did not have enough money to buy ammunition item");
                    textArea.setText("Недостатньо коштів.");
                }
            }
        }
    }

}
