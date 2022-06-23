module fr.cesi.client {
  requires javafx.controls;
  requires javafx.fxml;

  opens fr.cesi.client to javafx.fxml;
  exports fr.cesi.client;
}
