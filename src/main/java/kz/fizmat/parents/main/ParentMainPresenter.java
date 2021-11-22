package kz.fizmat.parents.main;

import io.reactivex.schedulers.Schedulers;
import kz.fizmat.entity.Parent;
import kz.fizmat.network.FizmatApi;
import kz.fizmat.network.RestAdapter;
import kz.fizmat.parents.main.ParentMainContract;

public class ParentMainPresenter implements ParentMainContract.Presenter{

    FizmatApi api;
    ParentMainContract.Controller controller;

    public ParentMainPresenter(ParentMainContract.Controller controller){
        this.controller = controller;
        api = RestAdapter.getFizmatApi();
    }



    @Override
    public void loadAllParents() {
        api.getParents()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe(__ -> controller.showProgressIndicator())
                .doOnTerminate(() -> controller.stopProgressIndicator())
                .subscribe(
                        parents -> controller.onAllParentsLoaded(parents),
                        error -> System.out.println("ParentMainPresenter.loadAllParents()" + error.getMessage() + ", " + error.getLocalizedMessage() + ", " + error.getCause()),
                        () -> { }
                );
    }

    @Override
    public void deleteParent(int id) {
        api.deleteParent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe(__ -> controller.showProgressIndicator())
                .doOnTerminate(() -> controller.stopProgressIndicator())
                .subscribe(
                        () -> controller.onParentDeleted(id),
                        error -> System.out.println("ParentMainPresenter.deleteParent():" + error.getMessage())
                );
    }

    @Override
    public void updateParent(Parent parent) {
        api.updateParent(parent)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnSubscribe(__ -> controller.showProgressIndicator())
                .doOnTerminate(() -> controller.stopProgressIndicator())
                .subscribe(
                        updatedParent -> controller.onParentUpdated(updatedParent),
                        error -> System.out.println("ParentMainPresenter.updateParent():" + error.getMessage()),
                        () -> {}
                );
    }

}
