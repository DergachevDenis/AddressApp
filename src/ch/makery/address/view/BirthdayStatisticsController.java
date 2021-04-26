package ch.makery.address.view;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import ch.makery.address.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

public class BirthdayStatisticsController {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private ObservableList<String> monthNames = FXCollections.observableArrayList(); // collection for name month

    @FXML
    void initialize() {
        // get array with name month
        String[] months = DateFormatSymbols.getInstance(Locale.ROOT).getMonths();
        // add list
        monthNames.addAll(Arrays.asList(months));
        //Name month for X axis
        xAxis.setCategories(monthNames);
    }

    // Задаёт адресатов, о которых будет показана статистика
    public void setPersonData(List<Person> personList) {
        //read address people have birthday in this month
        int[] monthCounter = new int[12];
        for (Person person : personList) {
            int month = person.getBirthday().getMonthValue() - 1;
            monthCounter[month]++;
        }
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        //create objekt XYChart.Data for everyone month and add him in series
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }
        barChart.getData().add(series);
    }
}

