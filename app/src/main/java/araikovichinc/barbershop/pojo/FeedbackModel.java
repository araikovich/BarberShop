package araikovichinc.barbershop.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Tigran on 17.03.2018.
 */

public class FeedbackModel implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("day")
    @Expose
    private int day;
    @SerializedName("month")
    @Expose
    private int month;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("rating")
    @Expose
    private int rating;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("text")
    @Expose
    private String text;

    public FeedbackModel(){}

    public FeedbackModel(int id, int day, int month, int year, int rating, String title, String text) {
        this.id = id;
        this.day = day;
        this.month = month;
        this.year = year;
        this.rating = rating;
        this.title = title;
        this.text = text;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
