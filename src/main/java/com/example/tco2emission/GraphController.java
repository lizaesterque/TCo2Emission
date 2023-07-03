package com.example.tco2emission;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;



public class GraphController implements Initializable {

//    initialize all fields of the GUI


    @FXML
    private javafx.scene.chart.LineChart<String, Number> scatterChart;

    @FXML
    private LineChart<String, Number> populationChart;



//    the initialize method will start without be invoke
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        XYChart.Series<String, Number> series = new XYChart.Series<>();
        XYChart.Series<String, Number> populationSeries = new XYChart.Series<>();


//        make the connection with the database
        DatabaseConnector dbConnector = new DatabaseConnector();
        try (Connection connection = dbConnector.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT Name, Population, TCo2Emissions FROM co2emission;")) {

//            get each item from the database and save to the list to be save in the chart
            while (resultSet.next()) {
                String country = resultSet.getString("Name");
                BigDecimal population = resultSet.getBigDecimal("Population");
                BigDecimal emission = resultSet.getBigDecimal("TCo2Emissions");


                series.getData().add(new XYChart.Data<>(country, emission));
                populationSeries.getData().add(new XYChart.Data<>(country, population));

//                set the names for the graph
                series.setName("CO2 emissions");
                populationSeries.setName("Population");

            }

//            populate the graph with the data previous save and close the connection
            scatterChart.getData().add(series);
            populationChart.getData().add(populationSeries);
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

//    when click on the button will change for the other view(other fxml file)
    @FXML
    private void switchTableview(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Tableview.fxml"));
            Scene scene = new Scene(root, 950, 700);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}







