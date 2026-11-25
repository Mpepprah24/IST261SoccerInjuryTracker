package model;  // ← FIRST LINE

public class MedicalStaff extends Person {  // Person is in same package
    private String staffID, role, specialization, contactInfo;
    private String department;
    private int yearsOfExperience;

    public MedicalStaff(String name, int age, String staffID, String role,
                        String specialization, String contactInfo,
                        String department, int yearsOfExperience) {
        super(name, age, staffID);
        this.staffID = staffID;
        this.role = role;
        this.specialization = specialization;
        this.contactInfo = contactInfo;
        this.department = department;
        this.yearsOfExperience = yearsOfExperience;
    }

    public MedicalStaff(String staffID, String name, String role,
                        String specialization, String contactInfo) {
        super(name, 0, staffID);
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.specialization = specialization;
        this.contactInfo = contactInfo;
        this.department = "General";
        this.yearsOfExperience = 0;
    }

    @Override
    public String getRole() {
        return "Medical Staff - " + role + " (" + specialization + " Specialist)";
    }

    @Override
    public String getDescription() {
        return "MEDICAL PROFESSIONAL: " + name + " (" + age + " years old)" +
                "\n  Role: " + role +
                "\n  Specialization: " + specialization +
                "\n  Department: " + department +
                "\n  Experience: " + yearsOfExperience + " years" +
                "\n  Contact: " + contactInfo;
    }

    // Getters and setters
    public String getStaffID() { return staffID; }
    public void setStaffID(String staffID) { this.staffID = staffID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getRoleField() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public int getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(int yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    @Override
    public String toString() {
        return "Medical Staff Details:\n" +
                "  Staff ID: " + staffID + "\n" +
                "  Name: " + name + "\n" +
                "  Age: " + age + "\n" +
                "  Role: " + role + "\n" +
                "  Specialization: " + specialization + "\n" +
                "  Department: " + department + "\n" +
                "  Experience: " + yearsOfExperience + " years\n" +
                "  Contact: " + contactInfo;
    }
}