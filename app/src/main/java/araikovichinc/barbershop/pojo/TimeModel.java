package araikovichinc.barbershop.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tigran on 05.03.2018.
 */

public class TimeModel {
    @Expose
    @SerializedName("time_from_hours")
    private int timeFromHours;
    @Expose
    @SerializedName("time_to_min")
    private int timeToMin;
    @Expose
    @SerializedName("time_to_hours")
    private int timeToHours;
    @Expose
    @SerializedName("time_from_min")
    private int timeFromMin;

    public TimeModel(int timeFromHours, int timeToMin, int timeToHours, int timeFromMin) {
        this.timeFromHours = timeFromHours;
        this.timeToMin = timeToMin;
        this.timeToHours = timeToHours;
        this.timeFromMin = timeFromMin;
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
}
