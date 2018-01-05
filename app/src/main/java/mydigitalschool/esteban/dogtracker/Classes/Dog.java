package mydigitalschool.esteban.dogtracker.Classes;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


/**
 * Created by Esteban on 20/12/2017.
 */
public class Dog {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("idMaster")
    @Expose
    private String idMaster;


    public Dog(String name, String idMaster) {
        this.id = id;
        this.name = name;
        this.idMaster = idMaster;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(String idMaster) {
        this.idMaster = idMaster;
    }

}