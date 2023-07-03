package com.example.tco2emission;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class TableController implements Initializable {



    @FXML
    private TableColumn<Tco2Emission, Integer> countrycode;

    @FXML
    private TableColumn<Tco2Emission, String> name;

    @FXML
    private TableColumn<Tco2Emission, BigDecimal> population;

    @FXML
    private TableColumn<Tco2Emission, BigDecimal> tco;

    @FXML
    private TableView<Tco2Emission> Tco2Table;

    ObservableList<Tco2Emission> list = FXCollections.observableArrayList();



    //    the initialize method will start without be invoke


    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        countrycode.setCellValueFactory(new PropertyValueFactory<>("countryCode"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        population.setCellValueFactory(new PropertyValueFactory<>("population"));
        tco.setCellValueFactory(new PropertyValueFactory<>("tco2"));
        getData();
    }


    //       method to created a connection  and get the data from SQL
    @FXML
    private void getData() {
        DatabaseConnector dbConnector = new DatabaseConnector();
        try (Connection connection = dbConnector.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM co2emission")) {

            while (resultSet.next()) {

                int code = resultSet.getInt("CountryCodes");
                String name = resultSet.getString("Name");
                BigDecimal population = resultSet.getBigDecimal("Population");
                BigDecimal emissions = resultSet.getBigDecimal("TCo2Emissions");
                Tco2Emission tco2Emission = new Tco2Emission(code, name, population, emissions);
                list.add(tco2Emission);
            }

            // Set the data in the TableView
            Tco2Table.setItems(list);
            System.out.println(list);

            //close the connection
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }


//    button to change to the Graph Scene
    @FXML
    private void switchGraphview(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Graphview.fxml"));
            Scene scene = new Scene(root, 950, 700);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

