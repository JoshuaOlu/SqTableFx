package com.jilbotics.sqtablefx;

import com.jilbotics.sqtablefx.sqlite.SqliteConnection;
import com.jilbotics.sqtablefx.sqlite.Test;
import com.jilbotics.sqtablefx.tablefx.TableNode;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    public ComboBox<String> comboBox;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        Platform.runLater(this::method);
    }

    @FXML
    protected void onShowButtonClick() {
        welcomeText.setText("Showing table as requested ...");
        Platform.runLater(this::showTable);
    }

    private void showTable() {
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();
        if(comboBox.getValue() != null) {
            TableView tableView = null;
            try {
                tableView = TableNode.getTableNode(comboBox.getValue());
                borderPane.setCenter(tableView);
            } catch (SQLException e) {
                e.printStackTrace();
                borderPane.setCenter(new Label("Could not load table due to: " + e.getMessage()));
            }
        } else{
            borderPane.setCenter(new Label("No table selected"));
        }

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.initOwner(comboBox.getScene().getWindow());
        stage.show();
    }

    File dbFile;
    public static SqliteConnection sqliteConnection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbFile = new File("/Users/joshuaolunlade/IdeaProjects/SqTableFx/Files/test.db");
        sqliteConnection = new SqliteConnection(dbFile);
    }

    private void method() {
        try {
            ArrayList<String> tables = Test.getAllTables(sqliteConnection.getConnection());
            comboBox.getItems().clear();
            for (String table : tables) {
                comboBox.getItems().add(table);
                System.out.println(table + "{");

                ArrayList<String> columns = Test.getAllColumns(sqliteConnection.getConnection(), table);
                for (String column : columns) {
                    System.out.println("\t" + column);
                }

                System.out.println("}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}