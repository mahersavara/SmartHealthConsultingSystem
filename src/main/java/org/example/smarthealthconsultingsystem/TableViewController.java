package org.example.smarthealthconsultingsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TableViewController {

    @FXML
    private TableView<HealthInfoApp.HealthInfo> tableView;

    @FXML
    private TableColumn<HealthInfoApp.HealthInfo, String> titleColumn;

    @FXML
    private TableColumn<HealthInfoApp.HealthInfo, String> descriptionColumn;

    private Stage primaryStage;

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            VBox mainMenu = loader.load();
            MainMenuController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);

            Scene mainMenuScene = new Scene(mainMenu, 300, 400);
            primaryStage.setScene(mainMenuScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTableData(String tableName) {
        String url = "jdbc:mysql://localhost:3306/health_consulting_system";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT title, description FROM " + tableName)) {

            while (rs.next()) {
                HealthInfoApp.HealthInfo healthInfo = new HealthInfoApp.HealthInfo(rs.getString("title"), rs.getString("description"));
                tableView.getItems().add(healthInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
