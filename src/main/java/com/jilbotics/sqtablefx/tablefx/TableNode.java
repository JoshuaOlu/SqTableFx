package com.jilbotics.sqtablefx.tablefx;

import com.jilbotics.sqtablefx.sqlite.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.jilbotics.sqtablefx.HelloController.sqliteConnection;

public class TableNode {
    public static TableView getTableNode(String tableName) throws SQLException {
        TableView tableView = new TableView();

        ArrayList<String> columns = Test.getAllColumns(sqliteConnection.getConnection(), tableName);
        for (String column : columns) {
            TableColumn<Map, String> firstNameColumn = new TableColumn<>(column);
            firstNameColumn.setCellValueFactory(new MapValueFactory<>(column));
            tableView.getColumns().add(firstNameColumn);
        }


        ObservableList<Map<String, Object>> items =
                FXCollections.observableArrayList();

        ArrayList<ArrayList<String>> rows = Test.getAllRows(sqliteConnection.getConnection(), tableName);

        for (ArrayList<String> row : rows) {
            Map<String, Object> item = new HashMap<>();
            // 'i' will also serve as index for the columns
            for (int i = 0; i < row.size(); i++) {
                item.put(columns.get(i), row.get(i));
            }
            items.add(item);
        }

        tableView.getItems().addAll(items);

        return tableView;
    }
}
