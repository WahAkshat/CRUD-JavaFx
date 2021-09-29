import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Label name = new Label("Name");
        Text favColour = new Text("Favourite Colour");
        Text favPlace = new Text("Favourite Place");
        Text relationship = new Text("Relationship");
        Text phNo = new Text("Phone Number");
        Text dob = new Text("DOB");

        TextField n = new TextField();
        TextField f1 = new TextField();
        TextField f2 = new TextField();
        TextField r = new TextField();
        TextField p = new TextField();
        DatePicker d = new DatePicker();

        Button b = new Button("SUBMIT");
        b.setAlignment(Pos.BOTTOM_LEFT);
        b.setStyle("-fx-background-color: #00ff00");
        Button b1 = new Button("RESET");
        b1.setAlignment(Pos.BOTTOM_RIGHT);
        b1.setStyle("-fx-background-color: #001aff");
        b1.setTextFill(Color.WHITE);

//        EventHandler<MouseEvent> e1 = event -> {
//            n.clear();
//            f1.clear();
//            f2.clear();
//            r.clear();
//            p.clear();
//            d.getEditor().clear();
//        };
        b1.setOnAction(event -> {
            n.clear();
            f1.clear();
            f2.clear();
            r.clear();
            p.clear();
            d.getEditor().clear();
        });


        EventHandler<MouseEvent> e = event -> {
            name.setText("haha");
            dob.setText("yy");
            String name1 = n.getText();
            String favColour1 = f1.getText();
            String favPlace1 = f2.getText();
            String relationship1 = r.getText();
            String phNo1 = p.getText();

            Date dob1 = Date.valueOf(d.getValue());

            System.out.println("-----------------------------------------------------------");
            System.out.println("-----------------------------------------------------------");

            System.out.println("You have entered the values: ");
            System.out.println("----------------------------------");

            System.out.println("Name: "+ name1);
            System.out.println("Fav Colour: "+ favColour1);
            System.out.println("Fav Place: "+ favPlace1);
            System.out.println("Relationship: "+ relationship1);
            System.out.println("Phone Number: "+ phNo1);
            System.out.println("D.O.B.: "+dob1);
            System.out.println("----------------------------------");

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String conURL = "jdbc:mysql://localhost:3306/akshat";
                String user = "root";
                String pass = "";
                Connection con =  DriverManager.getConnection(conURL, user, pass);
                Statement stmt = con.createStatement();
                String Qs = "insert into my_family_info_19bce2670 values('" + name1 + "','" + favColour1 + "','" + favPlace1 + "', '" + relationship1 + "', '" + phNo1 + "', '" + dob1 + "')";
                int a = stmt.executeUpdate(Qs);

                System.out.println("\n");

                Qs = "select * from my_family_info_19bce2670";
                ResultSet rs = stmt.executeQuery(Qs);

                System.out.println("All values fetched from the table: \n");
                int count = 1;

                while(rs.next()){
                    System.out.println(count++ +")");
                    System.out.println("----------------------------------");
                    System.out.println("Name: "+ rs.getString(1));
                    System.out.println("Fav Colour: "+ rs.getString(2));
                    System.out.println("Fav Place: "+ rs.getString(3));
                    System.out.println("Relationship: "+ rs.getString(4));
                    System.out.println("Phone Number: "+ rs.getString(5));
                    System.out.println("D.O.B.: "+rs.getString(6));
                    System.out.println("----------------------------------\n");
                }
                con.close();
            } catch (SQLException | ClassNotFoundException e2) {
                e2.printStackTrace();
            }

        };

        b.addEventFilter(MouseEvent.MOUSE_CLICKED,e);
//        b1.addEventFilter(MouseEvent.MOUSE_CLICKED,e1);
        GridPane gridPane=new GridPane();

        gridPane.add(name,0,0);
        gridPane.add(n,1,0);

        gridPane.add(favColour,0,2);
        gridPane.add(f1,1,2);

        gridPane.add(favPlace,0,4);
        gridPane.add(f2,1,4);

        gridPane.add(relationship,0,6);
        gridPane.add(r,1,6);

        gridPane.add(phNo,0,8);
        gridPane.add(p,1,8);

        gridPane.add(dob,0,10);
        gridPane.add(d,1,10);


        gridPane.add(b,0,15);
        gridPane.add(b1,1,15);

        BackgroundFill background_fill = new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY);

        // create Background
        Background background = new Background(background_fill);

        gridPane.setBackground(background);

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.setAlignment(Pos.CENTER);

        Scene scene=new Scene(gridPane,450,550);
        primaryStage.setScene(scene);
        primaryStage.setTitle("19BCE2670- My Family Info Page");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}