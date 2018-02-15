package araikovichinc.barbershop.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tigran on 12.02.2018.
 */

public class GenderCard {
    @SerializedName("_id")
    @Expose
    private int sexId;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    public GenderCard(int sexId, String title, String imageUrl) {
        this.sexId = sexId;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public GenderCard(){};

    public int getSexId() {
        return sexId;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setSexId(int sexId) {
        this.sexId = sexId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

