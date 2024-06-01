package org.example.smarthealthconsultingsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }

public class HealthInfoApp extends Application {


    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Health Information System");

        VBox mainMenu = createMainMenu();
        Scene mainMenuScene = new Scene(mainMenu, 300, 400);

        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }

    private VBox createMainMenu() {
        VBox menu = new VBox();
        menu.setSpacing(10);

        Button btnHumanHealthInfo = new Button("Human Health Info");
        btnHumanHealthInfo.setOnAction(e -> showTableData("human_health_info"));

        Button btnHealthFoodHabitInfo = new Button("Health Food Habit Info");
        btnHealthFoodHabitInfo.setOnAction(e -> showTableData("health_food_habit_info"));

        Button btnHealthLifestyleInfo = new Button("Health Lifestyle Info");
        btnHealthLifestyleInfo.setOnAction(e -> showTableData("health_lifestyle_info"));

        Button btnExpertAdvice = new Button("Expert Advice");
        btnExpertAdvice.setOnAction(e -> showTableData("expert_advice"));

        Button btnCounselingHomeAdvice = new Button("Counciling Home Advice");
        btnCounselingHomeAdvice.setOnAction(e -> showTableData("counciling_from_home_info"));

        Button btnDiseaseMedicineInfo = new Button("Disease Medicine Info");
        btnDiseaseMedicineInfo.setOnAction(e -> showTableData("disease_medicine_info"));

        Button btnAmbulanceServiceInfo = new Button("Ambulance Service Info");
        btnAmbulanceServiceInfo.setOnAction(e -> showTableData("ambulance_service_info"));

        menu.getChildren().addAll(btnHumanHealthInfo, btnHealthFoodHabitInfo, btnHealthLifestyleInfo, btnExpertAdvice,
                btnCounselingHomeAdvice, btnDiseaseMedicineInfo, btnAmbulanceServiceInfo);

        return menu;
    }

    private void showTableData(String tableName) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        Button btnBack = new Button("Back");
        btnBack.setOnAction(e -> primaryStage.setScene(new Scene(createMainMenu(), 300, 400)));

        TableView<HealthInfo> table = new TableView<>();

        TableColumn<HealthInfo, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<HealthInfo, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setMinWidth(600);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        table.getColumns().addAll(titleColumn, descriptionColumn);

        loadDataFromDatabase(tableName, table);

        vbox.getChildren().addAll(btnBack, table);

        primaryStage.setScene(new Scene(vbox, 800, 600));
    }

    private void loadDataFromDatabase(String tableName, TableView<HealthInfo> table) {
        String url = "jdbc:mysql://localhost:3306/health_consulting_system";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT title, description FROM " + tableName)) {

            while (rs.next()) {
                HealthInfo healthInfo = new HealthInfo(rs.getString("title"), rs.getString("description"));
                table.getItems().add(healthInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}