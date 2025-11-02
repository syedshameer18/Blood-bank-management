package model;

public class Donor {
	private int donor_id;
    private String fullname;
    private String blood_group;
    private int age;
    private String gender;
    private String phone;
    private String address;

    // Getters and Setters
    public int getdonor_id() {
        return donor_id;
    }
    public void setdonor_id(int donor_id) {
        this.donor_id = donor_id;
    }
    
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBlood_group() {
        return blood_group;
    }
    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
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