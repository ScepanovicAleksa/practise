package org.aleksa.employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
@NamedQueries({
        @NamedQuery(name = Employee.FIND_ALL, query = "SELECT e FROM Employee e"),
        @NamedQuery(name = Employee.FIND_BY_NAME, query = "SELECT e FROM Employee e WHERE e.name = :name")
})
public class Employee {

    public static final String FIND_ALL = "Employee.findAll";
    public static final String FIND_BY_NAME="Employee.findByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotNull @Min(18)
    private Integer age;



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
