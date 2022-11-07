package Menu;

import Menu.Commands.SortItemsByCost;
import Logger.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Switcher {
    private static final Logger logger = Logger.getLogger(SortItemsByCost.class.getName());

    private List<Command> commands = new ArrayList<>();
    public void addCommand(Command command) {
        this.commands.add(command); // optional
    }
    public void execute(int number){
        Log.setupLogger(logger);
        commands.get(number-1).execute();
        logger.log(Level.INFO,"chosen "+commands.get(number-1));
    }
}
