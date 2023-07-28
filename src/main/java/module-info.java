module simpleclient.simplelauncher2 {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            requires com.dlsc.formsfx;
    opens simpleclient.simplelauncher2 to javafx.fxml;
    exports simpleclient.simplelauncher2;
}