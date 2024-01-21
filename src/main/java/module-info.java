module com.example.image_editing {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens com.example.image_editing to javafx.fxml;
    exports com.example.image_editing;
}