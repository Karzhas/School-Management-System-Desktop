package kz.fizmat.network;


import io.reactivex.Completable;
import io.reactivex.Observable;
import kz.fizmat.entity.Parent;
import kz.fizmat.entity.Student;
import kz.fizmat.entity.Teacher;
import retrofit2.http.*;

import java.util.List;

public interface FizmatApi {

    @GET("/wakeupHerokuServer")
    Completable wakeupHerokuServer();

    @GET("/teachers")
    Observable<List<Teacher>> getTeachers();

    @POST("/teacher")
    Observable<Teacher> addTeacher(@Body Teacher teacher);

    @PUT("/teacher")
    Observable<Teacher> updateTeacher(@Body Teacher teacher);

    @DELETE("/teacher/{id}")
    Completable deleteTeacher(@Path("id") int id);

    //

    @GET("/students")
    Observable<List<Student>> getStudents();

    @POST("/student")
    Observable<Student> addStudent(@Body Student student);

    @PUT("/student")
    Observable<Student> updateStudent(@Body Student student);

    @DELETE("/student/{id}")
    Completable deleteStudent(@Path("id") int id);

    //

    @GET("/parents")
    Observable<List<Parent>> getParents();

    @POST("/parent")
    Observable<Parent> addParent(@Body Parent student);

    @PUT("/parent")
    Observable<Parent> updateParent(@Body Parent student);

    @DELETE("/parent/{id}")
    Completable deleteParent(@Path("id") int id);





}
