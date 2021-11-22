package kz.fizmat.teachers.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.*;
import kz.fizmat.App;
import kz.fizmat.entity.Teacher;
import kz.fizmat.teachers.adding.TeacherAddedCallback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class TeacherMainController implements Initializable, TeacherMainContract.Controller, TeacherAddedCallback {



    //
    @FXML public TableColumn<Teacher, Integer> idColumn;
    @FXML public TableColumn<Teacher, Boolean> isGraduatedColumn;
    @FXML public TableColumn<Teacher, String> nameColumn;
    @FXML public TableColumn<Teacher, String> surnameColumn;
    @FXML public TableColumn<Teacher, String> patronymicColumn;
    @FXML public TableColumn<Teacher, Boolean> genderColumn;
    @FXML public TableColumn<Teacher, String> nationalityColumn;
    @FXML public TableColumn<Teacher, LocalDate> birthDateColumn;
    @FXML public TableColumn<Teacher, String> educationColumn;
    @FXML public TableColumn<Teacher, String> qualificationColumn;
    @FXML public TableColumn<Teacher, Integer> pedagogicalExperienceColumn;
    @FXML public TableColumn<Teacher, Integer> generalExperienceColumn;
    @FXML public TableColumn<Teacher, String> categoryColumn;
    @FXML public TableColumn<Teacher, String> teachesInGradesColumn;
    @FXML public TableColumn<Teacher, Boolean> isFullTimeEmployeeColumn;
    @FXML public TableColumn<Teacher, String> maritalStatusColumn;
    @FXML public TableColumn<Teacher, Integer> numberOfChildrenColumn;
    @FXML public TableColumn<Teacher, String> awardsColumn;
    @FXML public TableColumn<Teacher, LocalDate> qualificationDateColumn;

    @FXML public TableView<Teacher> table;

    @FXML public ComboBox<String> filterOptionComboBox;
    @FXML public TextField filterTeacherTextField;
    @FXML public ProgressIndicator progress;
    //
    ObservableList<Teacher> actualList = FXCollections.observableArrayList();
    FilteredList<Teacher> filteredList = new FilteredList<>(actualList);
    TeacherMainContract.Presenter presenter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new TeacherMainPresenter(this);
        configureTable();
        presenter.loadAllTeachers();
        configureFilterOptionComboBox();
        configureFilterTeacherTextField();

    }

    private void configureFilterTeacherTextField() {
        filterTeacherTextField
                .textProperty()
                .addListener((observableValue, oldValue, newValue) -> {
                            filteredList = new FilteredList<>(actualList);
                            filteredList.setPredicate(teacher -> {
                                String currentOption = filterOptionComboBox.getValue();
                                if(newValue.equals("")){
                                    return true;
                                }

                                switch (currentOption) {
                                    case "По имени": return teacher.getName().toLowerCase().contains(newValue.toLowerCase());
                                    case "По фамилии": return teacher.getSurname().toLowerCase().contains(newValue.toLowerCase());
                                    case "По отчеству": return teacher.getPatronymic().toLowerCase().contains(newValue.toLowerCase());
                                    case "По национальности": return teacher.getNationality().toLowerCase().contains(newValue.toLowerCase());
//                                    case "По дате рождения": return teacher.getBirthDate().toLowerCase().contains(newValue.toLowerCase());
                                    case "По образованию": return teacher.getEducation().toLowerCase().contains(newValue.toLowerCase());
                                    case "По квалификации": return teacher.getQualification().toLowerCase().contains(newValue.toLowerCase());
//                                    case "По педстажу": return teacher.getPedagogicalExperience().toLowerCase().contains(newValue.toLowerCase());
//                                    case "По общему стажу": return teacher.getGeneralExperience().toLowerCase().contains(newValue.toLowerCase());
                                    default: return true;
                                }

                            });
                            table.setItems(filteredList);
                        }
                );
    }



    @Override
    public void showProgressIndicator() {
        table.setOpacity(0.1);
        progress.setVisible(true);
    }

    @Override
    public void stopProgressIndicator() {
        table.setOpacity(1);
        progress.setVisible(false);
    }


    private void configureTable() {
        // set cell factory
        {
            table.setEditable(true);
            nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            patronymicColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            nationalityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            educationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            qualificationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            birthDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
            qualificationDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
            pedagogicalExperienceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            generalExperienceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            teachesInGradesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            maritalStatusColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            numberOfChildrenColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            awardsColumn.setCellFactory(TextFieldTableCell.forTableColumn());

            genderColumn.setCellFactory(tc -> new TableCell<Teacher, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null :
                            item ? "муж." : "жен.");
                }
            });

            isGraduatedColumn.setCellFactory(tc -> new TableCell<Teacher, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null :
                            item ? "+" : "-");
                }
            });
            isFullTimeEmployeeColumn.setCellFactory(tc -> new TableCell<Teacher, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null :
                            item ? "+" : "-");
                }
            });


        }
        // on update cell
        {
            nameColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setName(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
            surnameColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setSurname(event.getNewValue());
                presenter.updateTeacher(teacher);

            });
            patronymicColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setPatronymic(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
            genderColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setGender(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
            nationalityColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setNationality(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
            birthDateColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setBirthDate(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
            educationColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setEducation(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
            qualificationColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setQualification(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
            pedagogicalExperienceColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setPedagogicalExperience(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
            generalExperienceColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setGeneralExperience(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
            categoryColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setCategory(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
            teachesInGradesColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setTeachesInGrades(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
            isFullTimeEmployeeColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setFullTimeEmployee(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
            maritalStatusColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setMaritalStatus(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
            numberOfChildrenColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setNumberOfChildren(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
            awardsColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setAwards(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
            isGraduatedColumn.setOnEditCommit(event -> {
                Teacher teacher = event.getRowValue();
                teacher.setGraduated(event.getNewValue());
                presenter.updateTeacher(teacher);
            });
        }

    }


    public void onRefreshClicked(MouseEvent mouseEvent) {
        presenter.loadAllTeachers();
    }

    public void onDeleteRowClicked(MouseEvent mouseEvent) {
        Teacher teacher = table.getSelectionModel().getSelectedItem();
        presenter.deleteTeacher(teacher.getId());
    }


    private void configureFilterOptionComboBox() {
        ObservableList<String> langs = FXCollections.observableArrayList(
                "По имени", "По фамилии", "По отчеству", "По национальности", "По дате рождения",
                "По образованию", "По квалификации", "По педстажу", "По общему стажу"
        );
        filterOptionComboBox.setItems(langs);
        filterOptionComboBox.setValue(langs.get(0));

    }


    public void onFilterTeacherKeyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void onNewTeacherAdded(Teacher teacher) {
        actualList.add(teacher);
        table.setItems(actualList);
    }



    @Override
    public void onAllTeachersLoaded(List<Teacher> teachers) {
        actualList = FXCollections.observableArrayList(teachers);
        table.setItems(actualList);
    }

    @Override
    public void onTeacherDeleted(int id) {
        for (int i = 0; i < actualList.size(); i++) {
            if (actualList.get(i).getId() == id) {
                actualList.remove(i);
                table.setItems(actualList);
                break;
            }
        }
    }

    @Override
    public void onTeacherUpdated(Teacher teacher) {
        int position = -1;
        for (int i = 0; i < actualList.size(); i++) {
            if (actualList.get(i).getId() == teacher.getId()) {
                position = i;
                break;
            }
        }
        actualList.set(position, teacher);
        table.setItems(actualList);
    }


    public void onAddNewTeacher(MouseEvent mouseEvent) throws IOException {
        App.openTeacherAdding(this);
    }



}