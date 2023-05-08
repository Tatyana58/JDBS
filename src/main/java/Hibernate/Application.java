package Hibernate;

import Hibernate.model.City;
import Hibernate.model.Employee;
import Hibernate.service.CityDao;
import Hibernate.service.CityDaoImpl;
import Hibernate.service.EmployeeDAO;
import Hibernate.service.EmployeeDaoImpl;
import org.hibernate.Session;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Application {
//    public static final String user = "postgres";
//    public static final String password = "8791103";
//    public static final String url = "jdbc:postgresql://localhost:5432/skypro";
 public static Session session= HibernateSessionFactoryUtil.getSessionFactory().openSession();
    public static int x;
    public static void main(String[] args) throws SQLException {
        //printCity();                                        // выводит все города работает

        Scanner scanner = new Scanner(System.in);
        Employee employee1 = new Employee();
        employee1 = new Employee(13,"Svetkina","Sasha","women",34,null);
        EmployeeDAO employeeDAO = new EmployeeDaoImpl();
        Employee employee2 = new Employee();
        employee2 = new Employee(1,"Lbov","Sergey","men",21,null);
        CityDao cityDao = new CityDaoImpl();
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
                    employeeDAO.addEmployeeTable(employee2);                         //работает
                    break;
                case 2:
                    System.out.println("*  2-Получение конкретного объекта Employee по id.         *");
                    System.out.print("Введите число:");
                    x = scanner.nextInt();
                    employeeDAO.getEmployeeId(x);                        // работает
                    break;
                case 3:
                    System.out.println("*  3-Получение списка всех объектов Employee из базы.      *");
                    employeeDAO.getAllEmployeeTable();                   //работает
                    break;
                case 4:
                    System.out.println("*  4-Изменение конкретного объекта Employee в базе по id.  *");
                    System.out.print("Введите число:");
                    x = scanner.nextInt();
                    employeeDAO.updateEmployeeId(x, employee1);         //работает
                    break;
                case 5:
                    System.out.println("*  5-Удаление конкретного объекта Employee из базы по id.  *");
                    System.out.print("Введите число:");
                    x = scanner.nextInt();
                    employeeDAO.deleteEmployeeId(x);                   // работает
                    break;
                case 6:
                    //РАБОТАЕТ
                    System.out.println("*  1-Создание объекта City в таблицу.    *");
                    City city = City.builder()
                            .city_Name("Praga")
                            .employees(List.of())
                            .build();
                    cityDao.addCity(city);
                    System.out.println("Добавили город :"+city);
                    break;
                case 7:
                    //РАБОТАЕТ
                    System.out.println("*  7-Изменить название объекта City на другое по id.  *");
                    City city1 = City.builder()
                            .city_id(44)
                            .city_Name("Samara")
                            .build();
                    System.out.println("Город :"+cityDao.getAllCity().contains(city1));
                    cityDao.getById(44).getCity_Name();
                    city1.setCity_Name("Vladivostok");
                    city1.setCity_id(44);
                    cityDao.updateCity(city1);
                    System.out.println("Изменили на :"+cityDao.getAllCity().contains(city1) +"на :" + city1 );
                    break;
                case 8:
                    //РАБОТАЕТ!!!
                    System.out.println("*  8-Укажите сотрудников в объекте City. Сохраните город и убедитесь, " +
                            "что сотрудники тоже сохранились в базе данных. *");
                    City city2 = City.builder()
                            .city_Name("Paris")
                            .employees(List.of())
                            .build();
                    cityDao.addCity(city2);
                    Employee employee =Employee.builder()
                            .first_name("Grishina")
                            .last_name("liza")
                            .gender("women")
                            .age(40)
                            .city(city2)
                            .build();
                    Employee employee3=Employee.builder()
                            .first_name("Ershova")
                            .last_name("lina")
                            .gender("women")
                            .age(19)
                            .city(city2)
                            .build();
                    city2.setEmployees(List.of(employee,employee3));
                    City updateCity = cityDao.updateCity(city2);
                    employeeDAO.getAllEmployeeTable().containsAll(updateCity.getEmployees());
                    cityDao.getById(updateCity.getCity_id());
                    break;
                case 9:
                    //РАБОТАЕТ
                    System.out.println("*  -Удалите экземпляр City из базы данных и убедитесь, что и город," +
                            " и ссылающиеся на него сотрудники удалены.  *");
                    City city4 = City.builder().city_Name("Erevan").build();
                    cityDao.deleteCity(city4);
                    System.out.println("Удалили город"+city4 + " и сотрудника "+Employee.EmployeeBuilder.class);
                    break;
                case 10:
                    //РАБОТАЕТ
                    System.out.println("Замените одного из сотрудников в городе, обновите сущность в базе данных и убедитесь," +
                            " что сотрудник изменился в БД.");
                    City city5 = City.builder()
                            .city_Name("Penza")
                            .city_id(2)
                            .employees(List.of())
                            .build();
                    Employee employee6 =Employee.builder()
                            .first_name("Kozlova")
                            .last_name("Lola")
                            .gender("women")
                            .age(81)
                            .city(city5)
                            .build();
                    employee6.setFirst_name("Annenkova");
                    employee6.setLast_name("Uliya");
                    employee6.setAge(25);
                    employeeDAO.updateEmployeeId(40,employee6);
                    cityDao.updateCity(city5);
                    city5.setEmployees(List.of(employee6));
                    City updateCity1 = cityDao.updateCity(city5);
                    cityDao.getById(updateCity1.getCity_id());
                    break;
                default:
                    System.out.println("Вы ввели не верное число.");
                    break;
            }
        }
    }

    public static void menu() {
        System.out.println("*********************************************************************");
        System.out.println("*                    Mеню                                           *");
        System.out.println("*  0-выход                                                          *");
        System.out.println("*  1-Создание (добавление) сущности Employee в таблицу.             *");
        System.out.println("*  2-Получение конкретного объекта Employee по id.                  *");
        System.out.println("*  3-Получение списка всех объектов Employee из базы.               *");
        System.out.println("*  4-Изменение конкретного объекта Employee в базе по id.           *");
        System.out.println("*  5-Удаление конкретного объекта Employee из базы по id.           *");
        System.out.println("*  6-Создание объекта City в таблицу.                               *");
        System.out.println("*  7-Изменить название объекта City на другое.                      *");
        System.out.println("*  8-Укажите сотрудников в объекте City. Сохраните город и " +
                                "убедитесь, что сотрудники тоже сохранились в базе данных.      *");
        System.out.println("*  9-Удалите экземпляр City из базы данных и убедитесь, что и город," +
                                       " и ссылающиеся на него сотрудники удалены.              *");
        System.out.println("*  10-Замените одного из сотрудников в городе, обновите сущность " +
                            "в базе данных и убедитесь, что сотрудник изменился в БД.           *");
        System.out.println("*********************************************************************");

    }
//    public static void printCity() {   // выводит все города работает
//        try (final Connection connection =
//                     DriverManager.getConnection(url, user, password);
//             PreparedStatement statement =
//                     connection.prepareStatement("" + "SELECT * FROM city")) {
//            System.out.println("Соединение установлено!");
//            final ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                int city_idOf = resultSet.getInt("city_id");
//                System.out.print("id : " + city_idOf);
//                String city_name = " , City : " + resultSet.getString("city_name");
//                System.out.println(city_name);
//            }
//        } catch (SQLException e) {
//            System.out.println("Ошибка при подключении к базе данных!");
//            e.printStackTrace();
//            // Исключение для обработки возможных ошибок при подключении
//        }
//    }
//    public static void printAllEmployee() { // выводит всех сотрудников
//        try (final Connection connection =
//                     DriverManager.getConnection(url, user, password);
//             PreparedStatement statement =
//                     connection.prepareStatement(""+"SELECT * FROM employee WHERE id = (?)")) {
//            System.out.println("Соединение установлено!");
//            for (int x=1;x<=10;x++) {
//                statement.setInt(1, x); // где 8 - ID сотрудника
//                final ResultSet resultSet = statement.executeQuery();
//                if (resultSet.next()) {
//                    String firstName = "Name: " + resultSet.getString("first_name");
//                    String lastName = "LastName: " + resultSet.getString("last_name");
//                    String gender = "Gender: " + resultSet.getString(4);
//                    int age = resultSet.getInt(5);
//                    String city_id = ("City: " + resultSet.getInt(6));
//                    System.out.println(firstName+", "+lastName+", "+gender+", "+ age+", "+ city_id);
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Ошибка при подключении к базе данных!");
//            e.printStackTrace();
//            // Исключение для обработки возможных ошибок при подключении
//        }
//    }

}