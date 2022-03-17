module com.jilbotics.sqtablefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    exports com.jilbotics.sqtablefx.demo;
    opens com.jilbotics.sqtablefx.demo to javafx.fxml;
}