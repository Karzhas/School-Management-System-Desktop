package kz.fizmat.parents.main;

import kz.fizmat.entity.Parent;

import java.util.List;

public interface ParentMainContract {

    interface Controller{
        void onAllParentsLoaded(List<Parent> parents);
        void onParentDeleted(int id);
        void onParentUpdated(Parent parent);
        void showProgressIndicator();
        void stopProgressIndicator();
    }
    interface Presenter{
        void loadAllParents();
        void deleteParent(int id);
        void updateParent(Parent parent);
    }

}
