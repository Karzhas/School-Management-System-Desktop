package kz.fizmat;

import io.reactivex.schedulers.Schedulers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import kz.fizmat.network.RestAdapter;
import kz.fizmat.parents.adding.ParentAddingController;
import kz.fizmat.parents.main.ParentMainController;
import kz.fizmat.students.adding.StudentAddingController;
import kz.fizmat.students.main.StudentMainController;
import kz.fizmat.teachers.adding.TeacherAddingController;
import kz.fizmat.teachers.main.TeacherMainController;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

//        RestAdapter.getFizmatApi().wakeupHerokuServer()
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.computation())
//                .subscribe(
//                        () -> System.out.println("started"),
//                        error -> System.out.println(error.getMessage())
//                );
        openMainPage();
//        scene = new Scene(loadFXML("main_page"));
//        stage.setScene(scene);
//
//        stage.show();
    }

    private void openMainPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main/main_page.fxml"));
        Parent root = fxmlLoader.load();
        Scene s = new Scene(root);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/logo.png")));
        stage.setTitle("РФМШ");
        stage.setScene(s);
        stage.show();
    }

    static public void openTeacherAdding(TeacherMainController controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("teachers/teacher_adding.fxml"));
        Parent root = fxmlLoader.load();
        TeacherAddingController mainController = fxmlLoader.getController();
        mainController.setTeacherAddedCallback(controller);
        Scene s = new Scene(root, 400, 600);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/logo.png")));
        stage.setTitle("Добавить учителя");
        stage.setScene(s);
        stage.show();
    }

    public static void openStudentAdding(StudentMainController studentMainController) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("students/student_adding.fxml"));
        Parent root = fxmlLoader.load();
        StudentAddingController mainController = fxmlLoader.getController();
        mainController.setStudentAddedCallback(studentMainController);
        Scene s = new Scene(root, 400, 600);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/logo.png")));
        stage.setTitle("Добавить ученика");
        stage.setScene(s);
        stage.show();
    }

    static public void openParentAdding(ParentMainController controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("parents/parent_adding.fxml"));
        Parent root = fxmlLoader.load();
        ParentAddingController mainController = fxmlLoader.getController();
        mainController.setParentAddedCallback(controller);
        Scene s = new Scene(root, 400, 600);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/logo.png")));
        stage.setTitle("Родители");
        stage.setScene(s);
        stage.show();
    }

    static public void openTeacherMain() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("teachers/teacher_main.fxml"));
        Parent root = fxmlLoader.load();
        Scene s = new Scene(root, 400, 600);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/logo.png")));
        stage.setMaximized(true);
        stage.setTitle("Учителя");
        stage.setScene(s);
        stage.show();
    }
    static public void openStudentMain() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("students/student_main.fxml"));
        Parent root = fxmlLoader.load();
        Scene s = new Scene(root, 400, 600);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/logo.png")));
        stage.setMaximized(true);
        stage.setTitle("Ученики");
        stage.setScene(s);
        stage.show();

        // asdasdasda
    }
    static public void openParentMain() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("parents/parent_main.fxml"));
        Parent root = fxmlLoader.load();
        Scene s = new Scene(root, 400, 600);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/logo.png")));
        stage.setMaximized(true);
        stage.setTitle("Добавить родителя");
        stage.setScene(s);
        stage.show();
    }

    static public void setRoot(String fxml) throws IOException {
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