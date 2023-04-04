import model.City;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDaoService implements EmployeeDAO {
    @Override
    public void addEmployeeTable(Employee employee) {
        try(PreparedStatement statement = ConnectionConfig.getConnection()
                .prepareStatement("INSERT INTO employee (first_name,last_name,gender,age,city_id) " +
                        "VALUES ((?),(?),(?),(?),(?))");){
            statement.setString(1,employee.getFirst_name());
            statement.setString(2,employee.getLast_name());
            statement.setString(3,employee.getGender());
            statement.setInt(4,employee.getAge());
            statement.setInt(5,employee.getCity().getCity_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployeeId(int id) {
        Employee employee=new Employee();
        try (PreparedStatement statement = ConnectionConfig.getConnection().
                prepareStatement("SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id WHERE id=(?)");){

            statement.setInt(1,id);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next()) {
                employee.setId(resultSet.getInt(1));
                employee.setFirst_name(resultSet.getString("first_name"));
                employee.setLast_name("last_name");
                employee.setGender("gender");
                employee.setAge(5);
                employee.setCity(new City((resultSet.getInt("city_id")),resultSet.getString("city_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

//    @Override
//    public List<Employee> getAddEmployeeTable() {
//        List<Employee> employees = new ArrayList<>();
//        try(PreparedStatement statement = ConnectionConfig.getConnection().
//                prepareStatement("SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id");){
//            ResultSet resultSet= statement.executeQuery();
//            while (resultSet.next()) {
//                int id=Integer.parseInt(resultSet.getString(1));
//                String firstName=resultSet.getString("first_name");
//                String lastName=resultSet.getString("last_name");
//                String gender=resultSet.getString("gender");
//                int age=Integer.parseInt(resultSet.getString(25));
//                City city = new City((resultSet.getInt("city_id")),resultSet.getString("city_name"));
//                employees.add(String.valueOf(new Employee(id,firstName,lastName,gender,age,city)));
//            }
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return employees;
//    }

    @Override
    public void updateEmployeeId(int id, Employee employee) {

    }

    @Override
    public void deleteEmployeeId(int id) {

    }
}
