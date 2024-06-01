module org.example.smarthealthconsultingsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.smarthealthconsultingsystem to javafx.fxml;
    exports org.example.smarthealthconsultingsystem;
}