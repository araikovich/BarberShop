package araikovichinc.barbershop.pojo;

import java.io.Serializable;

/**
 * Created by Tigran on 17.02.2018.
 */

public class Reservation implements Serializable {
    private int day;
    private int month;
    private int year;
    private int gender;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
