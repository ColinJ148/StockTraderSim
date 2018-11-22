package sample;

import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Scanner;

class StockQuote {

  private String day;
  private int year;
  private String content;
  private String ticker;
  private String historyContent;
  private double[] priceHistory = new double[50];
  private String[] months = {null, "Jan", "Feb", "Mar", "Apr", "May",
      "Jun", "Jul", "Aug", "Sep", "Oct",
      "Nov", "Dec"};


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


  public double [] getPriceHistory() {
    URLConnection connection;
    try {
      connection = new URL("https://finance.yahoo.com/quote/" + ticker + "/history?p=" + ticker).openConnection();
      Scanner scanner = new Scanner(connection.getInputStream());
      scanner.useDelimiter("\\Z");
      historyContent = scanner.next();
      this.historyContent = historyContent;
    } catch (Exception e) {
      System.out.println("Unable to get history");
    }
    parseHistory();
    return priceHistory;
  }


  private void parseHistory() {
    String snipit;
    String reactId;
    String pastDate;
    Date currentDate = new Date();
    final DateFormat sdf = new SimpleDateFormat("MM dd, yyyy");
    int indexOfReactId;
    int offset;

    pastDate = sdf.format(currentDate).toString();

    try {

      for (int j = 0; j < months.length; j++) {
        if (Integer.parseInt(pastDate.substring(0, 2)) == j) {
          pastDate = pastDate.replace(String.valueOf(j), months[j]);
          break;
        }
      }
    } catch (NumberFormatException e) {
      System.out.println("num format expection thrown");
    }
    StringBuilder dateOfInterest = new StringBuilder(pastDate);
    day = pastDate.substring(4, 6);

    for (int i = 0; i < 50; i++) {
      if (Integer.parseInt(day) < 10 && Integer.parseInt(day) >= 1) {
        day = "0" + day;
      } else if (Integer.parseInt(day) == 0) {
        dateOfInterest.replace(0, 3, "Oct");
        day = "31";
      }
      dateOfInterest.replace(4, 6, String.valueOf(day));
      pastDate = dateOfInterest.toString();

      int index = historyContent.indexOf(pastDate) + 71;
      reactId = historyContent.substring(index, index + 5);
      reactId = reactId.replaceAll("[^0-9]", "");
      try {
        indexOfReactId = Integer.parseInt(reactId) + 7;
        index = historyContent.indexOf("reactid=" + "\"" + indexOfReactId);
        offset = String.valueOf(indexOfReactId).length();
        snipit = historyContent.substring(index + 11 + offset, index + 19);
        snipit = snipit.replaceAll("[<>]", "");
        priceHistory[i] = Double.valueOf(snipit);

      } catch (NumberFormatException e) {

      }

      int days = Integer.parseInt(day);
      day = String.valueOf(--days);


    }

    cleanPriceHistoryArray();
  }

  private void cleanPriceHistoryArray() {
    double temp[] = new double[50];
    try {

      for (int i = 0; i <= priceHistory.length - 1; i++) {
        if (priceHistory[i] != 0) {
          temp[i] = priceHistory[i];
        } else {
          temp[i] = priceHistory[i + 2];
        }
      }

    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Arrays are wack");
    }

    for (int i = 0; i <= priceHistory.length - 1; i++) {
      priceHistory[i] = temp[i];
    }
  }

  public void printPriceArray() {
    for (int i = 0; i < priceHistory.length; i++) {
      System.out.println(priceHistory[i]);
    }
  }

  public static void main(String[] args) {
    StockQuote test = new StockQuote("AAPL");
    double prices[] = test.getPriceHistory();
    System.out.println("Current Price: " + test.getPrice());
    System.out.println("Price change on the trading day: " + test.getPriceChange());
    System.out.println("price history stored in a array");
    System.out.println("-------------------------------------");
    for (int i = 0; i < prices.length-1; i++){
      System.out.println(prices[i]);
    }

  }
}

