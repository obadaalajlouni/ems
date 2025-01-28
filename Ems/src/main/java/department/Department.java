package department;

import com.example.Ems.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(
            generator ="department_id",
strategy = GenerationType.SEQUENCE
    )
    private Integer id;
    private String name;
    private String description;
@OneToMany
    private List<Employee> employees;
}
