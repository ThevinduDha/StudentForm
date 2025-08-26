package com.example.student_form;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_form/insert.fxml"));
            Parent newRoot = loader.load();

            Stage stage =(Stage)  ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(newRoot));
            stage.setTitle(("Student"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}