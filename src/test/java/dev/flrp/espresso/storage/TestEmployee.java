package dev.flrp.espresso.storage;

import java.sql.Timestamp;

public class TestEmployee {

    public int id;
    public String name;
    public int age;
    public String department;
    public Timestamp createdAt;
    public Timestamp updatedAt;

    public TestEmployee(int id, String name, int age, String department, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", department=" + department
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

}
