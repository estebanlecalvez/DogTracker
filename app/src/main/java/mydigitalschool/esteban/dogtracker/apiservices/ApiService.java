package mydigitalschool.esteban.dogtracker.apiservices;



import java.util.List;

import mydigitalschool.esteban.dogtracker.Classes.Dog;
import mydigitalschool.esteban.dogtracker.models.SimpleModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by Esteban on 22/12/2017.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("account/InsertAccount.php")
    Call<SimpleModel> insertAccount(@Field("login") String login,
                                    @Field("password") String password);


    @GET("account/getAccount.php")
    Call<SimpleModel> getAllUsers();

    @GET("dog/GetDog.php")
    Call<List<Dog>> getAllDogs(@Query("idMaster") int idMaster);

    @FormUrlEncoded
    @POST("dog/InsertDog.php")
    Call<SimpleModel> insertDog(@Field("name") String name,
                                @Field("idMaster") String idMaster);

}
