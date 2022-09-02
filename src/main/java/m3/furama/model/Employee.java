package m3.furama.model;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String fullName;
    private LocalDate birthday;
    private String identifyNumber;
    private Double salary;
    private String phone;
    private String email;
    private String address;
    private int positionId;
    private int degreeId;
    private int departmentId;

    public Employee(int id, String fullName, LocalDate birthday, String identifyNumber, Double salary, String phone, String email, String address, int positionId, int degreeId, int departmentId) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
        this.salary = salary;
        this.identifyNumber = identifyNumber;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.positionId = positionId;
        this.degreeId = degreeId;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getIdentifyNumber() {
        return identifyNumber;
    }

    public void setIdentifyNumber(String identifyNumber) {
        this.identifyNumber = identifyNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(int degreeId) {
        this.degreeId = degreeId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
