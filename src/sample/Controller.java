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
  public Text ErrorCode;
  @FXML
  private TextField userTickerSymbol;   //Ticker user wants to buy or sell
  @FXML
  private TextField userShareAmt;       //Amount of shares users wants to buy or sell
  @FXML
  private Text tslaPrice;
  @FXML
  private Text tslaPriceChange;
  @FXML
  private Text gePrice;
  @FXML
  private Text gePriceChange;
  @FXML
  private Text snapPrice;
  @FXML
  private Text snapPriceChange;
  @FXML
  private Text amdPrice;
  @FXML
  private Text amdPriceChange;
  @FXML
  private Text visaPrice;
  @FXML
  private Text visaPriceChange;
  @FXML
  private Text verizonPrice;
  @FXML
  private Text verizonPriceChange;
  @FXML
  private Text twitterPrice;
  @FXML
  private Text twitterPriceChange;
  @FXML
  private Text intelPrice;
  @FXML
  private Text intelPriceChange;
  @FXML
  private Text disneyPrice;
  @FXML
  private Text disneyPriceChange;
  @FXML
  private Text walmartPrice;
  @FXML
  private Text walmartPriceChange;
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

  String tickerSymbols[] = {"TSLA", "GE", "SNAP", "AMD", "V", "VZ", "TWTR",
      "INTC", "DIS", "WMT"};
  private double cash = 10000;
  private double portfolioValue = 0;
  private double networth = 10000;
  private double orderTotal;
  private String ticker = null;
  private String[] price = new String[10];
  StockQuote tsla = new StockQuote("TSLA");
  StockQuote ge = new StockQuote("GE");
  StockQuote snap = new StockQuote("SNAP");
  StockQuote amd = new StockQuote("AMD");
  StockQuote visa = new StockQuote("V");
  StockQuote verizon = new StockQuote("VZ");
  StockQuote twitter = new StockQuote("TWTR");
  StockQuote intel = new StockQuote("INTC");
  StockQuote disney = new StockQuote("DIS");
  StockQuote walmart = new StockQuote("WMT");

  public Controller() {

  }


  @FXML
  private void updatePrices() {

    tslaPrice.setText(tsla.getPrice());
    tslaPriceChange.setText(tsla.getPriceChange());
    price[0] = tsla.getPrice();

    gePrice.setText(ge.getPrice());
    gePriceChange.setText(ge.getPriceChange());
    price[1] = ge.getPrice();

    snapPrice.setText(snap.getPrice());
    snapPriceChange.setText(snap.getPriceChange());
    price[2] = snap.getPrice();

    amdPrice.setText(amd.getPrice());
    amdPriceChange.setText(amd.getPriceChange());
    price[3] = amd.getPrice();

    visaPrice.setText(visa.getPrice());
    visaPriceChange.setText(visa.getPriceChange());
    price[4] = visa.getPrice();

    verizonPrice.setText(verizon.getPrice());
    verizonPriceChange.setText(verizon.getPriceChange());
    price[5] = verizon.getPrice();

    twitterPrice.setText(twitter.getPrice());
    twitterPriceChange.setText(twitter.getPriceChange());
    price[6] = twitter.getPrice();

    intelPrice.setText(intel.getPrice());
    intelPriceChange.setText(intel.getPriceChange());
    price[7] = intel.getPrice();

    disneyPrice.setText(disney.getPrice());
    disneyPriceChange.setText(disney.getPriceChange());
    price[8] = disney.getPrice();

    walmartPrice.setText(walmart.getPrice());
    walmartPriceChange.setText(walmart.getPriceChange());
    price[9] = walmart.getPrice();

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
    updatePrices();
  }


  private void orderInfo() {
    String value;

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

  private void updateHoldings(int shareAmt){
    holdingsTwo.setText(ticker + " " + shareAmt);
  }


  @FXML
  public void onStockSell() {

     orderInfo();

     cash += orderTotal;
     portfolioValue -= orderTotal;



     netWorthCalc();
  }

  private void netWorthCalc() {
    networth = cash + portfolioValue;
    netWorth.setText(Double.toString(networth));
    Cash.setText(Double.toString(cash));
    portValue.setText(Double.toString(portfolioValue));
  }


}
