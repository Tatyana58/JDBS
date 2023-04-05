import java.util.List;

public interface EmployeeDAO {
    void addEmployeeTable(Employee employee);
    Employee getEmployeeId(int id);
    List<Employee> getAddEmployeeTable();
    // List<Employee> getAddEmployeeTable();
    void updateEmployeeId(int id, Employee employee);
    void deleteEmployeeId(int id);
}