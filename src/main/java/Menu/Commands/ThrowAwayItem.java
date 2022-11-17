package Menu.Commands;

import Accounts.Account;
import Accounts.Knight;
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

public class ThrowAwayItem implements Command {
    private Knight knight;
    int key;

    private TextArea textArea;
    private static final Logger logger = Logger.getLogger(ReturnItem.class.getName());

    public ThrowAwayItem(Knight knight, int key, TextArea textArea) {
        this.knight = knight;
        this.key = key;
        this.textArea = textArea;
    }

    public void execute() {
        Log.setupLogger(logger);
        PreparedStatement statement;
        String query = "DELETE FROM ammunition WHERE name = '"+knight.getAmmunitionItems().get(key-1).getName()+
                "' AND weight = "+knight.getAmmunitionItems().get(key-1).getWeight()+
                " AND cost = "+knight.getAmmunitionItems().get(key-1).getCost()+
                " AND description = '"+knight.getAmmunitionItems().get(key-1).getDescription()+
                "' AND type = '"+knight.getAmmunitionItems().get(key-1).getType()+"')";
        statement = DataBase.getInstance().prepareStatement(query);
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        knight.deleteItem(key - 1);
        if (textArea!=null){
            textArea.setText("Успішно викинуто.");
        }
    }
}
