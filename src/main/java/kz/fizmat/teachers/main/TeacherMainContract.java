package kz.fizmat.teachers.main;

import kz.fizmat.entity.Teacher;

import java.util.List;

public interface TeacherMainContract {
    interface Controller{
        //void onNewTeacherAdded(Teacher teacher);
        void onAllTeachersLoaded(List<Teacher> teachers);
        void onTeacherDeleted(int id);
        void onTeacherUpdated(Teacher teacher);
        void showProgressIndicator();
        void stopProgressIndicator();
    }
    interface Presenter{
        void loadAllTeachers();
        void deleteTeacher(int id);
        void updateTeacher(Teacher teacher);
    }

}
