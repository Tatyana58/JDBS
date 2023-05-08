package Hibernate.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
@Builder
@Entity
@EqualsAndHashCode(of = "city_id")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int city_id;
    private String city_Name;
    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Employee> employees;

    @Override
    public String toString() {
        return "City{" +
                "city_id=" + city_id +
                ", city_Name='" + city_Name + '\'' +
                ", employees=" + employees +
                '}';
    }
}
