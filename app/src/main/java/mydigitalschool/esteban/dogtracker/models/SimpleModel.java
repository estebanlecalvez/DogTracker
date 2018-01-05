package mydigitalschool.esteban.dogtracker.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import mydigitalschool.esteban.dogtracker.Classes.Account;
import mydigitalschool.esteban.dogtracker.Classes.Dog;

/**
 * Created by Esteban on 20/12/2017.
 */

public class SimpleModel {
    @SerializedName("Account")
    private List<Account> listAccount;
    @SerializedName("Dog")
    private List<Dog> listDog;
    @SerializedName("success")
    private int status;
    @SerializedName("message")
    private String message;

    public SimpleModel(List<Dog> listAccount,int status, String message) {
        this.listDog = listDog;
        this.status = status;
        this.message = message;
    }


    public SimpleModel() {
    }

    public List<Account> getListAccount(){
        return listAccount;
    }

    public void setListAccount(List<Account> listAccount){
        this.listAccount=listAccount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
