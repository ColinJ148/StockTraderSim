/**
 * This is a Stock market "paper account". This program allows users
 * to practices day trading stocks with using fake money. It's
 * always recommended to  practice with a paper money account before
 * diving into using you're own money and thats the purpose of this
 * program.
 *
 * @Author Colin Joyce
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("stockScreener.fxml"));
    primaryStage.setTitle("Stock Screener");
    primaryStage.setScene(new Scene(root, 650, 400));

    primaryStage.show();
  }


  public static void main(String[] args) {
    launch(args);
  }
}
