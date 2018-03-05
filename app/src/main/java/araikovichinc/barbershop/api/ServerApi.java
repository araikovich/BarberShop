package araikovichinc.barbershop.api;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.pojo.GenderCard;
import araikovichinc.barbershop.pojo.HairdresserModel;
import araikovichinc.barbershop.pojo.HairstyleCategoryCard;
import araikovichinc.barbershop.pojo.HairstyleDetailCard;
import araikovichinc.barbershop.pojo.ServiceModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Tigran on 12.02.2018.
 */

public interface ServerApi {
    @POST("/Barbershop/GetGenderCategories.php")
    Call<ArrayList<GenderCard>> getSexCategories();

    @FormUrlEncoded
    @POST("/Barbershop/GetHairstyleCategory.php")
    Call<ArrayList<HairstyleCategoryCard>> getHairstyleCategories(@Field("gender_id") int sexId);

    @FormUrlEncoded
    @POST("/Barbershop/GetHairstyleDetailing.php")
    Call<ArrayList<HairstyleDetailCard>> getHairstyleDetailing(@Field("category_id") int categoryId);

    @FormUrlEncoded
    @POST("/Barbershop/GetHairdressers.php")
    Call<ArrayList<HairdresserModel>> getHairdressers(@Field("gender_id") int genderId);

    @FormUrlEncoded
    @POST("/Barbershop/GetServiceCategories.php")
    Call<ArrayList<ServiceModel>> getServiceCategories(@Field("gender_id") int genderId);
}
