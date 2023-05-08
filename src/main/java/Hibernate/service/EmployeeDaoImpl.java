package Hibernate.service;

import Hibernate.HibernateSessionFactoryUtil;
import Hibernate.model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDAO {
    @Override
    public Integer addEmployeeTable(Employee employee) {
        Integer id;
        try (Session session= HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            id = (Integer) session.save(employee);
            transaction.commit();
        }return id;
    }

    @Override
    public Employee getEmployeeId(int id) {
        try (Session session= HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class,id);
        }
    }

    @Override
    public List<Employee> getAllEmployeeTable() {
        try (Session session= HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            System.out.println("Все элементы :");
            return session.createSQLQuery("SELECT*FROM employee").list();
        }
    }

    @Override
    public void updateEmployeeId(int id, Employee employee) {
        try (Session session= HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee.setId(id);
            session.update(employee);
            transaction.commit();
        }
    }

    @Override
    public void deleteEmployeeId(int id) {
        try (Session session= HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(getEmployeeId(id));
            transaction.commit();
        }
    }

//    @Override
//    public void addEmployeeTable() {
//        try(PreparedStatement statement = ConnectionConfig.getConnection()
//                .prepareStatement("INSERT INTO employee (first_name,last_name,gender,age,city_id) " +
//                        "VALUES ((?),(?),(?),(?),(?))");){
//            statement.setString(1, "Klopov");
//            statement.setString(2, "Gleb");
//            statement.setString(3, "men");
//            statement.setInt(4,21);
//            statement.setInt(5,4);
//            System.out.println("Добавили сотрудника в таблицу.");
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Employee getEmployeeId(int id) {
//        Employee employee=new Employee();
//        try (PreparedStatement statement = ConnectionConfig.getConnection().
//                prepareStatement("SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id WHERE id=(?)");){
//            statement.setInt(1,id);
//            ResultSet resultSet= statement.executeQuery();
//            while (resultSet.next()) {
//                employee.setId(resultSet.getInt(1));
//                employee.setFirst_name(resultSet.getString("first_name"));
//                employee.setLast_name(resultSet.getString("last_name"));
//                employee.setGender(resultSet.getString("gender"));
//                employee.setAge(resultSet.getInt(5));
//                employee.setCity(new City((resultSet.getInt("city_id")),resultSet.getString("city_name")));
//                System.out.println("id: " + resultSet.getInt(1) + ", " + employee.getFirst_name() + ", " + employee.getLast_name() + ", " + employee.getGender() + ", " + employee.getAge() + ", " + employee.getCity());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (employee.getFirst_name() == null) {
//            System.out.println("Нет данных. Сотрудника с таким id - не существует.");
//        }
//        return employee;
//    }
//    @Override
//    public List<Employee> getAllEmployeeTable() {
//        List<Employee> employees= new ArrayList<>();
//        try(PreparedStatement statement = ConnectionConfig.getConnection().
//                prepareStatement("SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id");){
//            ResultSet resultSet= statement.executeQuery();
//            while (resultSet.next()) {
//                int id=Integer.parseInt(resultSet.getString(1));
//                String firstName=resultSet.getString("first_name");
//                String lastName=resultSet.getString("last_name");
//                String gender=resultSet.getString("gender");
//                int age=Integer.parseInt(resultSet.getString(5));
//                City city = new City((resultSet.getInt("city_id")),resultSet.getString("city_name"));
//                employees.add(new Employee(id,firstName,lastName,gender,age,city));
//                System.out.println("id: "+resultSet.getInt(1)+", "+firstName+", "+lastName+", "+gender+", "+ age+", "+ city.getCity_id()+", "+city.getCity_Name());
//            }
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return employees;
//    }
//
//    @Override
//    public void updateEmployeeId(int id,Employee employee) {
//        //employee=new Employee(13, "Rew", "Leva", "men", 54, new City(1,"ваы"));
//        try(PreparedStatement statement = ConnectionConfig.getConnection()
//                .prepareStatement("UPDATE employee SET first_name=(?),last_name=(?),gender=(?),age=(?),city_id=(?) WHERE id=(?)")){
//            statement.setString(1,"Svetkin");
//            statement.setString(2,"Slava");
//            statement.setString(3,"men");
//            statement.setInt(4,43);
//            statement.setInt(5, 1);
//            statement.setInt(6, id);
//            System.out.println("Изменили данные в таблице.");
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }



//
//    @Override
//    public void deleteEmployeeId(int id) {
//        try(PreparedStatement statement = ConnectionConfig.getConnection()
//                .prepareStatement("DELETE FROM employee WHERE id=(?)")){
//            statement.setInt(1,id);
//            System.out.println("Удалили сотрудника по id: " + id);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
