import model.City;

import java.awt.*;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static final String user = "postgres";
    public static final String password = "8791103";
    public static final String url = "jdbc:postgresql://localhost:5432/skypro";
    public static int x;
    public static void main(String[] args) throws SQLException {
        //printCity();                                        // выводит все города работает
        Scanner scanner = new Scanner(System.in);
        Employee employee1 = new Employee();
        EmployeeDAO employeeDAO = new EmployeeDaoImpl();
        Label:
        menu();
        System.out.print("Введите число:");
        if (scanner.hasNextInt()) {
            x = scanner.nextInt();
            switch (x) {
                case 0:
                    System.out.println("Выход\n");
                    break;
                case 1:
                    System.out.println("*  1-Создание (добавление) сущности Employee в таблицу.    *");
                    employeeDAO.addEmployeeTable();                         //работает
                    break;
                case 2:
                    System.out.println("*  2-Получение конкретного объекта Employee по id.         *");
                    System.out.println("Введите число:");
                    x = scanner.nextInt();
                    employeeDAO.getEmployeeId(x);                        // работает
                    break;
                case 3:
                    System.out.println("*  3-Получение списка всех объектов Employee из базы.      *");
                    employeeDAO.getAllEmployeeTable();                   //работает
                    break;
                case 4:
                    System.out.println("*  4-Изменение конкретного объекта Employee в базе по id.  *");
                    System.out.println("Введите число:");
                    x = scanner.nextInt();
                    employeeDAO.updateEmployeeId(x, employee1);         //работает
                    break;
                case 5:
                    System.out.println("*  5-Удаление конкретного объекта Employee из базы по id.  *");
                    System.out.println("Введите число:");
                    x = scanner.nextInt();
                    employeeDAO.deleteEmployeeId(x);                   // работает
                    break;
                default:
                    System.out.println("Вы ввели не верное число.");
                    break;
            }
        }
    }

    public static void printCity() {   // выводит все города работает
        try (final Connection connection =
                     DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement("" + "SELECT * FROM city")) {
            System.out.println("Соединение установлено!");
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int city_idOf = resultSet.getInt("city_id");
                System.out.print("id : " + city_idOf);
                String city_name = " , City : " + resultSet.getString("city_name");
                System.out.println(city_name);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
            // Исключение для обработки возможных ошибок при подключении
        }
    }
    public static void printAllEmployee() { // выводит всех сотрудников
        try (final Connection connection =
                     DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement(""+"SELECT * FROM employee WHERE id = (?)")) {
            System.out.println("Соединение установлено!");
            for (int x=1;x<=10;x++) {
                statement.setInt(1, x); // где 8 - ID сотрудника
                final ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String firstName = "Name: " + resultSet.getString("first_name");
                    String lastName = "LastName: " + resultSet.getString("last_name");
                    String gender = "Gender: " + resultSet.getString(4);
                    int age = resultSet.getInt(5);
                    String city_id = ("City: " + resultSet.getInt(6));
                    System.out.println(firstName+", "+lastName+", "+gender+", "+ age+", "+ city_id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
            // Исключение для обработки возможных ошибок при подключении
        }
    }
    public static void menu() {
        System.out.println("************************************************************");
        System.out.println("*                    Mеню                                  *");
        System.out.println("*  0-выход                                                 *");
        System.out.println("*  1-Создание (добавление) сущности Employee в таблицу.    *");
        System.out.println("*  2-Получение конкретного объекта Employee по id.         *");
        System.out.println("*  3-Получение списка всех объектов Employee из базы.      *");
        System.out.println("*  4-Изменение конкретного объекта Employee в базе по id.  *");
        System.out.println("*  5-Удаление конкретного объекта Employee из базы по id.  *");
        System.out.println("******************************");

    }
}