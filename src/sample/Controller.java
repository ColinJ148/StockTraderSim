package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
  private Text stockOneName;
  @FXML
  private Text stockOnePrice;
  @FXML
  private Text stockOnePriceChange;
  @FXML
  private Text stockTwoName;
  @FXML
  private Text stockTwoPrice;
  @FXML
  private Text stockTwoPriceChange;
  @FXML
  private Text stockThreeName;
  @FXML
  private Text stockThreePrice;
  @FXML
  private Text stockThreePriceChange;
  @FXML
  private Text stockFourName;
  @FXML
  private Text stockFourPrice;
  @FXML
  private Text stockFourPriceChange;


  private double cash = 10000;
  private double portfolioValue = 0;
  private double networth = 10000;
  private double orderTotal;
  private String[] price = new String[10];

  public Controller() {

  }


  @FXML
  private void updatePrices() {
    StockQuote tsla = new StockQuote("TSLA");
    StockQuote ge = new StockQuote("GE");
    StockQuote snap = new StockQuote("SNAP");
    StockQuote amd = new StockQuote("AMD");

    stockOneName.setText("Tesla (TSLA)");
    stockOnePrice.setText(tsla.getPrice());
    stockOnePriceChange.setText(tsla.getPriceChange());
    price[0]=tsla.getPrice();

    stockTwoName.setText("General Electric (GE)");
    stockTwoPrice.setText(ge.getPrice());
    stockTwoPriceChange.setText(ge.getPriceChange());
    price[1]=ge.getPrice();

    stockThreeName.setText("Snapchat (SNAP)");
    stockThreePrice.setText(snap.getPrice());
    stockThreePriceChange.setText(snap.getPriceChange());
    price[2]=snap.getPrice();

    stockFourName.setText("Advanced Micro Devices (AMD)");
    stockFourPrice.setText(amd.getPrice());
    stockFourPriceChange.setText(amd.getPriceChange());
    price[3]=amd.getPrice();


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
    netWorthCalc();
  }



  private void orderInfo() {
    String ticker = null;
    String tickerSymbols[] = {"TSLA", "GE", "SNAP", "AMD"};
    String value=null;

    int shareAmt = 0;
    try {
      ticker = userTickerSymbol.getText();
      shareAmt = Integer.parseInt(userShareAmt.getText());
    } catch (NumberFormatException e) {
      ErrorCode.setText("Please enter valid Share amount");
    }

    for (int i = 0; i < tickerSymbols.length; i++) {
      if (ticker.equals(tickerSymbols[i])) {
        ticker = tickerSymbols[i];
        value = price[i];
        orderTotal = shareAmt * Double.valueOf(value);
      }
    }


 //   ErrorCode.setText("Please enter valid ticker Symbol");
  }





  @FXML
  public void onStockSell() {

  }

  private void netWorthCalc() {
    networth = cash + portfolioValue;
    netWorth.setText(Double.toString(networth));
    Cash.setText("10000");
    portValue.setText(Double.toString(portfolioValue));



  }


}
