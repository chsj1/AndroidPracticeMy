package test.hjd.com.storage2;

/**
 * Created by huangjundong on 2017/3/3.
 */

public class Person {
    int id;
    String name;
    int salary;

    public Person(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
