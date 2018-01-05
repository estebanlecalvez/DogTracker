package mydigitalschool.esteban.dogtracker.apiservices;

import java.util.List;

import mydigitalschool.esteban.dogtracker.Classes.Account;
import mydigitalschool.esteban.dogtracker.models.SimpleModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Esteban on 22/12/2017.
 */

public interface ApiGet {

    String BASE_URL = "http://dogtracker.alwaysdata.net/";

    @FormUrlEncoded
    @POST("account/getAccount.php")
    Call<List<Account>> getUsersId(@Field("login") String login);
}
