package kz.fizmat.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;


public class Teacher {

    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String nationality;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate qualificationDate;

    private String education;
    private String qualification;
    private boolean graduated;
    private boolean fullTimeEmployee;
    private boolean gender;
    private Integer pedagogicalExperience;
    private Integer generalExperience;
    private String category;
    private String teachesInGrades;
    private String maritalStatus;
    private Integer numberOfChildren;
    private String awards;

    public Teacher(){}

    public static class Builder {

        private int id;
        private String name;
        private String surname;
        private String patronymic;
        private String nationality;
        private LocalDate birthDate;
        private LocalDate qualificationDate;
        private String education;
        private String qualification;
        private boolean graduated;
        private boolean fullTimeEmployee;
        private boolean gender;
        private Integer pedagogicalExperience;
        private Integer generalExperience;
        private String category;
        private String teachesInGrades;
        private String maritalStatus;
        private Integer numberOfChildren;
        private String awards;

        public Builder() {
        }

        Builder(int id, String name, String surname, String patronymic, String nationality, LocalDate birthDate, LocalDate qualificationDate, String education, String qualification, boolean graduated, boolean fullTimeEmployee, boolean gender, Integer pedagogicalExperience, Integer generalExperience, String category, String teachesInGrades, String maritalStatus, Integer numberOfChildren, String awards) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.patronymic = patronymic;
            this.nationality = nationality;
            this.birthDate = birthDate;
            this.qualificationDate = qualificationDate;
            this.education = education;
            this.qualification = qualification;
            this.graduated = graduated;
            this.fullTimeEmployee = fullTimeEmployee;
            this.gender = gender;
            this.pedagogicalExperience = pedagogicalExperience;
            this.generalExperience = generalExperience;
            this.category = category;
            this.teachesInGrades = teachesInGrades;
            this.maritalStatus = maritalStatus;
            this.numberOfChildren = numberOfChildren;
            this.awards = awards;
        }

        public Builder id(int id){
            this.id = id;
            return Builder.this;
        }

        public Builder name(String name){
            this.name = name;
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

        public Builder qualificationDate(LocalDate qualificationDate){
            this.qualificationDate = qualificationDate;
            return Builder.this;
        }

        public Builder education(String education){
            this.education = education;
            return Builder.this;
        }

        public Builder qualification(String qualification){
            this.qualification = qualification;
            return Builder.this;
        }

        public Builder graduated(boolean graduated){
            this.graduated = graduated;
            return Builder.this;
        }

        public Builder fullTimeEmployee(boolean fullTimeEmployee){
            this.fullTimeEmployee = fullTimeEmployee;
            return Builder.this;
        }

        public Builder gender(boolean gender){
            this.gender = gender;
            return Builder.this;
        }

        public Builder pedagogicalExperience(Integer pedagogicalExperience){
            this.pedagogicalExperience = pedagogicalExperience;
            return Builder.this;
        }

        public Builder generalExperience(Integer generalExperience){
            this.generalExperience = generalExperience;
            return Builder.this;
        }

        public Builder category(String category){
            this.category = category;
            return Builder.this;
        }

        public Builder teachesInGrades(String teachesInGrades){
            this.teachesInGrades = teachesInGrades;
            return Builder.this;
        }

        public Builder maritalStatus(String maritalStatus){
            this.maritalStatus = maritalStatus;
            return Builder.this;
        }

        public Builder numberOfChildren(Integer numberOfChildren){
            this.numberOfChildren = numberOfChildren;
            return Builder.this;
        }

        public Builder awards(String awards){
            this.awards = awards;
            return Builder.this;
        }

        public Teacher build() {

            return new Teacher(this);
        }
    }

    private Teacher(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.patronymic = builder.patronymic;
        this.nationality = builder.nationality;
        this.birthDate = builder.birthDate;
        this.qualificationDate = builder.qualificationDate;
        this.education = builder.education;
        this.qualification = builder.qualification;
        this.graduated = builder.graduated;
        this.fullTimeEmployee = builder.fullTimeEmployee;
        this.gender = builder.gender;
        this.pedagogicalExperience = builder.pedagogicalExperience;
        this.generalExperience = builder.generalExperience;
        this.category = builder.category;
        this.teachesInGrades = builder.teachesInGrades;
        this.maritalStatus = builder.maritalStatus;
        this.numberOfChildren = builder.numberOfChildren;
        this.awards = builder.awards;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getQualificationDate() {
        return qualificationDate;
    }

    public void setQualificationDate(LocalDate qualificationDate) {
        this.qualificationDate = qualificationDate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }

    public boolean isFullTimeEmployee() {
        return fullTimeEmployee;
    }

    public void setFullTimeEmployee(boolean fullTimeEmployee) {
        this.fullTimeEmployee = fullTimeEmployee;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Integer getPedagogicalExperience() {
        return pedagogicalExperience;
    }

    public void setPedagogicalExperience(Integer pedagogicalExperience) {
        this.pedagogicalExperience = pedagogicalExperience;
    }

    public Integer getGeneralExperience() {
        return generalExperience;
    }

    public void setGeneralExperience(Integer generalExperience) {
        this.generalExperience = generalExperience;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTeachesInGrades() {
        return teachesInGrades;
    }

    public void setTeachesInGrades(String teachesInGrades) {
        this.teachesInGrades = teachesInGrades;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }



}
