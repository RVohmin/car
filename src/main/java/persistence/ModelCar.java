package persistence;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "models")
public class ModelCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MarkCar> marks = new ArrayList<>();

    public static ModelCar of(String name) {
        ModelCar modelCar = new ModelCar();
        modelCar.name = name;
        return modelCar;
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

    public List<MarkCar> getMarks() {
        return marks;
    }

    public void setMarks(List<MarkCar> marks) {
        this.marks = marks;
    }

    public void addMark(MarkCar markCar) {
        this.marks.add(markCar);
    }

    @Override
    public String toString() {
        return "MarkCar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelCar modelCar = (ModelCar) o;
        return id == modelCar.id &&
                Objects.equals(name, modelCar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

