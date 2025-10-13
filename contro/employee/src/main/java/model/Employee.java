public class Employee {
private long id;
private String name;
private String role;
private String email;
private String phno;
private double salary;


public Employee() {
}


public Employee(long id, String name, String role, String email, String phno, double salary) {
    this.id = id;
    this.name = name;
    this.role = role;
    this.email = email;
    this.phno = phno;
    this.salary = salary;
}


public long getId() {
    return id;
}


public void setId(long id) {
    this.id = id;
}


public String getName() {
    return name;
}


public void setName(String name) {
    this.name = name;
}


public String getRole() {
    return role;
}


public void setRole(String role) {
    this.role = role;
}


public String getEmail() {
    return email;
}


public void setEmail(String email) {
    this.email = email;
}


public String getPhno() {
    return phno;
}


public void setPhno(String phno) {
    this.phno = phno;
}


public double getSalary() {
    return salary;
}


public void setSalary(double salary) {
    this.salary = salary;
}




}
