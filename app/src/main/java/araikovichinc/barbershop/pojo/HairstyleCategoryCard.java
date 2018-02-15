package araikovichinc.barbershop.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tigran on 12.02.2018.
 */

public class HairstyleCategoryCard {
    @SerializedName("_id")
    @Expose
    private int id;

    @SerializedName("sex_id")
    @Expose
    private int sexId;

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    @SerializedName("title")
    @Expose
    private String title;

    public HairstyleCategoryCard(int id, int sexId, String imageUrl, String title) {
        this.id = id;
        this.sexId = sexId;
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public HairstyleCategoryCard(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSexId() {
        return sexId;
    }

    public void setSexId(int sexId) {
        this.sexId = sexId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
