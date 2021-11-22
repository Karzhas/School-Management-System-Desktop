package kz.fizmat.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public class Parent {

    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String nationality;
    private boolean gender;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;

    public Parent(){}

    public static class Builder {

        private int id;
        private String name;
        private String surname;
        private String patronymic;
        private String nationality;
        private boolean gender;
        private LocalDate birthDate;

        public Builder() {
        }

        Builder(int id, String name, String surname, String patronymic, String nationality, boolean gender, LocalDate birthDate) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.patronymic = patronymic;
            this.nationality = nationality;
            this.gender = gender;
            this.birthDate = birthDate;
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

        public Builder gender(boolean gender){
            this.gender = gender;
            return Builder.this;
        }

        public Builder birthDate(LocalDate birthDate){
            this.birthDate = birthDate;
            return Builder.this;
        }

        public Parent build() {

            return new Parent(this);
        }
    }

    private Parent(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.patronymic = builder.patronymic;
        this.nationality = builder.nationality;
        this.gender = builder.gender;
        this.birthDate = builder.birthDate;
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

    public void doSomething() {
        // do something
    }
}
