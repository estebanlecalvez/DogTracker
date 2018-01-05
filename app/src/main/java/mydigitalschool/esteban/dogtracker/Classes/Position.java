package mydigitalschool.esteban.dogtracker.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Esteban on 20/12/2017.
 */
public class Position {

    @SerializedName("idDog")
    @Expose
    private Integer idDog;
    @SerializedName("latDog")
    @Expose
    private Double latDog;
    @SerializedName("lngDog")
    @Expose
    private Double lngDog;

    public Position(Integer idDog, Double latDog, Double lngDog) {
        this.idDog = idDog;
        this.latDog = latDog;
        this.lngDog = lngDog;
    }

    public Integer getIdDog() {
        return idDog;
    }

    public void setIdDog(Integer idDog) {
        this.idDog = idDog;
    }

    public Double getLatDog() {
        return latDog;
    }

    public void setLatDog(Double latDog) {
        this.latDog = latDog;
    }

    public Double getLngDog() {
        return lngDog;
    }

    public void setLngDog(Double lngDog) {
        this.lngDog = lngDog;
    }

}