package Hibernate;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"id"})

@Table(name = "employee")
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String first_name;
    @Column
    private String last_name;
    @Column
    private String gender;
    @Column
    private int age;
    @Column(name = "city_id")
    private int city;
}
