package com.mendoza.ryzellrowayne;

public class Person {

    String fname, gender;
    int age;

    public Person(String fname, String gender, int age) {
        this.fname = fname;
        this.gender = gender;
        this.age = age;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
