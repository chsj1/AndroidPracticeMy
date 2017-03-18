package test.hjd.com.androidpracticemy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by huangjundong on 2017/2/25.
 */

public class Person2 implements Parcelable {

    private String username;
    private int salary;

    public Person2(String username, int salary) {
        this.username = username;
        this.salary = salary;
    }

    public Person2(Parcel source) {
        this.username = source.readString();
        this.salary = source.readInt();
    }

    public static final Creator<Person2> CREATOR = new Creator<Person2>() {
        @Override
        public Person2 createFromParcel(Parcel source) {
            return new Person2(source);
        }

        @Override
        public Person2[] newArray(int size) {
            return new Person2[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeInt(salary);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
