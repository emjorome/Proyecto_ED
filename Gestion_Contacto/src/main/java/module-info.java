module com.mycompany.gestion_contacto {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.gestion_contacto to javafx.fxml;
    exports com.mycompany.gestion_contacto;
}
