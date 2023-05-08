package Hibernate.service;

import Hibernate.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    //void addEmployeeTable();

    Employee getEmployeeId(int id);
    List<Employee> getAllEmployeeTable();
    // List<Employee> getAddEmployeeTable();
    void updateEmployeeId(int id, Employee employee);
    void deleteEmployeeId(int id);

    Integer addEmployeeTable(Employee employee);
}