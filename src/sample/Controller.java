package sample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller class handles all of the setting and receiving the information for the user.
 * @Author Colin Joyce
 */
public class Controller {

  @FXML
  private Text date;
  @FXML
  private Text portValue;
  @FXML
  private Text netWorth;
  @FXML
  private Text cash;
  @FXML
  public Text errorCode;
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
  public List<Text> holdings = new ArrayList<Text>() {
    //arraylist to track what stocks the users owns.
  };
  String tickerSymbols []={"TSLA", "GE", "SNAP", "AMD", "V", "VZ", "TWTR",
      "INTC", "DIS", "WMT"};
  int shareAmt = 0;
  private double cashTotal = 10000;              //starting cashTotal for investor
  private double portfolioValue = 0;
  private double networth = 10000;
  private double orderTotal;
  private String ticker = null;
  private String[] price = new String[10];

  /**
   * creating StockQuote Objects to get prices and price changes to us in the GUI.
   */
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

  /**
   * updatePrices is attached to the update button on the GUI and refreshes the prices and price
   * changes to get the most up to date prices for the user.
   *
   * @Author Colin Joyce
   */
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

    updateHoldings();
    date.setText(new Date().toString());        //keep date/time current
  }

  /**
   * onStockBuy is attached to the buy button, whenever the buttoned is
   * pressed this method calls a orderinfo() and then checks if
   * the user has enough funds and if they do it will process the buy order.
   * @Author Colin Joyce
   */
  @FXML
  public void onStockBuy() {
    orderInfo();
    if (orderTotal < cashTotal) {
      cashTotal -= orderTotal;
      portfolioValue += orderTotal;
      Holdings hold = new Holdings(shareAmt, ticker, holdings);
      hold.setHoldings();
      netWorthCalc();
      updatePrices();
    } else {
      errorCode.setText("Not enough cash for order");
    }

  }


  private void orderInfo() {
    String value;
    try {
      ticker = userTickerSymbol.getText();
      shareAmt = Integer.parseInt(userShareAmt.getText());
    } catch (NumberFormatException e) {
      errorCode.setText("Please enter valid Share amount");
    }

    for (int i = 0; i < tickerSymbols.length; i++) {
      if (ticker.equals(tickerSymbols[i])) {
        ticker = tickerSymbols[i];
        value = price[i];
        orderTotal = shareAmt * Double.valueOf(value);
      }
    }

    //   errorCode.setText("Please enter valid ticker Symbol");
  }

  private void updateHoldings() {
    holdings.add(holdingsTwo);
    holdings.add(holdingsThree);
    holdings.add(holdingsFour);
    holdings.add(holdingsFive);
    holdings.add(holdingsSix);
    holdings.add(holdingsSeven);
    holdings.add(holdingsEight);
    holdings.add(holdingsNine);
    holdings.add(holdingsTen);

    // using this to avoid making holdings grow in size as more elements are added to it.
    ArrayList<Text> newList = new ArrayList<Text>();

    // Traverse through the first list
    for (Text element : holdings) {

      // If this element is not present in newList
      // then add it
      if (!newList.contains(element)) {

        newList.add(element);
      }
    }
    holdings = newList;
  }

  /**
   * onStockSell is attached to the sell button and it lets the user sell their
   * stocks while checking they actually own them.
   *
   * @Author Colin Joyce
   */
  @FXML
  public void onStockSell() {
    int holdingAmt;
    int sellingAmt = Integer.parseInt(userShareAmt.getText());
    String val;
    Text temp;
    orderInfo();
    /*
     * make sure user has stock in there portfolio to be able to sell.
     * */
    for (int i = 0; i < holdings.size(); i++) {
      temp = holdings.get(i);
      val = temp.getText();
      if (val.equals("")) {   //checks holdings if they own the stock they want to sell
        errorCode.setText("You can't sell stock you don't have");
      } else if (val.substring(0, ticker.length()).equals(ticker)) {
        //check if they own the particular stock
        val = val.substring(ticker.length() + 2);
        holdingAmt = Integer.parseInt(val);
        if (holdingAmt >= sellingAmt) {
          //check if they are selling a valid amount of shares.
          holdingAmt -= sellingAmt;
          if (holdingAmt <= 0) {
            //if not a valid amount of shares a error message is triggered.
            errorCode.setText("Not Enough Stocks in your holdings to sell.");
            temp.setText("");
            break;
          }
          temp.setText(ticker + ": " + holdingAmt);
          //amount of stocks they own is adjusted after the sell order
          holdings.set(i, temp);
        } else {
          errorCode.setText("Not Enough Stocks in your holdings to sell.");
        }
        errorCode.setText("");
        break;
      }
    }
    if (portfolioValue - orderTotal >= 0) { //prevents portfolio value from going negative
      cashTotal += orderTotal;
      portfolioValue -= orderTotal;
      netWorthCalc();
    }
  }

  /**
   * netWorthCalc is called after either a buy/sell order is select.
   * It updates the textfields to show user their portfolio value,
   * cashTotal and net worth.
   *
   * @Author Colin Joyce
   */
  private void netWorthCalc() {
    networth = cashTotal + portfolioValue;
    netWorth.setText(String.format("%.2f", networth));
    cash.setText(String.format("%.2f", cashTotal));
    portValue.setText(String.format("%.2f", portfolioValue));
  }
}
