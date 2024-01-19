module org.example.gestionale {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.gestionale to javafx.fxml;
    exports org.example.gestionale;
}