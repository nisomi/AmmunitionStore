package UserInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Accounts.Knight;
import Accounts.Seller;
import Menu.Commands.Login;
import Menu.Storage;
import UserInterface.animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button SignInButton;

    @FXML
    private Button SignUpButton;

    @FXML
    void initialize() {

        SignInButton.setOnAction(actionEvent -> {
            String login = LoginField.getText().trim();
            String password = PasswordField.getText().trim();
            new Login(login,password).execute();

            if (Storage.getAccount() instanceof Knight){
                SignInButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/KnightMenu.fxml"));
                try {
                    loader.load();
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (Storage.getAccount() instanceof Seller){
                SignInButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/SellerMenu.fxml"));
                try {
                    loader.load();
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (Storage.getAccount() == null) {
                Shake LoginAnimation = new Shake(LoginField);
                Shake PasswordAnimation = new Shake(PasswordField);
                LoginAnimation.playAnimation();
                PasswordAnimation.playAnimation();
            }
        });

        SignUpButton.setOnAction(actionEvent -> {
            SignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/SignUp.fxml"));
            try {
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


}

