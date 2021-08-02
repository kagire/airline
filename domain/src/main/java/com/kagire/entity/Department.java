package com.kagire.entity;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void cloneData(Department department){
        this.name = department.getName();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;

        if (obj == null || obj.getClass() != this.getClass()) return false;

        Department department2 = (Department) obj;
        return this.name.equals(department2.getName());
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }
}
