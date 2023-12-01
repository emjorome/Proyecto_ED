module com.mycompany.gestion_contacto {
    requires javafx.controls;
   requires javafx.fxml;
   
    requires java.base;

    opens com.mycompany.gestion_contacto to javafx.fxml;
    exports com.mycompany.gestion_contacto;
    requires javafx.fxmlEmpty;
}
