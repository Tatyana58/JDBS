import lombok.*;
import model.City;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    private int id;
    private String first_name;
    private String last_name;
    private String gender;
    private int age;
    private City city;
}
