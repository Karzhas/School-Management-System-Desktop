package kz.fizmat.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;



public class Student {

    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String nationality;
    private boolean gender;
    private String grade;
    private String mom;
    private String dad;
    private String classroomTeacher;
    private Double gpa;
    private String sportAchievements;
    private String achievementsAtTheOlympiads;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate yearOfAdmission;
    private boolean grantPlatnik;
    private boolean largeFamily;
    private boolean freeFromPhysicalEducation;

    public Student(){}

    public static class Builder {

        private int id;
        private String name;
        private String surname;
        private String patronymic;
        private String nationality;
        private boolean gender;
        private LocalDate birthDate;
        private String grade;
        private String mom;
        private String dad;
        private String classroomTeacher;
        private Double gpa;
        private String sportAchievements;
        private String achievementsAtTheOlympiads;
        private LocalDate yearOfAdmission;
        private boolean grantPlatnik;
        private boolean largeFamily;
        private boolean freeFromPhysicalEducation;

        public Builder() {
        }

        Builder(String name, int id, String surname, String patronymic, String nationality, LocalDate birthDate, String grade, String mom, String dad, String classroomTeacher, Double gpa, boolean gender, String sportAchievements, String achievementsAtTheOlympiads, LocalDate yearOfAdmission, boolean grantPlatnik, boolean largeFamily, boolean freeFromPhysicalEducation) {
            this.name = name;
            this.id = id;
            this.surname = surname;
            this.patronymic = patronymic;
            this.nationality = nationality;
            this.birthDate = birthDate;
            this.grade = grade;
            this.mom = mom;
            this.dad = dad;
            this.classroomTeacher = classroomTeacher;
            this.gpa = gpa;
            this.gender = gender;
            this.sportAchievements = sportAchievements;
            this.achievementsAtTheOlympiads = achievementsAtTheOlympiads;
            this.yearOfAdmission = yearOfAdmission;
            this.grantPlatnik = grantPlatnik;
            this.largeFamily = largeFamily;
            this.freeFromPhysicalEducation = freeFromPhysicalEducation;
        }

        public Builder name(String name){
            this.name = name;
            return Builder.this;
        }

        public Builder id(int id){
            this.id = id;
            return Builder.this;
        }

        public Builder surname(String surname){
            this.surname = surname;
            return Builder.this;
        }

        public Builder patronymic(String patronymic){
            this.patronymic = patronymic;
            return Builder.this;
        }

        public Builder nationality(String nationality){
            this.nationality = nationality;
            return Builder.this;
        }

        public Builder birthDate(LocalDate birthDate){
            this.birthDate = birthDate;
            return Builder.this;
        }

        public Builder grade(String grade){
            this.grade = grade;
            return Builder.this;
        }

        public Builder mom(String mom){
            this.mom = mom;
            return Builder.this;
        }

        public Builder dad(String dad){
            this.dad = dad;
            return Builder.this;
        }

        public Builder classroomTeacher(String classroomTeacher){
            this.classroomTeacher = classroomTeacher;
            return Builder.this;
        }

        public Builder gpa(Double gpa){
            this.gpa = gpa;
            return Builder.this;
        }

        public Builder gender(boolean gender){
            this.gender = gender;
            return Builder.this;
        }

        public Builder sportAchievements(String sportAchievements){
            this.sportAchievements = sportAchievements;
            return Builder.this;
        }

        public Builder achievementsAtTheOlympiads(String achievementsAtTheOlympiads){
            this.achievementsAtTheOlympiads = achievementsAtTheOlympiads;
            return Builder.this;
        }

        public Builder yearOfAdmission(LocalDate yearOfAdmission){
            this.yearOfAdmission = yearOfAdmission;
            return Builder.this;
        }

        public Builder isGrant(boolean isGrant){
            this.grantPlatnik = isGrant;
            return Builder.this;
        }

        public Builder isLargeFamily(boolean isLargeFamily){
            this.largeFamily = isLargeFamily;
            return Builder.this;
        }

        public Builder freeFromPhysicalEducation(boolean freeFromPhysicalEducation){
            this.freeFromPhysicalEducation = freeFromPhysicalEducation;
            return Builder.this;
        }

        public Student build() {

            return new Student(this);
        }
    }

    private Student(Builder builder) {
        this.name = builder.name;
        this.id = builder.id;
        this.surname = builder.surname;
        this.patronymic = builder.patronymic;
        this.nationality = builder.nationality;
        this.birthDate = builder.birthDate;
        this.grade = builder.grade;
        this.mom = builder.mom;
        this.dad = builder.dad;
        this.classroomTeacher = builder.classroomTeacher;
        this.gpa = builder.gpa;
        this.gender = builder.gender;
        this.sportAchievements = builder.sportAchievements;
        this.achievementsAtTheOlympiads = builder.achievementsAtTheOlympiads;
        this.yearOfAdmission = builder.yearOfAdmission;
        this.grantPlatnik = builder.grantPlatnik;
        this.largeFamily = builder.largeFamily;
        this.freeFromPhysicalEducation = builder.freeFromPhysicalEducation;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMom() {
        return mom;
    }

    public void setMom(String mom) {
        this.mom = mom;
    }

    public String getDad() {
        return dad;
    }

    public void setDad(String dad) {
        this.dad = dad;
    }

    public String getClassroomTeacher() {
        return classroomTeacher;
    }

    public void setClassroomTeacher(String classroomTeacher) {
        this.classroomTeacher = classroomTeacher;
    }

    public Double getGpa() {
        return gpa;
    }


    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public String getSportAchievements() {
        return sportAchievements;
    }

    public void setSportAchievements(String sportAchievements) {
        this.sportAchievements = sportAchievements;
    }

    public String getAchievementsAtTheOlympiads() {
        return achievementsAtTheOlympiads;
    }

    public void setAchievementsAtTheOlympiads(String achievementsAtTheOlympiads) {
        this.achievementsAtTheOlympiads = achievementsAtTheOlympiads;
    }

    public LocalDate getYearOfAdmission() {
        return yearOfAdmission;
    }

    public void setYearOfAdmission(LocalDate yearOfAdmission) {
        this.yearOfAdmission = yearOfAdmission;
    }


    public boolean isGrantPlatnik() {
        return grantPlatnik;
    }

    public void setGrantPlatnik(boolean grantPlatnik) {
        this.grantPlatnik = grantPlatnik;
    }

    public boolean isLargeFamily() {
        return largeFamily;
    }

    public void setLargeFamily(boolean largeFamily) {
        this.largeFamily = largeFamily;
    }

    public boolean isFreeFromPhysicalEducation() {
        return freeFromPhysicalEducation;
    }

    public void setFreeFromPhysicalEducation(boolean freeFromPhysicalEducation) {
        this.freeFromPhysicalEducation = freeFromPhysicalEducation;
    }
}
