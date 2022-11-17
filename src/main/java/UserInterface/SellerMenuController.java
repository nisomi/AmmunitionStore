package UserInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Accounts.Account;
import Accounts.Seller;
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

public class SellerMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AllAmmoButton;

    @FXML
    private Button BackButton;

    @FXML
    private Button BalanceButton;

    @FXML
    private Button AddButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private TextField InputField;

    @FXML
    private TextArea TextArea;

    @FXML
    private Button ok;

    @FXML
    void initialize() {
        String style = InputField.getStyle();
        List<Account> accounts = new ArrayList<>();
        new GetAccounts(accounts).execute();

        AddButton.setOnAction(actionEvent -> {
            InputField.setStyle(style);
            TextArea.clear();
            InputField.setPromptText("Оберіть тип амуніції, яку хочете додати:");
            ShowTypes();

            ok.setOnAction(action -> {
                try {
                    int choice = Integer.parseInt(InputField.getText());
                    if (choice>12 || choice<1){
                        throw new NumberFormatException();
                    }
                    InputField.clear();
                    InputField.setPromptText("Назва: ");
                    ok.setOnAction(action1 -> {
                        String name = InputField.getText().trim();
                        InputField.clear();
                        InputField.setPromptText("Вага:");
                        InputField.setStyle(style);
                        ok.setOnAction(action2 -> {
                            try {
                                int weight = Integer.parseInt(InputField.getText());
                                InputField.clear();
                                InputField.setPromptText("Ціна:");
                                InputField.setStyle(style);
                                ok.setOnAction(action3 -> {
                                    int cost;
                                    try {
                                        cost = Integer.parseInt(InputField.getText());
                                        InputField.clear();
                                        switch (choice) {
                                            case 1, 2, 3, 4, 5, 6, 7: {
                                                InputField.setPromptText("Захист:");
                                                InputField.setStyle(style);
                                                ok.setOnAction(action4 -> {
                                                    int protection;
                                                    try {
                                                        protection = Integer.parseInt(InputField.getText());
                                                        InputField.clear();
                                                        InputField.setPromptText("Короткий опис:");
                                                        InputField.setStyle(style);
                                                        ok.setOnAction(action5 -> {
                                                            String description = InputField.getText();
                                                            new AddItem((Seller) Storage.getAccount(), Storage.getAmmunitionItems(), name, weight, cost, protection, 0, description, choice, TextArea).execute();

                                                        });
                                                    } catch (NumberFormatException e) {
                                                        ShowError();
                                                    }
                                                });
                                                break;
                                            }
                                            case 8, 9, 10, 11: {
                                                InputField.setPromptText("Сила удару:");
                                                InputField.setStyle(style);
                                                ok.setOnAction(action4 -> {
                                                    int damage;
                                                    try {
                                                        damage = Integer.parseInt(InputField.getText());
                                                        InputField.clear();
                                                        InputField.setPromptText("Короткий опис:");
                                                        InputField.setStyle(style);
                                                        ok.setOnAction(action5 -> {
                                                            String description = InputField.getText();
                                                            new AddItem((Seller) Storage.getAccount(), Storage.getAmmunitionItems(), name, weight, cost, 0, damage, description, choice, TextArea).execute();
                                                        });
                                                    } catch (NumberFormatException e) {
                                                        ShowError();
                                                    }
                                                });
                                                break;
                                            }
                                        }
                                    } catch (NumberFormatException e) {
                                        ShowError();
                                    }
                                });
                            }catch (NumberFormatException | IndexOutOfBoundsException e) {
                                ShowError();
                            }
                        });
                    });
                } catch (NumberFormatException e) {
                    ShowError();
                }
            });
                InputField.clear();
        });

        DeleteButton.setOnAction(actionEvent -> {
            InputField.setStyle(style);
            TextArea.clear();
            if (Storage.getAmmunitionItems().size() == 0)
            {
                TextArea.setText("Наразі доступної амуніції для видалення немає");
            }
            else {
                for (int i = 1; i <= Storage.getAmmunitionItems().size(); i++) {
                    TextArea.appendText("\n" + i + "." + Storage.getAmmunitionItems().get(i - 1));
                }
                InputField.setPromptText("Введіть номер товару який хочете видалити: ");
                ok.setOnAction(action -> {
                    try {
                        int key = Integer.parseInt(InputField.getText());
                        new DeleteItem(Storage.getAmmunitionItems(), key, TextArea).execute();
                        InputField.setPromptText("");
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        ShowError();
                    }

                });
            }
        });

        BalanceButton.setOnAction(action -> {
            InputField.setStyle(style);
            new ShowWalletBalance(Storage.getAccount(), TextArea).execute();
        });

        AllAmmoButton.setOnAction(actionEvent -> {
            InputField.setStyle(style);
            TextArea.clear();
            InputField.clear();
            if (Storage.getAmmunitionItems().size() == 0)
            {
                TextArea.setText("Наразі доступної амуніції немає");
            }
            else {
                InputField.setPromptText("Оберіть номер дії: ");
                ShowActions();
                ok.setOnAction(action -> {
                    TextArea.clear();
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
                            InputField.clear();
                            InputField.setPromptText("Введіть коректне значення");
                        }
                    }
                });
                InputField.setPromptText("");
            }
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
        });
    }

    void ShowTypes(){
        TextArea.appendText("\n\t1. Чоботи");
        TextArea.appendText("\n\t2. Нагрудник");
        TextArea.appendText("\n\t3. Кольчуга");
        TextArea.appendText("\n\t4. Плащ");
        TextArea.appendText("\n\t5. Рукавиці");
        TextArea.appendText("\n\t6. Шолом");
        TextArea.appendText("\n\t7. Щит");
        TextArea.appendText("\n\t8. Кинджал");
        TextArea.appendText("\n\t9. Довгий лук");
        TextArea.appendText("\n\t10. Посох");
        TextArea.appendText("\n\t11. Меч");
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
