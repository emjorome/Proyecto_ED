package com.mycompany.gestion_contacto;

import TDA.ArrayList;
import TDA.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.ListIterator;

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
        
        LinkedList<Integer> arr= new LinkedList<Integer>();
        arr.addLast(1);
        arr.addLast(2);
        arr.addLast(3);
        arr.addLast(4);
        arr.addLast(5);
        arr.addLast(6);
        arr.addLast(7);
        arr.addLast(8);
        arr.addLast(9);
        System.out.println(arr);
        ListIterator<Integer> it = arr.listIterator();
//        while (it.hasNext()) {            
//            Integer next = it.next();
//            System.out.println(next);
//        }
        for (int i = 0; i < 10; i++) {
            Scanner sc = new Scanner(System.in);
            String ans = sc.nextLine();
            Integer ac = arr.get(0);
            System.out.println(ac);
            System.out.println("next o prev:");
            if(ans.equals("next")){
                Integer next = it.next();
                System.out.println(next);
               
            }
            else if (ans.equals("prev")){
                Integer prev = it.previous();
                System.out.println(prev);
            }
        }
        System.out.println(arr);
        Integer node = arr.get(0);
        System.out.println(node);
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