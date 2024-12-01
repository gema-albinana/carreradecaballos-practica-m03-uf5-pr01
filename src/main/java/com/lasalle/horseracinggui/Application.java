package com.lasalle.horseracinggui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Image icon = new Image(getClass().getResource("/images/KNIGHT_CLUBS.png").toExternalForm());
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("game.fxml"));

        // TODO: De momento lo tengo puesto en 1280x768 para que la ventana(Stage) se dimensione al tablero
        //  Pero para el resto de stages como el menu, y apuestas, habrá que ajustarlo a cada diseño.

        /* Ejemplo:
               FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("menu.fxml"));
               Scene scene = new Scene(fxmlLoader.load(), 600, 400);
               stage.setTitle("Horses racing game | MENU");
         */

        Scene scene = new Scene(fxmlLoader.load(), 1280, 768);
        stage.setTitle("Horses racing game");
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}