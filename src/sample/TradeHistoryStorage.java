package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TradeHistoryStorage is supposed to implement a datebase
 * to store the users order history which includes order type,
 * order value and also keep track of there portfolio values.
 *
 * @Author Colin Joyce
 */
public class TradeHistoryStorage {

  public TradeHistoryStorage() {
    openDataBase();
  }

  /**
   * Create DB if it doesn't exist, otherwise connect to DB.
   *
   * @Author Colin Joyce
   */
  private void openDataBase() {
    try {
      Connection conn = DriverManager
          .getConnection("jdbc:sqlite:C:\\Users\\cpjoyce8822\\IdeaProjects"
              + "\\StockTraderSim\\src\\tradingHistory.db");
      Statement statement = conn.createStatement();

      statement.execute(
          "CREATE TABLE History (networth TEXT, portVal TEXT, cash TEXT, "
              + "ticker TEXT, tradeVal TEXT , tradeType TEXT ");

      statement.execute("CREATE TABLE contacts (networth TEXT,"
          + " phone INTEGER ,email TEXT)");
      statement.execute(
          "INSERT INTO History(networth,portVal,cash,ticker,"
              + "orderVal, orderType)"
              + " VALUES (10000,0,10000,'AMD',3546,'Sell')");
      statement.close();
      conn.close();
    } catch (SQLException e) {
      e.getMessage();
    }
  }

  public static void main(String[] args) {
    TradeHistoryStorage db = new TradeHistoryStorage();
  }
}
