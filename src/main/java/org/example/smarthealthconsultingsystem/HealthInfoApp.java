package org.example.smarthealthconsultingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HealthInfoApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Health Information System");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            VBox mainMenu = loader.load();
            MainMenuController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);

            Scene mainMenuScene = new Scene(mainMenu, 300, 400);
            primaryStage.setScene(mainMenuScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }






    public static class HealthInfo {
        private String title;
        private String description;

        public HealthInfo(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


}
