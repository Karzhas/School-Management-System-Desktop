package kz.fizmat.teachers.adding;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import kz.fizmat.entity.Teacher;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TeacherAddingController implements Initializable, TeacherAddingContract.Controller {

    @FXML public TextField nameFX;
    @FXML public TextField surnameFX;
    @FXML public TextField patronymicFX;
    @FXML public TextField nationalityFX;
    @FXML public TextField educationFX;
    @FXML public TextField pedagogicalExperienceFX;
    @FXML public TextField qualificationFX;
    @FXML public TextField generalExperienceFX;
    @FXML public TextField numberOfChildrenFX;
    @FXML public TextField awardsFX;
    @FXML public TextField maritalStatusFX;
    @FXML public TextField categoryFX;
    @FXML public TextField teachesInGradesFX;
    @FXML public DatePicker qualificationDateFX;
    @FXML public DatePicker birthDateFX;
    @FXML public RadioButton genderMaleFX;
    @FXML public RadioButton genderFemaleFX;
    @FXML public RadioButton fullTimeFX;
    @FXML public RadioButton partTimeFX;
    @FXML public RadioButton isGraduatedFX;
    @FXML public Button cancelFX;
    @FXML public Button addFX;
    @FXML public ProgressIndicator progress;
    @FXML public StackPane stackPane;
    @FXML public ScrollPane scrollPane;

    ToggleGroup genderGroup;
    ToggleGroup workTimeGroup;

    List<TextField> standardNodes;
    List<DatePicker> dateNodes;
    List<RadioButton> radioButtonNodes;

    TeacherAddingContract.Presenter presenter;
    TeacherAddedCallback teacherAddedCallback;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> stackPane.requestFocus());

        genderGroup = new ToggleGroup();
        workTimeGroup = new ToggleGroup();
        genderFemaleFX.setToggleGroup(genderGroup);
        genderMaleFX.setToggleGroup(genderGroup);
        partTimeFX.setToggleGroup(workTimeGroup);
        fullTimeFX.setToggleGroup(workTimeGroup);
        //
        standardNodes = Arrays.asList(nameFX, surnameFX, patronymicFX, nationalityFX, educationFX,
                                     pedagogicalExperienceFX, qualificationFX, generalExperienceFX,
                                     numberOfChildrenFX, awardsFX, maritalStatusFX, categoryFX, teachesInGradesFX);
        dateNodes = Arrays.asList(qualificationDateFX, birthDateFX);
        radioButtonNodes = Arrays.asList(genderMaleFX, genderFemaleFX, fullTimeFX, partTimeFX, isGraduatedFX);
        //
        presenter = new TeacherAddingPresenter(this);
        //
        progress.setVisible(false);
        //
        stackPane.setOnScroll(event -> {
            double deltaY = event.getDeltaY()*3; // *6 to make the scrolling a bit faster
            double width = scrollPane.getContent().getBoundsInLocal().getWidth();
            double value = scrollPane.getVvalue();
            scrollPane.setVvalue(value + -deltaY/width); // deltaY/width to make the scrolling equally fast regardless of the actual width of the component
        });
        //

    }


    public void setTeacherAddedCallback(TeacherAddedCallback teacherAddedCallback) {
        this.teacherAddedCallback = teacherAddedCallback;
    }

    public void onCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelFX.getScene().getWindow();
        stage.close();
    }

    public void onAddTeacher(ActionEvent actionEvent) {
        Teacher teacher = constructTeacher();
        presenter.onAddTeacher(teacher);
    }


    @Override
    public void onTeacherAdded(Teacher teacher) {
        teacherAddedCallback.onNewTeacherAdded(teacher);
    }

    @Override
    public void showProgressIndicator(boolean isVisible) {
        progress.setVisible(isVisible);
    }

    @Override
    public void clearAddingTextFields() {


        for(TextField node : standardNodes){
            node.setText("");
        }
        for(DatePicker node : dateNodes){
            node.setValue(null);
        }
        for(RadioButton node : radioButtonNodes){
            node.setSelected(false);
        }
    }

    private Teacher constructTeacher(){
        String name = nameFX.getText();
        String surname = surnameFX.getText();
        String patronymic = patronymicFX.getText();
        String nationality = nationalityFX.getText();
        String education = educationFX.getText();
        String qualification = qualificationFX.getText();
        String category = categoryFX.getText();
        String teachesInGrades = teachesInGradesFX.getText();
        String maritalStatus = maritalStatusFX.getText();
        String awards = awardsFX.getText();
        LocalDate qualificationDate = qualificationDateFX.getValue();
        LocalDate birthDate = birthDateFX.getValue();
        boolean isGraduated = isGraduatedFX.isSelected();
        boolean isFullTimeEmployee = fullTimeFX.isSelected();
        boolean gender = genderFemaleFX.isSelected();
        Integer numberOfChildren = numberOfChildrenFX.getText().equals("") ? null : Integer.parseInt(numberOfChildrenFX.getText());
        Integer pedagogicalExperience = pedagogicalExperienceFX.getText().equals("") ? null : Integer.parseInt(pedagogicalExperienceFX.getText());
        Integer generalExperience = generalExperienceFX.getText().equals("") ? null : Integer.parseInt(generalExperienceFX.getText());
        return new Teacher.Builder()
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .gender(gender)
                .nationality(nationality)
                .birthDate(birthDate)
                .education(education)
                .qualification(qualification)
                .qualificationDate(qualificationDate)
                .graduated(isGraduated)
                .pedagogicalExperience(pedagogicalExperience)
                .generalExperience(generalExperience)
                .category(category)
                .teachesInGrades(teachesInGrades)
                .fullTimeEmployee(isFullTimeEmployee)
                .maritalStatus(maritalStatus)
                .numberOfChildren(numberOfChildren)
                .awards(awards).build();
    }




}
