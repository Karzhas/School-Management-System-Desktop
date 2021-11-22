package kz.fizmat.teachers.adding;

import kz.fizmat.entity.Teacher;

public interface TeacherAddingContract {

    interface Controller{
        void showProgressIndicator(boolean isVisible);
        void clearAddingTextFields();
        void onTeacherAdded(Teacher teacher);
    }

    interface Presenter{
        void onAddTeacher(Teacher teacher);
    }

}
