package araikovichinc.barbershop.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tigran on 05.03.2018.
 */

public class TimeModel {
    @Expose
    @SerializedName("tome_from_hours")
    int timeFromHours;
    @Expose
    @SerializedName("tome_to_min")
    int timeToMin;
    @Expose
    @SerializedName("tome_to_hours")
    int timeToHours;
    @Expose
    @SerializedName("tome_from_min")
    int timeFromMin;
    @Expose
    @SerializedName("day")
    int day;
    @Expose
    @SerializedName("month")
    int month;
    @Expose
    @SerializedName("year")
    int year;

    public TimeModel(int timeFromHours, int timeToMin, int timeToHours, int timeFromMin, int day, int month, int year) {
        this.timeFromHours = timeFromHours;
        this.timeToMin = timeToMin;
        this.timeToHours = timeToHours;
        this.timeFromMin = timeFromMin;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public TimeModel(){}

    public int getTimeFromHours() {
        return timeFromHours;
    }

    public void setTimeFromHours(int timeFromHours) {
        this.timeFromHours = timeFromHours;
    }

    public int getTimeToMin() {
        return timeToMin;
    }

    public void setTimeToMin(int timeToMin) {
        this.timeToMin = timeToMin;
    }

    public int getTimeToHours() {
        return timeToHours;
    }

    public void setTimeToHours(int timeToHours) {
        this.timeToHours = timeToHours;
    }

    public int getTimeFromMin() {
        return timeFromMin;
    }

    public void setTimeFromMin(int timeFromMin) {
        this.timeFromMin = timeFromMin;
    }

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
}
