package kz.fizmat.students.adding;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import kz.fizmat.entity.Student;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class StudentAddingController implements Initializable, StudentAddingContract.Controller {
    @FXML public TextField surnameFX;
    @FXML public TextField nameFX;
    @FXML public TextField patronymicFX;
    @FXML public TextField nationalityFX;
    @FXML public TextField gradeFX;
    @FXML public TextField momFX;
    @FXML public TextField dadFX;
    @FXML public TextField classroomTeacherFX;
    @FXML public TextField gpaFX;
    @FXML public TextField achievementsAtTheOlympiadsFX;
    @FXML public TextField sportAchievementsFX;
    @FXML public RadioButton genderMaleFX;
    @FXML public RadioButton genderFemaleFX;
    @FXML public RadioButton isGraduatedFX;
    @FXML public RadioButton grantFX;
    @FXML public RadioButton platnoeFX;
    @FXML public RadioButton isLargeFamilyFX;
    @FXML public RadioButton freeFromPhysicalEducationFX;
    @FXML public DatePicker birthDateFX;
    @FXML public DatePicker yearOfAdmissionFX;

    @FXML public Button cancelFX;
    @FXML public Button addFX;
    @FXML public ProgressIndicator progress;
    @FXML public StackPane stackPane;
    @FXML public ScrollPane scrollPane;


    ToggleGroup genderGroup;
    ToggleGroup grantOrPaidGroup;

    List<TextField> textFieldNodes;
    List<DatePicker> dateNodes;
    List<RadioButton> radioButtonNodes;

    StudentAddingContract.Presenter presenter;
    StudentAddedCalback studentAddedCallback;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> stackPane.requestFocus());

        genderGroup = new ToggleGroup();
        grantOrPaidGroup = new ToggleGroup();
        genderFemaleFX.setToggleGroup(genderGroup);
        genderMaleFX.setToggleGroup(genderGroup);
        grantFX.setToggleGroup(grantOrPaidGroup);
        platnoeFX.setToggleGroup(grantOrPaidGroup);
        //
        textFieldNodes = Arrays.asList(nameFX, surnameFX, patronymicFX, nationalityFX, gradeFX,
                momFX, dadFX, classroomTeacherFX,
                gpaFX, achievementsAtTheOlympiadsFX, sportAchievementsFX);
        dateNodes = Arrays.asList(birthDateFX, yearOfAdmissionFX);
        radioButtonNodes = Arrays.asList(genderMaleFX, genderFemaleFX, isGraduatedFX, grantFX, platnoeFX, isLargeFamilyFX, freeFromPhysicalEducationFX);
        //
        presenter = new StudentAddingPresenter(this);
        //
        stackPane.setOnScroll(event -> {
            double deltaY = event.getDeltaY()*3; // *6 to make the scrolling a bit faster
            double width = scrollPane.getContent().getBoundsInLocal().getWidth();
            double value = scrollPane.getVvalue();
            scrollPane.setVvalue(value + -deltaY/width); // deltaY/width to make the scrolling equally fast regardless of the actual width of the component
        });
        //

    }


    public void setStudentAddedCallback(StudentAddedCalback studentAddedCallback) {
        this.studentAddedCallback = studentAddedCallback;
    }

    public void onCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelFX.getScene().getWindow();
        stage.close();
    }

    public void onAddStudent(ActionEvent actionEvent) {
        Student student = constructStudent();
        presenter.onAddStudent(student);
    }


    @Override
    public void onStudentAdded(Student student) {
        studentAddedCallback.onNewStudentAdded(student);
    }

    @Override
    public void showProgressIndicator(boolean isVisible) {
        progress.setVisible(isVisible);
    }

    @Override
    public void clearAddingTextFields() {


        for(TextField node : textFieldNodes){
            node.setText("");
        }
        for(DatePicker node : dateNodes){
            node.setValue(null);
        }
        for(RadioButton node : radioButtonNodes){
            node.setSelected(false);
        }
    }

    private Student constructStudent(){
        String name = nameFX.getText();
        String surname = surnameFX.getText();
        String patronymic = patronymicFX.getText();
        String nationality = nationalityFX.getText();
        String grade = gradeFX.getText();
        String mom = momFX.getText();
        String dad = dadFX.getText();
        String classroomTeacher = classroomTeacherFX.getText();
        Double gpa = gpaFX.getText().equals("") ? null : Double.parseDouble(gpaFX.getText());
        String sportAchievements = sportAchievementsFX.getText();
        String achievementsAtTheOlympiads = achievementsAtTheOlympiadsFX.getText();
        LocalDate birthDate = birthDateFX.getValue();
        LocalDate yearOfAdmission = yearOfAdmissionFX.getValue();
        boolean gender = genderFemaleFX.isSelected();
        boolean isGrant = grantFX.isSelected();
        boolean isLargeFamily = isLargeFamilyFX.isSelected();
        boolean freeFromPhysicalEducation = freeFromPhysicalEducationFX.isSelected();


        return new Student.Builder()
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .gender(gender)
                .nationality(nationality)
                .birthDate(birthDate)
                .grade(grade)
                .mom(mom)
                .dad(dad)
                .classroomTeacher(classroomTeacher)
                .gpa(gpa)
                .sportAchievements(sportAchievements)
                .achievementsAtTheOlympiads(achievementsAtTheOlympiads)
                .yearOfAdmission(yearOfAdmission)
                .isGrant(isGrant)
                .isLargeFamily(isLargeFamily)
                .freeFromPhysicalEducation(freeFromPhysicalEducation)
                .build();
    }


}
