package UserInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Menu.Commands.SignUp;
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

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField WalletField;

    @FXML
    void initialize() {
        SignUpButton.setOnAction(actionEvent -> {
            String login = LoginField.getText().trim();
            String password = PasswordField.getText().trim();
            int wallet = Integer.parseInt(WalletField.getText());
            new SignUp(login,password,wallet).execute();
            if (Storage.getAccount() != null) {
                SignUpButton.getScene().getWindow().hide();
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
            else {
                Shake LoginAnimation = new Shake(LoginField);
                Shake PasswordAnimation = new Shake(PasswordField);
                Shake WalletAnimation = new Shake(WalletField);
                LoginAnimation.playAnimation();
                PasswordAnimation.playAnimation();
                WalletAnimation.playAnimation();
            }
        });

    }


}
