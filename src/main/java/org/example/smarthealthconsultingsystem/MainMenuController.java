package org.example.smarthealthconsultingsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    private Stage primaryStage;

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void showHumanHealthInfo() {
        showTableData("human_health_info");
    }

    @FXML
    private void showHealthFoodHabitInfo() {
        showTableData("health_food_habit_info");
    }

    @FXML
    private void showHealthLifestyleInfo() {
        showTableData("health_lifestyle_info");
    }

    @FXML
    private void showExpertAdvice() {
        showTableData("expert_advice");
    }

    @FXML
    private void showCounselingHomeAdvice() {
        showTableData("counciling_from_home_info");
    }

    @FXML
    private void showDiseaseMedicineInfo() {
        showTableData("disease_medicine_info");
    }

    @FXML
    private void showAmbulanceServiceInfo() {
        showTableData("ambulance_service_info");
    }

    private void showTableData(String tableName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TableView.fxml"));
            VBox tableView = loader.load();
            TableViewController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            controller.loadTableData(tableName);

            Scene tableScene = new Scene(tableView, 800, 600);
            primaryStage.setScene(tableScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
