module com.jilbotics.sqtablefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.jilbotics.sqtablefx to javafx.fxml;
    exports com.jilbotics.sqtablefx;
}