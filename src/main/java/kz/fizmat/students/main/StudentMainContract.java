package kz.fizmat.students.main;

import kz.fizmat.entity.Student;

import java.util.List;

public interface StudentMainContract {

    interface Controller{
        void onAllStudentsLoaded(List<Student> students);
        void onStudentDeleted(int id);
        void onStudentUpdated(Student student);
        void showProgressIndicator();
        void stopProgressIndicator();
    }
    interface Presenter{
        void loadAllStudents();
        void deleteStudent(int id);
        void updateStudent(Student student);
    }

}
