package org.example.gestionale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionaleApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GestionaleApplication.class.getResource("gestionale-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        stage.setMinHeight(496);
        stage.setMaxHeight(496);
        stage.setMinWidth(740);
        stage.setMaxWidth(740);


    }

    public static void main(String[] args) {
        launch();
    }
}