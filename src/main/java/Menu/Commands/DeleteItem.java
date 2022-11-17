package Menu.Commands;

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


public class DeleteItem implements Command {
    private List<AmmunitionItem> ammunitionItemList;
    private int key;
    private TextArea textArea;
    public DeleteItem(List<AmmunitionItem> ammunitionItemList, int key, TextArea textArea ) {
        this.ammunitionItemList = ammunitionItemList;
        this.key = key;
        this.textArea = textArea;
    }
    private static final Logger logger = Logger.getLogger(DeleteItem.class.getName());

    public void execute() {
        Log.setupLogger(logger);
        PreparedStatement statement;
        String query = "DELETE FROM ammunition WHERE name = '"+ammunitionItemList.get(key - 1).getName()+
                "' AND weight = "+ammunitionItemList.get(key - 1).getWeight()+
                " AND cost = "+ammunitionItemList.get(key - 1).getCost()+
                " AND description = '"+ammunitionItemList.get(key - 1).getDescription()+
                "' AND type = '"+ammunitionItemList.get(key - 1).getType()+
                "' AND (userID is null)";
        statement = DataBase.getInstance().prepareStatement(query);
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (textArea!=null){
            textArea.setText("Успішно видалено");
        }

        ammunitionItemList.remove(key - 1);
        logger.log(Level.INFO, "Ammunition item was successfully deleted");
    }
}
