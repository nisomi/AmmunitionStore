package Menu;

import java.util.ArrayList;
import java.util.List;

public class Switcher {
    private List<Command> commands = new ArrayList<>();
    public void addCommand(Command command) {
        this.commands.add(command); // optional
    }
    public void execute(int number){
        commands.get(number-1).execute();
    }
}
