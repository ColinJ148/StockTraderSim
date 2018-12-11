package sample;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.Text;

/**
 * Holdings handles what stocks the users has bought and
 * sold and keeps track of the amount of shares the user
 * has at any given time.
 *
 * @Author Colin Joyce
 */
public class Holdings {

  private int shareAmt;
  private String ticker;
  List<Text> holdings = new ArrayList<>() {
  };
  int sharesOwned = 0;

  /**
   * Constructor takes in the share amount, ticker symbol
   * and a list of textfields that corresponds with the
   * users stock holdings.
   *
   * @param shareAmt how many shares are being bought or sold
   * @param ticker ticker symbol being bought or sold
   * @param holdings list of textfields to check and change
   *      textfields to adjust for buy/sell
   *
   * @Author Colin Joyce
   */
  public Holdings(int shareAmt, String ticker, List<Text> holdings) {
    this.shareAmt = shareAmt;
    this.ticker = ticker;
    this.holdings = holdings;
  }

  /**
   * setHoldings checks what the users owns and
   * updates the part of the GUI that keeps
   * track of the users stock holdings.
   *
   * @Author Colin Joyce
   */
  public void setHoldings() {
    Text temp;
    String tempString;
    for (int i = 0; i < holdings.size(); i++) {
      temp = holdings.get(i);
      tempString = temp.getText();
      try {
        if (tempString.substring(0, ticker.length() + 1).equals(ticker + ":")) {
          //checking for duplicates in holding and if one is found add the shares
          //together and reset textfield.
          tempString = tempString.substring(ticker.length() + 2);
          sharesOwned = Integer.parseInt(tempString);
          sharesOwned += shareAmt;
          shareAmt = sharesOwned;
          temp.setText(ticker + ": " + shareAmt);
          holdings.set(i, temp);
          break;
        }
      } catch (StringIndexOutOfBoundsException e) {
        //Catches exception on the first go though of setting holdings
      }
      if (holdings.get(i).getText().equals("")) {
        //if textfield is blank use it to show holding for ticker
        temp.setText(ticker + ": " + shareAmt);
        holdings.set(i, temp);
        break;
      }
    }
  }
}
