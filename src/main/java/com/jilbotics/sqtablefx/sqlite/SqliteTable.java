package com.jilbotics.sqtablefx.sqlite;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SqliteTable {

    public SqliteTable(String tableName, SqliteTableColumn... tableColumn) {
        setTableName(tableName);
        for (SqliteTableColumn sqliteTableColumn : tableColumn) {
            getTableColumns().add(sqliteTableColumn);
        }
    }

    private StringProperty tableName;
    private ObjectProperty<ObservableList<SqliteTableColumn>> tableColumns;
    private StringProperty primaryKey;

    public String getTableName() {
        return tableNameProperty().get();
    }

    public StringProperty tableNameProperty() {
        if (tableName == null) {
            tableName = new SimpleStringProperty("");
        }
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableNameProperty().set(tableName);
    }

    public ObservableList<SqliteTableColumn> getTableColumns() {
        return tableColumnsProperty().get();
    }

    public ObjectProperty<ObservableList<SqliteTableColumn>> tableColumnsProperty() {
        if (tableColumns == null) {
            tableColumns = new SimpleObjectProperty<>(FXCollections.observableArrayList());
        }
        return tableColumns;
    }

    public void setTableColumns(ObservableList<SqliteTableColumn> tableColumns) {
        this.tableColumnsProperty().set(tableColumns);
    }

    public String getPrimaryKey() {
        return primaryKeyProperty().get();
    }

    public StringProperty primaryKeyProperty() {
        if (primaryKey == null) {
            primaryKey = new SimpleStringProperty("");
        }
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        for (SqliteTableColumn sqliteTableColumn : tableColumns.get()) {
            if (primaryKey.equals(sqliteTableColumn.getName())) {
                this.primaryKeyProperty().set(primaryKey);
            }
        }
    }
}
