package sample;

import java.util.Date;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class Controller {


  @FXML
  private Text portValue;
  @FXML
  private Text netWorth;
  @FXML
  private Text Cash;
  @FXML
  private Text ErrorCode;
  @FXML
  private TextField userTickerSymbol;   //Ticker user wants to buy or sell
  @FXML
  private TextField userShareAmt;       //Amount of shares users wants to buy or sell
  @FXML
  private Text stock1;
  @FXML
  private Text stock2;
  @FXML
  private Text stock3;
  @FXML
  private Text stock4;


  private double cash = 10000;
  private double portfolioValue = 0;
  private double networth = 10000;
  private double orderTotal;

  public Controller() {

  }

  @FXML
  public void onStockBuy() {
    orderInfo();
    if (orderTotal < cash) {
      cash -= orderTotal;
      portfolioValue += orderTotal;
    } else {
      ErrorCode.setText("Not enough Cash for order");
    }
    netWorthCalc(cash, portfolioValue);
  }

  private void orderInfo() {
    String ticker = null;
    int shareAmt = 0;
    try {
      ticker = userTickerSymbol.getText();
      shareAmt = Integer.parseInt(userShareAmt.getText());
    } catch (NumberFormatException e) {
      ErrorCode.setText("Please enter valid Share amount");
    }
    orderTotal = shareAmt * 20;
    //get ticker price from api

//    System.out.println(ticker);
//    System.out.println("Share Amt:" + shareAmt);

  }
  @FXML
  private void updatePrices(){
    StockQuote tsla = new StockQuote("TSLA");
    StockQuote ge = new StockQuote("GE");
    StockQuote snap = new StockQuote("SNAP");
    StockQuote amd = new StockQuote("AMD");

    stock1.setText(tsla.getPrice() + "      " + tsla.getPriceChange());
  //  stock2.setText(ge.getPrice() + "      " + ge.getPriceChange());



  }
  @FXML
  public void onStockSell(){

  }
  private void netWorthCalc(Double cash, Double portfolioValue) {
    networth = cash + portfolioValue;
    netWorth.setText(String.valueOf(networth));
    Cash.setText(String.valueOf(cash));
    portValue.setText(String.valueOf(portfolioValue));


  }


}
