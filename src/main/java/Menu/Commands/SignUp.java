package Menu.Commands;

import Accounts.Account;
import Accounts.Knight;
import Ammunition.AmmunitionItem;
import DataBase.DataBase;
import Menu.Command;
import Menu.Storage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SignUp implements Command {
    private String login;
    private String password;
    int wallet;

    public SignUp(String login, String password, int wallet) {
        this.login = login;
        this.password = password;
        this.wallet = wallet;
    }

    @Override
    public void execute() {
        List<Account> accounts = new ArrayList<>();
        new GetAccounts(accounts).execute();
        List<AmmunitionItem> ammunition = new ArrayList<>();
        new GetItems(ammunition).execute();
        if (!CheckIfExist(login, accounts)) {
            PreparedStatement statement;
            Knight new_knight = new Knight(login,password,wallet);
            accounts.add(new_knight);
            String query = "INSERT INTO users (login,password,user_type,wallet) VALUES ('" + login + "' ,'" + password + "' ," + "'Лицар'" + ", " + wallet + ")";
            statement = DataBase.getInstance().prepareStatement(query);
            try {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            new Storage(new_knight,ammunition);
        }
    }
    private boolean CheckIfExist(String login, List<Account> accounts){
        for (Account account : accounts) {
            if (account.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }
}
