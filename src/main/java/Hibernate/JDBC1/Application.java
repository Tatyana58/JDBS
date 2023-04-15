package JDBC;
import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "8791103";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        try (final Connection connection =
                     DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement(""+"SELECT * FROM employee WHERE id = (?)")) {
            System.out.println("Соединение установлено!");
                statement.setInt(1, 10); // где 8 - ID сотрудника
                final ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    //int id = Integer.parseInt("id"+resultSet.getInt("id"));
                    String firstName = "Name: " + resultSet.getString("first_name");
                    String lastName = "LastName: " + resultSet.getString("last_name");
                    String gender = "Gender: " + resultSet.getString(4);
                    int age = resultSet.getInt(5);
                    String city_id = ("City: " + resultSet.getInt(6));
                    System.out.println("id: "+resultSet.getInt(1)+", "+firstName+", "+lastName+", "+gender+", "+ age+", "+ city_id);
                }

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
            // Исключение для обработки возможных ошибок при подключении
        }
    }
}
