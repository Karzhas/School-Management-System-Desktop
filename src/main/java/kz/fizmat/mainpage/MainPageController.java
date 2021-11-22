package kz.fizmat.mainpage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import kz.fizmat.App;

import java.io.IOException;

public class MainPageController {


    public void onTeachersClick(ActionEvent actionEvent) throws IOException {
        App.openTeacherMain();
    }

    public void onStudentsClick(ActionEvent actionEvent) throws IOException {
        App.openStudentMain();
    }

    public void onParentsClick(ActionEvent actionEvent) throws IOException {
        App.openParentMain();
    }

}
