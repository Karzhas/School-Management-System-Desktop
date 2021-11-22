package kz.fizmat.teachers.adding;

import io.reactivex.schedulers.Schedulers;
import kz.fizmat.entity.Teacher;
import kz.fizmat.network.FizmatApi;
import kz.fizmat.network.RestAdapter;

public class TeacherAddingPresenter implements TeacherAddingContract.Presenter{
    FizmatApi api;
    TeacherAddingContract.Controller controller;

    public TeacherAddingPresenter(TeacherAddingContract.Controller controller) {
        this.controller = controller;
        api = RestAdapter.getFizmatApi();
    }

    @Override
    public void onAddTeacher(Teacher teacher) {
        api.addTeacher(teacher)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe(__ -> controller.showProgressIndicator(true))
                .doOnTerminate(() -> controller.showProgressIndicator(false))
                .subscribe(
                        newTeacher -> controller.onTeacherAdded(newTeacher),
                        error -> System.out.println("TeacherAddingPresenter().onAddTeacher:" + error.getMessage()),
                        () -> controller.clearAddingTextFields()
                );
    }
}
