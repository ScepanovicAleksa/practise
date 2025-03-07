package org.aleksa.employee;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EmployeeService {

    @PersistenceContext
    EntityManager em;


    public List<Employee> getEmployees(){
        TypedQuery<Employee> query = em.createNamedQuery(Employee.FIND_ALL, Employee.class);
        return query.getResultList();
    }

    public List<Employee> getEmployeesByName(String name){
        TypedQuery<Employee> query = em.createNamedQuery(Employee.FIND_BY_NAME, Employee.class)
                .setParameter("name", name);
        return query.getResultList();
    }

    //TODO Criteria
    public List<Employee> getEmployeesByAgeOlderThan(Integer age) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> employeeCriteriaQuery = builder.createQuery(Employee.class);
        Root<Employee> emp = employeeCriteriaQuery.from(Employee.class);

        Predicate agePredicate = builder.greaterThan(emp.get(Employee_.age), age);
        employeeCriteriaQuery.select(emp).where(agePredicate);

        return em.createQuery(employeeCriteriaQuery).getResultList();
    }

    @Transactional
    public void insertEmployee(Employee employee){
        em.persist(employee);
    }

    @Transactional
    public Employee updateEmployee(Integer id, Employee employee){
        Employee e= em.find(Employee.class, id);
        if(e == null){
            throw new EntityNotFoundException("Employee with id=" + id + " not found");
        }


        e.setAge(employee.getAge());
        e.setName(employee.getName());
        e.setAddress(employee.getAddress());
        return e;
    }

    @Transactional
    public void deleteEmployee(Integer id){
        Employee e = em.find(Employee.class, id);
        if(e == null){
            throw new EntityNotFoundException("Employee with id=" + id + " not found");
        }
        em.remove(e);
    }
}
