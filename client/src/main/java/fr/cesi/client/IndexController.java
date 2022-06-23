package fr.cesi.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexController {
  @FXML
  private ListView service_list;
  @FXML
  private ListView place_list;
  @FXML
  private TableView user_list;
  @FXML
  private TextField search_input;
  @FXML
  private Button search_button;
  @FXML
  private ListView details_list;

  @FXML
  private void handleButtonAction(ActionEvent event) {
    System.out.println("You clicked me!");
    search_button.setText("Hello World!");
  }


  public void initialize(URL url, ResourceBundle resources) {

  }
}
