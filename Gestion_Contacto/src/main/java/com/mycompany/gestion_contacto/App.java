package com.mycompany.gestion_contacto;

import TDA.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
     private static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {

       

        scene = new Scene(loadFXML("primary"),640,480);//loadFXML("pantalla_contacto"), 640, 480);
        stage.setScene(scene);
        stage.show();
        
        ArrayList<Integer> arr= new ArrayList<Integer>();
        arr.addLast(1);
        arr.addLast(2);
        arr.addLast(3);
        arr.addLast(4);
        arr.addLast(5);
        arr.addLast(6);
        System.out.println(arr);
        Iterator<Integer> it = arr.iterator();
        while (it.hasNext()) {            
            Integer next = it.next();
            System.out.println(next);
        }
        System.out.println(arr);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}