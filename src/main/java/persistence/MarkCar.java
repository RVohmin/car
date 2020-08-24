package persistence;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "marks")
public class MarkCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public static MarkCar of(String name) {
        MarkCar markCar = new MarkCar();
        markCar.name = name;
        return markCar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarkCar markCar = (MarkCar) o;
        return id == markCar.id &&
                Objects.equals(name, markCar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
