package Menu.Commands;

import Accounts.Account;
import Accounts.Knight;
import Accounts.Seller;
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

public class GetAccounts implements Command {
    List<Account> accounts;
    private static final Logger logger = Logger.getLogger(GetAccounts.class.getName());

    public GetAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    public void execute() {
        PreparedStatement statement;
        ResultSet resultSet;

        Log.setupLogger(logger);
        try {
            String query = "SELECT * FROM users";
            statement = DataBase.getInstance().prepareStatement(query);
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String type = resultSet.getString("user_type");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int money = resultSet.getInt("wallet");
                switch (type) {
                    case "Лицар": {
                        accounts.add(new Knight(login,password,money));
                        break;
                    }
                    case "Торговець": {
                        accounts.add(new Seller(login,password,money));
                        break;
                    }

                }
            }
            logger.log(Level.INFO,"Added all accounts");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"database with accounts not found",e);
            sendMessage("Critical error occurred: " + e + "\nDB with accounts not found");
            new Exit().execute();
        }
    }

}
