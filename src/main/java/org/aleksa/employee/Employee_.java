package org.aleksa.employee;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated("EclipseLink")
@StaticMetamodel(Employee.class)
public class Employee_ {

    public static volatile SingularAttribute<Employee, Integer> id;
    public static volatile SingularAttribute<Employee, String> name;
    public static volatile SingularAttribute<Employee, String> address;
    public static volatile SingularAttribute<Employee, Integer> age;

}
