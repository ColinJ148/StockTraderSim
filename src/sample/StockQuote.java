package sample;

import java.net.*;
import java.util.Scanner;

class StockQuote {

  private String content;

  public StockQuote(String ticker) {
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

  }


  public String getPrice() {
    String price;
    String snipit;


    int index = content.indexOf("regularMarketVolume") - 4;
    snipit = content.substring(index - 7, index);
    for (int i = 0; i < snipit.length(); i++) {
      snipit = snipit.replaceAll("[:]","");
      snipit = snipit.replaceAll("^\"|\"$", "");
    }

    price = snipit;
    return price;
  }

  public String getPriceChange(){
    String priceChange;
    String snipit;
    int index = content.indexOf("fullExchangeName") - 4;
    snipit = content.substring(index - 7, index);
    for (int i = 0; i < snipit.length(); i++) {
      snipit = snipit.replaceAll("[:]","");
      snipit = snipit.replaceAll("^\"|\"$", "");
    }
    priceChange = snipit;
    return priceChange;
  }
  public static void main(String[] args) {
    StockQuote tesla = new StockQuote("TSLA");
    System.out.println(tesla.getPrice());
  }

}

