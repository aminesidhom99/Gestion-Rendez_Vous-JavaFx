/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


/**
 * This class represents a doctor.
 */
public class doctor {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private int age;
    private int cin;
    private String password;
    private String roleperm;
    private String diplome;
    private String specialite;
     public static doctor Current_doctor;

    public doctor(int id, String firstName, String lastName, int cin, String email, String address, int age, String password, String roleperm, String diplome, String specialite) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cin = cin;
        this.address = address;
        this.age = age;
        this.password = password;
        this.roleperm = roleperm;
        this.diplome = diplome;
        this.specialite = specialite;
    }

    public doctor() {

    }

    public doctor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
      public static doctor getCurrent_doctor() {
        return Current_doctor;
    }

    public static void setCurrent_doctor(doctor Current_doctor) {
        doctor.Current_doctor = Current_doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleperm() {
        return roleperm;
    }

    public void setRoleperm(String roleperm) {
        this.roleperm = roleperm;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "doctor{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", cin=" + cin + ", email=" + email + ", address=" + address + ", age=" + age + ", password=" + password + ", roleperm=" + roleperm + ", diplome=" + diplome + ", specialite=" + specialite + '}';
    }
}