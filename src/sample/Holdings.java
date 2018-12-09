package sample;

import javafx.fxml.FXML;
import javafx.scene.text.Text;


/**
 * handle holdings section in GUI
 */
public class Holdings {
  private int shareAmt;
  private String ticker;

  @FXML
  private Text holdingsOne;
  @FXML
  private Text holdingsTwo;
  @FXML
  private Text holdingsThree;
  @FXML
  private Text holdingsFour;
  @FXML
  private Text holdingsFive;
  @FXML
  private Text holdingsSix;
  @FXML
  private Text holdingsSeven;
  @FXML
  private Text holdingsEight;
  @FXML
  private Text holdingsNine;
  @FXML
  private Text holdingsTen;
  @FXML
  private Text holdingsEleven;


  public Holdings(int shareAmt, String ticker){
    this.shareAmt = shareAmt;
    this.ticker = ticker;
  }


  public void setHoldings(){

  }

  public void parseHoldings(){

  }

}
