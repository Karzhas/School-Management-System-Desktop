package kz.fizmat.parents.adding;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import kz.fizmat.entity.Parent;
import kz.fizmat.parents.adding.ParentAddingContract;
import kz.fizmat.parents.adding.ParentAddingPresenter;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ParentAddingController implements Initializable, ParentAddingContract.Controller {
    @FXML public TextField surnameFX;
    @FXML public TextField nameFX;
    @FXML public TextField patronymicFX;
    @FXML public TextField nationalityFX;
    @FXML public RadioButton genderMaleFX;
    @FXML public RadioButton genderFemaleFX;
    @FXML public DatePicker birthDateFX;

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

    ParentAddingContract.Presenter presenter;
    ParentAddedCallback parentAddedCallback;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> stackPane.requestFocus());

        genderGroup = new ToggleGroup();
        grantOrPaidGroup = new ToggleGroup();
        genderFemaleFX.setToggleGroup(genderGroup);
        genderMaleFX.setToggleGroup(genderGroup);
        //
        textFieldNodes = Arrays.asList(nameFX, surnameFX, patronymicFX, nationalityFX);
        dateNodes = Arrays.asList(birthDateFX);
        radioButtonNodes = Arrays.asList(genderMaleFX, genderFemaleFX);
        //
        presenter = new ParentAddingPresenter(this);
        //
        stackPane.setOnScroll(event -> {
            double deltaY = event.getDeltaY()*3; // *6 to make the scrolling a bit faster
            double width = scrollPane.getContent().getBoundsInLocal().getWidth();
            double value = scrollPane.getVvalue();
            scrollPane.setVvalue(value + -deltaY/width); // deltaY/width to make the scrolling equally fast regardless of the actual width of the component
        });
        //

    }


    public void setParentAddedCallback(ParentAddedCallback parentAddedCallback) {
        this.parentAddedCallback = parentAddedCallback;
    }

    public void onCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelFX.getScene().getWindow();
        stage.close();
    }

    public void onAddParent(ActionEvent actionEvent) {
        Parent parent = constructParent();
        presenter.onAddParent(parent);
    }


    @Override
    public void onParentAdded(Parent parent) {
        parentAddedCallback.onNewParentAdded(parent);
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

    private Parent constructParent(){
        String name = nameFX.getText();
        String surname = surnameFX.getText();
        String patronymic = patronymicFX.getText();
        String nationality = nationalityFX.getText();
        LocalDate birthDate = birthDateFX.getValue();
        boolean gender = genderFemaleFX.isSelected();


        return new Parent.Builder()
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .gender(gender)
                .nationality(nationality)
                .birthDate(birthDate)
                .build();
    }


}
