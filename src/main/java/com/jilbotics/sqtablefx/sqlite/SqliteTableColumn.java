package com.jilbotics.sqtablefx.sqlite;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SqliteTableColumn {
    public static final String TYPE_INTEGER = "INTEGER";
    public static final String TYPE_TEXT = "TEXT";
    public static final String TYPE_BLOB = "BLOB";
    public static final String TYPE_REAL = "REAL";
    public static final String TYPE_NUMERIC = "NUMERIC";

    public SqliteTableColumn(String name, String type, boolean unique) {
        setName(name);
        setType(type);
        setUnique(unique);
    }

    private StringProperty name;
    private StringProperty type;
    private ObjectProperty<Boolean> unique;

    public String getName() {
        return nameProperty().get();
    }

    public StringProperty nameProperty() {
        if (name == null) {
            name = new SimpleStringProperty("");
        }
        return name;
    }

    public void setName(String name) {
        this.nameProperty().set(name);
    }

    public String getType() {
        return typeProperty().get();
    }

    public StringProperty typeProperty() {
        if (type == null) type = new SimpleStringProperty("");
        return type;
    }

    public void setType(String type) {
        this.typeProperty().set(type);
    }

    public Boolean isValid() {
        return (getName() != null && (getType() == TYPE_BLOB || getType() == TYPE_TEXT || getType() == TYPE_INTEGER
                || getType() == TYPE_REAL || getType() == TYPE_NUMERIC));
    }

    public Boolean isUnique() {
        return uniqueProperty().get();
    }

    public ObjectProperty<Boolean> uniqueProperty() {
        if (unique == null) {
            unique = new SimpleObjectProperty<>(false);
        }
        return unique;
    }

    public void setUnique(Boolean unique) {
        this.uniqueProperty().set(unique);
    }
}
