module com.lasalle.horseracinggui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lasalle.horseracinggui to javafx.fxml;
    exports com.lasalle.horseracinggui;
}