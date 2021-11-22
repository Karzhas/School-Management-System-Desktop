package kz.fizmat.students.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import kz.fizmat.App;
import kz.fizmat.entity.Student;
import kz.fizmat.students.adding.StudentAddedCalback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class StudentMainController implements Initializable, StudentMainContract.Controller, StudentAddedCalback {
    @FXML public TableColumn<Student, Integer> idColumn;
    @FXML public TableColumn<Student, String> nameColumn;
    @FXML public TableColumn<Student, String> surnameColumn;
    @FXML public TableColumn<Student, String> patronymicColumn;
    @FXML public TableColumn<Student, String> nationalityColumn;
    @FXML public TableColumn<Student, String> gradeColumn;
    @FXML public TableColumn<Student, String> momColumn;
    @FXML public TableColumn<Student, String> dadColumn;
    @FXML public TableColumn<Student, String> classroomTeacherColumn;
    @FXML public TableColumn<Student, String> sportAchievementsColumn;
    @FXML public TableColumn<Student, String> achievementsAtTheOlympiadsColumn;
    @FXML public TableColumn<Student, Double> gpaColumn;
    @FXML public TableColumn<Student, LocalDate> yearOfAdmissionColumn;
    @FXML public TableColumn<Student, LocalDate> birthDateColumn;
    @FXML public TableColumn<Student, Boolean> genderColumn;
    @FXML public TableColumn<Student, Boolean> isGrantColumn;
    @FXML public TableColumn<Student, Boolean> isLargeFamilyColumn;
    @FXML public TableColumn<Student, Boolean> freeFromPhysicalEducationColumn;


    @FXML public TableView<Student> table;

    @FXML public ComboBox<String> filterOptionComboBox;
    @FXML public TextField filterStudentTextField;
    @FXML public ProgressIndicator progress;
    //
    ObservableList<Student> actualList = FXCollections.observableArrayList();
    FilteredList<Student> filteredList = new FilteredList<>(actualList);
    StudentMainContract.Presenter presenter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new StudentMainPresenter(this);
        configureTable();
        presenter.loadAllStudents();
        configureFilterOptionComboBox();
        configureFilterStudentTextField();

    }

    private void configureFilterStudentTextField() {
        filterStudentTextField
                .textProperty()
                .addListener((observableValue, oldValue, newValue) -> {
                            filteredList = new FilteredList<>(actualList);
                            filteredList.setPredicate(student -> {
                                String currentOption = filterOptionComboBox.getValue();
                                if(newValue.equals("")){
                                    return true;
                                }

                                switch (currentOption) {
                                    case "По имени": return student.getName().toLowerCase().contains(newValue.toLowerCase());
                                    case "По фамилии": return student.getSurname().toLowerCase().contains(newValue.toLowerCase());
                                    case "По отчеству": return student.getPatronymic().toLowerCase().contains(newValue.toLowerCase());
                                    case "По национальности": return student.getNationality().toLowerCase().contains(newValue.toLowerCase());
//                                    case "По дате рождения": return student.getBirthDate().toLowerCase().contains(newValue.toLowerCase());
//                                    case "По педстажу": return student.getPedagogicalExperience().toLowerCase().contains(newValue.toLowerCase());
//                                    case "По общему стажу": return student.getGeneralExperience().toLowerCase().contains(newValue.toLowerCase());
                                    default: return true;
                                }

                            });
                            table.setItems(filteredList);
                        }
                );
    }



    @Override
    public void showProgressIndicator() {
        progress.setVisible(true);
    }

    @Override
    public void stopProgressIndicator() {
        progress.setVisible(false);
    }


    private void configureTable() {
        // set cell factory
        {
            table.setEditable(true);
            nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            patronymicColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            nationalityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            gradeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            momColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            birthDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
            dadColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            classroomTeacherColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            gpaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            sportAchievementsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            yearOfAdmissionColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
            isGrantColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
            isLargeFamilyColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
            freeFromPhysicalEducationColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));

            genderColumn.setCellFactory(tc -> new TableCell<Student, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null :
                            item ? "муж." : "жен.");
                }
            });




        }
        // on update cell
        {
            nameColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setName(event.getNewValue());
                presenter.updateStudent(student);
            });
            surnameColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setSurname(event.getNewValue());
                presenter.updateStudent(student);

            });
            patronymicColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setPatronymic(event.getNewValue());
                presenter.updateStudent(student);
            });
            genderColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setGender(event.getNewValue());
                presenter.updateStudent(student);
            });
            nationalityColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setNationality(event.getNewValue());
                presenter.updateStudent(student);
            });
            birthDateColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setBirthDate(event.getNewValue());
                presenter.updateStudent(student);
            });
            gradeColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setGrade(event.getNewValue());
                presenter.updateStudent(student);
            });
            momColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setMom(event.getNewValue());
                presenter.updateStudent(student);
            });
            dadColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setDad(event.getNewValue());
                presenter.updateStudent(student);
            });
            classroomTeacherColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setClassroomTeacher(event.getNewValue());
                presenter.updateStudent(student);
            });
            classroomTeacherColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setClassroomTeacher(event.getNewValue());
                presenter.updateStudent(student);
            });
            gpaColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setGpa(event.getNewValue());
                presenter.updateStudent(student);
            });
            sportAchievementsColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setSportAchievements(event.getNewValue());
                presenter.updateStudent(student);
            });
            achievementsAtTheOlympiadsColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setAchievementsAtTheOlympiads(event.getNewValue());
                presenter.updateStudent(student);
            });
            yearOfAdmissionColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setYearOfAdmission(event.getNewValue());
                presenter.updateStudent(student);
            });
            isGrantColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setGrantPlatnik(event.getNewValue());
                presenter.updateStudent(student);
            });
            isLargeFamilyColumn.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setLargeFamily(event.getNewValue());
                presenter.updateStudent(student);
            });
        }

    }


    public void onRefreshClicked(MouseEvent mouseEvent) {
        presenter.loadAllStudents();
    }

    public void onDeleteRowClicked(MouseEvent mouseEvent) {
        Student student = table.getSelectionModel().getSelectedItem();
        presenter.deleteStudent(student.getId());
    }


    private void configureFilterOptionComboBox() {
        ObservableList<String> langs = FXCollections.observableArrayList(
                "По имени", "По фамилии", "По отчеству", "По национальности", "По дате рождения",
                "По родителям", "По классам", "По GPA", "По спортивным достижениям"
        );
        filterOptionComboBox.setItems(langs);
        filterOptionComboBox.setValue(langs.get(0));

    }


    public void onFilterStudentKeyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void onNewStudentAdded(Student student) {
        actualList.add(student);
        table.setItems(actualList);
    }



    @Override
    public void onAllStudentsLoaded(List<Student> students) {
        actualList = FXCollections.observableArrayList(students);
        table.setItems(actualList);
    }

    @Override
    public void onStudentDeleted(int id) {
        for (int i = 0; i < actualList.size(); i++) {
            if (actualList.get(i).getId() == id) {
                actualList.remove(i);
                table.setItems(actualList);
                break;
            }
        }
    }

    @Override
    public void onStudentUpdated(Student student) {
        int position = -1;
        for (int i = 0; i < actualList.size(); i++) {
            if (actualList.get(i).getId() == student.getId()) {
                position = i;
                break;
            }
        }
        actualList.set(position, student);
        table.setItems(actualList);
    }


    public void onAddNewStudent(MouseEvent mouseEvent) throws IOException {
        App.openStudentAdding(this);
    }

}
