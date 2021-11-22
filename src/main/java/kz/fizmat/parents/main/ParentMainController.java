package kz.fizmat.parents.main;

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
import kz.fizmat.entity.Parent;
import kz.fizmat.parents.adding.ParentAddedCallback;
import kz.fizmat.parents.main.ParentMainContract;
import kz.fizmat.parents.main.ParentMainPresenter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ParentMainController implements Initializable, ParentMainContract.Controller, ParentAddedCallback {
    @FXML
    public TableColumn<Parent, Integer> idColumn;
    @FXML public TableColumn<Parent, String> nameColumn;
    @FXML public TableColumn<Parent, String> surnameColumn;
    @FXML public TableColumn<Parent, String> patronymicColumn;
    @FXML public TableColumn<Parent, String> nationalityColumn;
    @FXML public TableColumn<Parent, LocalDate> birthDateColumn;
    @FXML public TableColumn<Parent, Boolean> genderColumn;


    @FXML public TableView<Parent> table;

    @FXML public ComboBox<String> filterOptionComboBox;
    @FXML public TextField filterParentTextField;
    @FXML public ProgressIndicator progress;
    //
    ObservableList<Parent> actualList = FXCollections.observableArrayList();
    FilteredList<Parent> filteredList = new FilteredList<>(actualList);
    ParentMainContract.Presenter presenter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new ParentMainPresenter(this);
        configureTable();
        presenter.loadAllParents();
        configureFilterOptionComboBox();
        configureFilterParentTextField();

    }

    private void configureFilterParentTextField() {
        filterParentTextField
                .textProperty()
                .addListener((observableValue, oldValue, newValue) -> {
                            filteredList = new FilteredList<>(actualList);
                            filteredList.setPredicate(parent -> {
                                String currentOption = filterOptionComboBox.getValue();
                                if(newValue.equals("")){
                                    return true;
                                }

                                switch (currentOption) {
                                    case "По имени": return parent.getName().toLowerCase().contains(newValue.toLowerCase());
                                    case "По фамилии": return parent.getSurname().toLowerCase().contains(newValue.toLowerCase());
                                    case "По отчеству": return parent.getPatronymic().toLowerCase().contains(newValue.toLowerCase());
                                    case "По национальности": return parent.getNationality().toLowerCase().contains(newValue.toLowerCase());
//                                    case "По дате рождения": return parent.getBirthDate().toLowerCase().contains(newValue.toLowerCase());
//                                    case "По педстажу": return parent.getPedagogicalExperience().toLowerCase().contains(newValue.toLowerCase());
//                                    case "По общему стажу": return parent.getGeneralExperience().toLowerCase().contains(newValue.toLowerCase());
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
            birthDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));

            genderColumn.setCellFactory(tc -> new TableCell<Parent, Boolean>() {
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
                Parent parent = event.getRowValue();
                parent.setName(event.getNewValue());
                presenter.updateParent(parent);
            });
            surnameColumn.setOnEditCommit(event -> {
                Parent parent = event.getRowValue();
                parent.setSurname(event.getNewValue());
                presenter.updateParent(parent);

            });
            patronymicColumn.setOnEditCommit(event -> {
                Parent parent = event.getRowValue();
                parent.setPatronymic(event.getNewValue());
                presenter.updateParent(parent);
            });
            genderColumn.setOnEditCommit(event -> {
                Parent parent = event.getRowValue();
                parent.setGender(event.getNewValue());
                presenter.updateParent(parent);
            });
            nationalityColumn.setOnEditCommit(event -> {
                Parent parent = event.getRowValue();
                parent.setNationality(event.getNewValue());
                presenter.updateParent(parent);
            });
            birthDateColumn.setOnEditCommit(event -> {
                Parent parent = event.getRowValue();
                parent.setBirthDate(event.getNewValue());
                presenter.updateParent(parent);
            });

        }

    }


    public void onRefreshClicked(MouseEvent mouseEvent) {
        presenter.loadAllParents();
    }

    public void onDeleteRowClicked(MouseEvent mouseEvent) {
        Parent parent = table.getSelectionModel().getSelectedItem();
        presenter.deleteParent(parent.getId());
    }


    private void configureFilterOptionComboBox() {
        ObservableList<String> langs = FXCollections.observableArrayList(
                "По имени", "По фамилии", "По отчеству", "По национальности", "По дате рождения"
        );
        filterOptionComboBox.setItems(langs);
        filterOptionComboBox.setValue(langs.get(0));

    }


    public void onFilterParentKeyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void onNewParentAdded(Parent parent) {
        actualList.add(parent);
        table.setItems(actualList);
    }



    @Override
    public void onAllParentsLoaded(List<Parent> parents) {
        actualList = FXCollections.observableArrayList(parents);
        table.setItems(actualList);
    }

    @Override
    public void onParentDeleted(int id) {
        for (int i = 0; i < actualList.size(); i++) {
            if (actualList.get(i).getId() == id) {
                actualList.remove(i);
                table.setItems(actualList);
                break;
            }
        }
    }

    @Override
    public void onParentUpdated(Parent parent) {
        int position = -1;
        for (int i = 0; i < actualList.size(); i++) {
            if (actualList.get(i).getId() == parent.getId()) {
                position = i;
                break;
            }
        }
        actualList.set(position, parent);
        table.setItems(actualList);
    }


    public void onAddNewParent(MouseEvent mouseEvent) throws IOException {
        App.openParentAdding(this);
    }

}
