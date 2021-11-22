package kz.fizmat.students.adding;

import kz.fizmat.entity.Student;
import kz.fizmat.entity.Teacher;

public interface StudentAddingContract {
    interface Controller{
        void showProgressIndicator(boolean isVisible);
        void clearAddingTextFields();
        void onStudentAdded(Student student);
    }

    interface Presenter{
        void onAddStudent(Student student);
    }
}
