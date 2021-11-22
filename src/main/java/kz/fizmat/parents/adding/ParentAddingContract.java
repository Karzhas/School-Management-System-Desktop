package kz.fizmat.parents.adding;

import kz.fizmat.entity.Parent;

public interface ParentAddingContract {
    interface Controller{
        void showProgressIndicator(boolean isVisible);
        void clearAddingTextFields();
        void onParentAdded(Parent parent);
    }

    interface Presenter{
        void onAddParent(Parent parent);
    }
}
