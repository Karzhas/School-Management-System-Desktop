package kz.fizmat.parents.adding;

import io.reactivex.schedulers.Schedulers;
import kz.fizmat.entity.Parent;
import kz.fizmat.entity.Teacher;
import kz.fizmat.network.FizmatApi;
import kz.fizmat.network.RestAdapter;
import kz.fizmat.parents.adding.ParentAddingContract;

public class ParentAddingPresenter implements ParentAddingContract.Presenter{
    FizmatApi api;
    ParentAddingContract.Controller controller;

    public ParentAddingPresenter(ParentAddingContract.Controller controller) {
        this.controller = controller;
        api = RestAdapter.getFizmatApi();
    }

    @Override
    public void onAddParent(Parent parent) {
        api.addParent(parent)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe(__ -> controller.showProgressIndicator(true))
                .doOnTerminate(() -> controller.showProgressIndicator(false))
                .subscribe(
                        newParent -> controller.onParentAdded(newParent),
                        error -> System.out.println("ParentAddingPresenter().onAddParent:" + error.getMessage()),
                        () -> controller.clearAddingTextFields()
                );
    }
}