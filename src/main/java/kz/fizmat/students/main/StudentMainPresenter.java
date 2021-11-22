package kz.fizmat.students.main;

import io.reactivex.schedulers.Schedulers;
import kz.fizmat.entity.Student;
import kz.fizmat.network.FizmatApi;
import kz.fizmat.network.RestAdapter;

public class StudentMainPresenter implements StudentMainContract.Presenter{

    FizmatApi api;
    StudentMainContract.Controller controller;

    public StudentMainPresenter(StudentMainContract.Controller controller){
        this.controller = controller;
        api = RestAdapter.getFizmatApi();
    }



    @Override
    public void loadAllStudents() {
        api.getStudents()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe(__ -> controller.showProgressIndicator())
                .doOnTerminate(() -> controller.stopProgressIndicator())
                .subscribe(
                        students -> controller.onAllStudentsLoaded(students),
                        error -> System.out.println("StudentMainPresenter.loadAllStudents()" + error.getMessage() + ", " + error.getLocalizedMessage() + ", " + error.getCause()),
                        () -> { }
                );
    }

    @Override
    public void deleteStudent(int id) {
        api.deleteStudent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe(__ -> controller.showProgressIndicator())
                .doOnTerminate(() -> controller.stopProgressIndicator())
                .subscribe(
                        () -> controller.onStudentDeleted(id),
                        error -> System.out.println("StudentMainPresenter.deleteStudent():" + error.getMessage())
                );
    }

    @Override
    public void updateStudent(Student student) {
        api.updateStudent(student)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe(__ -> controller.showProgressIndicator())
                .doOnTerminate(() -> controller.stopProgressIndicator())
                .subscribe(
                        updatedStudent -> controller.onStudentUpdated(updatedStudent),
                        error -> System.out.println("StudentMainPresenter.updateStudent():" + error.getMessage()),
                        () -> {}
                );
    }

}
