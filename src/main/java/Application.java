import java.sql.*;
import java.util.List;

public class Application {
    public static final String user = "postgres";
    public static final String password = "8791103";
    public static final String url = "jdbc:postgresql://localhost:5432/skypro";
    public static void main(String[] args) throws SQLException {

        //printCity();                                       // выводит все города работает
        EmployeeDAO employeeDAO = new EmployeeDaoImpl();
        //employeeDAO.addEmployeeTable();                         //работает
        //employeeDAO.getEmployeeId(6);                        // работает
        //employeeDAO.updateEmployeeId(2,new Employee());
        //employeeDAO.getAddEmployeeTable();
        //employeeDAO.deleteEmployeeId(0);                   // работает
    }

    public static void printCity() {
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
    public static void printAllEmployee() {
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
}