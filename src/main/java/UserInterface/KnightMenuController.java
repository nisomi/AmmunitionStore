package UserInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Accounts.Account;
import Accounts.Knight;
import Ammunition.AmmunitionItem;
import Menu.Commands.*;
import Menu.Storage;
import UserInterface.animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class KnightMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AllAmmoButton;

    @FXML
    private Button AmmoButton;

    @FXML
    private Button BackButton;

    @FXML
    private Button BalanceButton;

    @FXML
    private Button BoughtButton;

    @FXML
    private Button BuyButton;

    @FXML
    private TextField InputField;

    @FXML
    private Button ReturnButton;

    @FXML
    private TextArea TextArea;

    @FXML
    private Button ThrowAwayButton;

    @FXML
    private Button ok;

    @FXML
    void initialize() {
        String style = InputField.getStyle();
        List<Account> accounts = new ArrayList<>();
        new GetAccounts(accounts).execute();

        BalanceButton.setOnAction(action -> {
            InputField.setPromptText("");
            InputField.clear();
            InputField.setStyle(style);
            new ShowWalletBalance(Storage.getAccount(), TextArea).execute();
        });

        BuyButton.setOnAction(action -> {
            InputField.setStyle(style);
            InputField.clear();
            TextArea.clear();
            InputField.setPromptText("");
            if (Storage.getAmmunitionItems().size() == 0)
            {
                TextArea.setText("Наразі доступної амуніції немає");
            }
            else {
                InputField.setPromptText("Введіть номер товару який хочете купити: ");
                for (int i = 1; i <= Storage.getAmmunitionItems().size(); i++) {
                    TextArea.appendText("\n" + i + "." + Storage.getAmmunitionItems().get(i - 1));
                }
                ok.setOnAction(actionEvent -> {
                    try {
                        int key = Integer.parseInt(InputField.getText());
                        InputField.clear();
                        new BuyItem((Knight) Storage.getAccount(), Storage.getAmmunitionItems(), accounts, key, TextArea).execute();
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        ShowError();
                    }
                });
            }
        });

        ReturnButton.setOnAction(action -> {
            InputField.setStyle(style);
            InputField.setPromptText("");
            TextArea.clear();
            InputField.clear();
            if (((Knight) (Storage.getAccount())).getAmmunitionItems().size() == 0) {
                TextArea.setText("Ви ще не придбали нічого, що могли б повернути");
            } else {
                for (int i = 1; i <= ((Knight) (Storage.getAccount())).getAmmunitionItems().size(); i++) {
                    TextArea.appendText("\n" + i + "." + ((Knight) (Storage.getAccount())).getAmmunitionItems().get(i - 1));
                }
                InputField.setPromptText("Введіть номер товару який хочете повернути: ");
                ok.setOnAction(actionEvent -> {
                    try {
                        int key = Integer.parseInt(InputField.getText());
                        new ReturnItem((Knight) Storage.getAccount(), Storage.getAmmunitionItems(), accounts, key, TextArea).execute();
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        ShowError();
                    }
                });
            }
        });

        ThrowAwayButton.setOnAction(actionEvent -> {
            InputField.setStyle(style);
            TextArea.clear();
            InputField.setPromptText("");
            if (((Knight)(Storage.getAccount())).getAmmunitionItems().size() == 0){
                TextArea.setText("Ви ще не придбали нічого, що могли б викинути");
            }
            else {
                for (int i = 1; i <= ((Knight) (Storage.getAccount())).getAmmunitionItems().size(); i++) {
                    TextArea.appendText("\n" + i + "." + ((Knight) (Storage.getAccount())).getAmmunitionItems().get(i - 1));
                }
                InputField.setPromptText("Введіть номер товару який хочете викинути: ");
                ok.setOnAction(action -> {
                    try {
                        int key = Integer.parseInt(InputField.getText());
                        new ThrowAwayItem((Knight) Storage.getAccount(), key, TextArea).execute();
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        ShowError();
                    }
                });
            }
        });

        BoughtButton.setOnAction(actionEvent -> {
            InputField.setStyle(style);
            InputField.setPromptText("");
            TextArea.clear();
            InputField.clear();
            if (((Knight)(Storage.getAccount())).getAmmunitionItems().size() == 0)
            {
                TextArea.setText("Ви ще не придбали нічого");
            }
            else {
                ShowActions();
                InputField.setPromptText("Введіть номер дії: ");
                ok.setOnAction(action -> {
                    TextArea.clear();
                    try {
                        InputField.setStyle(style);
                        int key = Integer.parseInt(InputField.getText());
                        switch (key) {
                            case 1: {
                                InputField.clear();
                                TextArea.clear();
                                for (AmmunitionItem item : ((Knight) (Storage.getAccount())).getAmmunitionItems()) {
                                    TextArea.appendText(item + "\n");
                                }
                                break;
                            }
                            case 2: {
                                InputField.clear();
                                new SortItemsByCost((((Knight) (Storage.getAccount())).getAmmunitionItems()), TextArea).execute();
                                break;
                            }
                            case 3: {
                                InputField.clear();
                                new SortItemsByWeight((((Knight) (Storage.getAccount())).getAmmunitionItems()), TextArea).execute();
                                break;
                            }
                            case 4: {
                                InputField.clear();
                                InputField.setPromptText("Введіть мінімальне значення вартості:");
                                InputField.setStyle(style);
                                ok.setOnAction(actionEvent1 -> {
                                    try {
                                        int min = Integer.parseInt(InputField.getText());
                                        InputField.clear();
                                        InputField.setPromptText("Введіть максимальне значення вартості:");
                                        InputField.setStyle(style);
                                        ok.setOnAction(actionEvent2 -> {
                                            try {
                                                int max = Integer.parseInt(InputField.getText());
                                                InputField.clear();
                                                new SearchItemsByCost(((Knight) (Storage.getAccount())).getAmmunitionItems(), min, max, TextArea).execute();
                                            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                                ShowError();
                                            }
                                        });
                                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                        ShowError();
                                    }
                                });

                                break;
                            }
                            case 5: {
                                InputField.clear();
                                InputField.setPromptText("Введіть мінімальне значення ваги:");
                                InputField.setStyle(style);
                                ok.setOnAction(actionEvent1 -> {
                                    try {
                                        int min = Integer.parseInt(InputField.getText());
                                        InputField.clear();
                                        InputField.setPromptText("Введіть максимальне значення ваги:");
                                        InputField.setStyle(style);
                                        ok.setOnAction(actionEvent2 -> {
                                            try {
                                                int max = Integer.parseInt(InputField.getText());
                                                InputField.clear();
                                                new SearchItemsByWeight(((Knight) (Storage.getAccount())).getAmmunitionItems(), min, max, TextArea).execute();
                                            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                                ShowError();
                                            }
                                        });
                                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                        ShowError();
                                    }
                                });
                                break;
                            }
                            default: {
                                ShowError();
                            }
                        }
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        ShowError();
                    }
                });

            }
        });

        AllAmmoButton.setOnAction(actionEvent -> {
            InputField.setStyle(style);
            InputField.setPromptText("");
            TextArea.clear();
            InputField.clear();
            if (Storage.getAmmunitionItems().size() == 0)
            {
                TextArea.setText("Наразі доступної амуніції немає");
            }
            else {
                ShowActions();
                InputField.setPromptText("Введіть номер дії: ");
                ok.setOnAction(action -> {
                    TextArea.clear();
                    try {
                        int key = Integer.parseInt(InputField.getText());
                        switch (key) {
                            case 1: {
                                InputField.clear();
                                TextArea.clear();
                                for (AmmunitionItem item : Storage.getAmmunitionItems()) {
                                    TextArea.appendText(item + "\n");
                                }
                                break;
                            }
                            case 2: {
                                InputField.clear();
                                new SortItemsByCost(Storage.getAmmunitionItems(), TextArea).execute();
                                break;
                            }
                            case 3: {
                                InputField.clear();
                                new SortItemsByWeight(Storage.getAmmunitionItems(), TextArea).execute();
                                break;
                            }
                            case 4: {
                                InputField.clear();
                                InputField.setPromptText("Введіть мінімальне значення вартості:");
                                InputField.setStyle(style);
                                ok.setOnAction(actionEvent1 -> {
                                    try {
                                        int min = Integer.parseInt(InputField.getText());
                                        InputField.clear();
                                        InputField.setPromptText("Введіть максимальне значення вартості:");
                                        InputField.setStyle(style);
                                        ok.setOnAction(actionEvent2 -> {
                                            try {
                                                int max = Integer.parseInt(InputField.getText());
                                                InputField.clear();
                                                new SearchItemsByCost(Storage.getAmmunitionItems(), min, max, TextArea).execute();
                                            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                                ShowError();
                                            }
                                        });
                                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                        ShowError();
                                    }
                                });
                                break;
                            }
                            case 5: {
                                InputField.clear();
                                InputField.setPromptText("Введіть мінімальне значення ваги:");
                                InputField.setStyle(style);
                                ok.setOnAction(actionEvent1 -> {
                                    try {
                                        int min = Integer.parseInt(InputField.getText());
                                        InputField.clear();
                                        InputField.setPromptText("Введіть максимальне значення ваги:");
                                        InputField.setStyle(style);
                                        ok.setOnAction(actionEvent2 -> {
                                            try {
                                                int max = Integer.parseInt(InputField.getText());
                                                InputField.clear();
                                                new SearchItemsByWeight(Storage.getAmmunitionItems(), min, max, TextArea).execute();
                                            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                                ShowError();
                                            }
                                        });
                                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                        ShowError();
                                    }
                                });
                                break;
                            }
                            default: {
                                ShowError();
                            }
                        }
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        ShowError();
                    }
                });
            }
        });

        AmmoButton.setOnAction(actionEvent -> {
            InputField.setStyle(style);
            InputField.setPromptText("");
            TextArea.clear();
            InputField.clear();
            new ShowAmmunitionProperties((Knight) Storage.getAccount(),TextArea).execute();
        });

        BackButton.setOnAction(action -> {
            BackButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Authorization.fxml"));
            try {
                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            InputField.setPromptText("");
        });
    }

    void ShowError(){
        InputField.clear();
        InputField.setPromptText("Введено хибне значення");
        Shake InputError = new Shake(InputField);
        InputError.playAnimation();
    }

    void ShowActions(){
        TextArea.appendText("1. Просто показати амуніцію");
        TextArea.appendText("\n2. Відсортувати обрану амуніцію за вартістю");
        TextArea.appendText("\n3. Відсортувати обрану амуніцію за вагою");
        TextArea.appendText("\n4. Знайти елементи амуніції, які відповідають заданому діапазону цін");
        TextArea.appendText("\n5. Знайти елементи амуніції, які відповідають заданому діапазону ваги");

    }
}
