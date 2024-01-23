module org.example.gestionale {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;


    opens org.example.gestionale to javafx.fxml;
    exports org.example.gestionale;
}