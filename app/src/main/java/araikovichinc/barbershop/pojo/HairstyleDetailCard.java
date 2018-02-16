package araikovichinc.barbershop.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tigran on 12.02.2018.
 */

public class HairstyleDetailCard {
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    @SerializedName("hairstyleId")
    @Expose
    private int hairstyleId;

    public HairstyleDetailCard(String imageUrl, int hairstyleId) {
        this.imageUrl = imageUrl;
        this.hairstyleId = hairstyleId;
    }

    public HairstyleDetailCard(){

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getHairstyleId() {
        return hairstyleId;
    }

    public void setHairstyleId(int hairstyleId) {
        this.hairstyleId = hairstyleId;
    }
}
