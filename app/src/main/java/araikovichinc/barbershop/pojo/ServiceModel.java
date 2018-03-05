package araikovichinc.barbershop.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tigran on 28.02.2018.
 */

public class ServiceModel implements Serializable {
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("price")
    @Expose
    int price;
    @SerializedName("id")
    @Expose
    int id;

    public ServiceModel(String title, int price, int id) {
        this.title = title;
        this.price = price;
        this.id = id;
    }

    public ServiceModel(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
