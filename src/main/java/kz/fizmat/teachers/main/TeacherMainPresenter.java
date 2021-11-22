package kz.fizmat.teachers.main;

import io.reactivex.schedulers.Schedulers;
import kz.fizmat.entity.Teacher;
import kz.fizmat.network.FizmatApi;
import kz.fizmat.network.RestAdapter;

public class TeacherMainPresenter implements TeacherMainContract.Presenter{

    FizmatApi api;
    TeacherMainContract.Controller controller;

    public TeacherMainPresenter(TeacherMainContract.Controller controller){
        this.controller = controller;
        api = RestAdapter.getFizmatApi();
    }




    @Override
    public void loadAllTeachers() {
        api.getTeachers()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe(__ -> controller.showProgressIndicator())
                .doOnTerminate(() -> controller.stopProgressIndicator())
                .subscribe(
                        teachers -> controller.onAllTeachersLoaded(teachers),
                        error -> System.out.println("TeacherMainPresenter.loadAllTeachers()" + error.getMessage()),
                        () -> { }
                );
    }

    @Override
    public void deleteTeacher(int id) {
        api.deleteTeacher(id)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe(__ -> controller.showProgressIndicator())
                .doOnTerminate(() -> controller.stopProgressIndicator())
                .subscribe(
                        () -> controller.onTeacherDeleted(id),
                        error -> System.out.println("TeacherMainPresenter.deleteTeacher():" + error.getMessage())
                );
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        api.updateTeacher(teacher)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe(__ -> controller.showProgressIndicator())
                .doOnTerminate(() -> controller.stopProgressIndicator())
                .subscribe(
                        updatedTeacher -> controller.onTeacherUpdated(updatedTeacher),
                        error -> System.out.println("TeacherMainPresenter.updateTeacher():" + error.getMessage()),
                        () -> {}
                );
    }
}
