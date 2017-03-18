package test.hjd.com.androidpracticemy;

import java.io.Serializable;

/**
 * Created by huangjundong on 2017/2/25.
 */

public class Person implements Serializable {
    public String name;
    public int salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Person(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
}
