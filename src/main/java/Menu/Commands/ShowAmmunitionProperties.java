package Menu.Commands;

import Accounts.Knight;
import Menu.Command;
import Logger.*;
import javafx.scene.control.TextArea;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowAmmunitionProperties implements Command {
    Knight knight;
    private static final Logger logger = Logger.getLogger(ShowAmmunitionProperties.class.getName());

    private TextArea textArea;
    public ShowAmmunitionProperties(Knight knight, TextArea textArea){
        this.knight = knight;
        this.textArea = textArea;
    }

    public void execute() {
        Log.setupLogger(logger);
        if (textArea!=null){
            textArea.appendText("Загальна вартість всієї придбаної амуніції: " + knight.getAmmunitionCost());
            textArea.appendText("\nВага: " + knight.getAmmunitionWeight());
            textArea.appendText("\nЗахист броні: " + knight.getAmmunitionProtection());
            textArea.appendText("\nСила атаки зброї: " + knight.getAmmunitionDamage());
        }
        logger.log(Level.INFO, "Showed ammunition properties");
    }
}
