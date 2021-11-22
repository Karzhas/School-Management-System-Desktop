package kz.fizmat.students.adding;

import io.reactivex.schedulers.Schedulers;
import kz.fizmat.entity.Student;
import kz.fizmat.entity.Teacher;
import kz.fizmat.network.FizmatApi;
import kz.fizmat.network.RestAdapter;

public class StudentAddingPresenter implements StudentAddingContract.Presenter{
    FizmatApi api;
    StudentAddingContract.Controller controller;

    public StudentAddingPresenter(StudentAddingContract.Controller controller) {
        this.controller = controller;
        api = RestAdapter.getFizmatApi();
    }

    @Override
    public void onAddStudent(Student student) {
        api.addStudent(student)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe(__ -> controller.showProgressIndicator(true))
                .doOnTerminate(() -> controller.showProgressIndicator(false))
                .subscribe(
                        newStudent -> controller.onStudentAdded(newStudent),
                        error -> System.out.println("StudentAddingPresenter().onAddStudent:" + error.getMessage()),
                        () -> controller.clearAddingTextFields()
                );
    }
}
