package Menu.Commands;

import Accounts.Account;
import Accounts.Knight;
import Ammunition.AmmunitionItem;
import Menu.Command;
import Logger.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exit implements Command {
    private static final Logger logger = Logger.getLogger(Exit.class.getName());

    public Exit(){
    }

    public void execute() {
        Log.setupLogger(logger);
            logger.log(Level.INFO,"Successfully exited");
            System.exit(0);
    }
}
