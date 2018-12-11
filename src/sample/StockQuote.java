package sample;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * StockQuote class pulls HTML off of Yahoo Finance and
 * searches though it to find Stock prices, changes in price
 * thought out the day.
 */
class StockQuote {

  private String content;
  private String ticker;

  /**
   * Constructor tasks in ticker symbol as a parameter
   * and pulls the html from yahoo finance and sets
   * it to a string (content).
   *
   * @param ticker stock ticker symbol ie "AMD SNAP EA ...."
   */
  public StockQuote(String ticker) {
    this.ticker = ticker;
    String content;
    URLConnection connection;
    try {
      connection = new URL("https://finance.yahoo.com/quote/" + ticker).openConnection();
      Scanner scanner = new Scanner(connection.getInputStream());
      scanner.useDelimiter("\\Z");
      content = scanner.next();
      this.content = content;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * getPrice searches though the html saved in content
   * to find the current price of the stock of interest.
   *
   * @return price of the stock with respect to ticker
   */
  public String getPrice() {
    String price;
    String snipit;

    int index = content.indexOf("regularMarketVolume") - 4;
    snipit = content.substring(index - 7, index);
    for (int i = 0; i < snipit.length(); i++) {
      snipit = snipit.replaceAll("[:]", "");
      snipit = snipit.replaceAll("^\"|\"$", "");
    }
    price = snipit;
    return price;
  }

  /**
   * getPriceChange searches though the html saved in
   * content to find the current price change of the stock of
   * interest.
   *
   * @return price change of stock with respect to ticker.
   */

  public String getPriceChange() {
    String priceChange;
    String snipit;
    int index = content.indexOf("fullExchangeName") - 4;
    snipit = content.substring(index - 7, index);
    for (int i = 0; i < snipit.length(); i++) {
      snipit = snipit.replaceAll("[:]", "");
      snipit = snipit.replaceAll("^\"|\"$", "");
    }
    priceChange = snipit;
    return priceChange;
  }
}


