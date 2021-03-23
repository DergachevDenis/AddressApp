package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.util.*;


public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Ссылка на главное приложение.
    private MainApp mainApp;

    public PersonOverviewController() {
    }

    //Инициализация класса-контроллера. Этот метод вызывается автоматически после того, как fxml-файл будет загружен.
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        // Очистка дополнительной информации об адресате.
        showPersonDetails(null);

        // Слушаем изменения выбора, и при изменении отображаем дополнительную информацию об адресате.
        personTable.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->showPersonDetails(newValue));
    }



    //Вызывается главным приложением, которое даёт на себя ссылку.
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Добавление в таблицу данных из наблюдаемого списка
        personTable.setItems(mainApp.getPersonData());
    }

    //Заполняет все текстовые поля, отображая подробности об адресате.
    // * Если указанный адресат = null, то все текстовые поля очищаются.
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Заполняем метки информацией из объекта person.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            // Если Person = null, то убираем весь текст.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    //Вызывается, когда пользователь кликает по кнопке удаления
    @FXML
    private void handleDeletePerson(){
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex>=0){
            personTable.getItems().remove(selectedIndex);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }

    }
    //Вызывается, когда пользователь кликает по кнопке New
    @FXML
    private void handleNewPerson(){
        Person tempPerson = new Person();
        boolean ocClicked = mainApp.showPersonEditDialog(tempPerson);
        if (ocClicked){
            mainApp.getPersonData().add(tempPerson);
        }
    }

    @FXML
    private void handleEditPerson(){
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if(selectedPerson!=null){
            boolean ocClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (ocClicked){
                showPersonDetails(selectedPerson);
            }
        }
        // Ничего не выбрано
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Peson Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
