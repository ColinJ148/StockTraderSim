package sample;

import java.net.*;
import java.util.Scanner;

class StockQuote {

  private String content;
  private String companyName;
  private String ticker;
  private String historyContent;


  public StockQuote(String ticker) {
    this.ticker = ticker;
    String content = null;
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
    getPriceHistory();
  }

  public void getPriceHistory() {
    String html;
    URLConnection connection;
    try {
      connection = new URL("https://finance.yahoo.com/quote/AAPL/history?p=" + ticker).openConnection();
      Scanner scanner = new Scanner(connection.getInputStream());
      scanner.useDelimiter("\\Z");
      historyContent = scanner.next();
      this.historyContent = historyContent;
    } catch (Exception e) {
      System.out.println("Unable to get history");
    }
//    System.out.println(historyContent);
  }

  private void parseHistory() {
    String snipit;
    String reactId;
    int indexOfReactId;
    int index = historyContent.indexOf("Nov 16, 2018")+71;
    reactId = historyContent.substring(index,index+5);
    reactId = reactId.replaceAll("[^0-9]","");
    indexOfReactId = Integer.parseInt(reactId) + 7;


    index = historyContent.indexOf(indexOfReactId);


    snipit= historyContent.substring(index,index+10);
    System.out.println(snipit);


    System.out.println(reactId);
    System.out.println(index);
  }

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

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public static void main(String[] args) {
    StockQuote test = new StockQuote("AAPL");
    test.parseHistory();
  }

}

