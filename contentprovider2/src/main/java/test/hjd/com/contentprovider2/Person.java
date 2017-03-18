package test.hjd.com.contentprovider2;

/**
 * Created by huangjundong on 2017/2/28.
 */

public class Person {
    public int id;
    public String name;
    public String salary;

    public Person(String name, String salary) {
        this.name = name;
        this.salary = salary;
    }

    public Person(int id, String name, String salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}
