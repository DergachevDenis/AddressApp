package ch.makery.address.view;

import ch.makery.address.model.Person;
import ch.makery.address.util.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javafx.stage.Stage;

import javax.print.DocFlavor;
import javafx.scene.control.TextField;
import java.util.ResourceBundle;

public class PersonEditDialogController {


    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField postalCodeField;

    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    private void initialize() {

    }

    //Сцена для этого окна
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    //Задаём адресата, информцацию о котором будем менять
    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");

    }

    // Returns true,если пользователь кликнул ОК, в другом случае false.
    public boolean isOkClicked() {
        return okClicked;
    }

    //Вызывается, когда пользователь кликнул по кнопке OK.
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    //Вызывается, когда пользователь кликнул по кнопке Cancel.
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    //Проверяет пользовательский ввод

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0){
            errorMessage+="No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0){
            errorMessage+="No valid last name!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0){
            errorMessage+="No valid street!\n";
        }
        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0){
            errorMessage+="No valid postalCode!\n";
        }
        else {
            //Пытаемся преобразовать почтовый код в INT
            try {
                Integer.parseInt(postalCodeField.getText());
            }
            catch (NumberFormatException e){
                errorMessage+="No valid postalCode (must be an integer)!\n";
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0){
            errorMessage+="No valid city!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0){
            errorMessage+="No valid birthdayField!\n";
        }
        else {
            if(!DateUtil.validDate(birthdayField.getText())){
                errorMessage+="No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length()==0){
            return true;
        }
        else {
            //Показываем сообщение о ошибке

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
