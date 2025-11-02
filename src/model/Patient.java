package model;

public class Patient {
    private int patient_id;
    private String fullname;
    private int age;
    private String gender;
    private String blood_group_requires;
    private String phone;
    private String address;

    // 🔹 Getters & Setters
    public int getPatient_id() {
        return patient_id;
    }
    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBlood_group_requires() {
        return blood_group_requires;
    }
    public void setBlood_group_requires(String blood_group_requires) {
        this.blood_group_requires = blood_group_requires;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}