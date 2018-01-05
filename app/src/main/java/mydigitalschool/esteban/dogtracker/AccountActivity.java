package mydigitalschool.esteban.dogtracker;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mydigitalschool.esteban.dogtracker.Classes.Dog;
import mydigitalschool.esteban.dogtracker.apiservices.ApiService;
import mydigitalschool.esteban.dogtracker.clients.ApiClient;
import mydigitalschool.esteban.dogtracker.models.SimpleModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Esteban on 16/12/2017.
 */

public class AccountActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    private TextView userName;
    private TextView userNumber;
    private Spinner userDogs;
    private Button btnSelectDog;
    private Button btnAddDog;
    private EditText etNameDog;
    public static String NOMCHIEN = "Nom chien";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Intent intent = getIntent();
        String email = intent.getStringExtra(LoginActivity.EMAIL);
        String password = intent.getStringExtra(LoginActivity.PASSWORD);

        progressDialog = new ProgressDialog(this);

        userName = findViewById(R.id.userName);
        userNumber = findViewById(R.id.userNumber);
        userDogs= (Spinner)findViewById(R.id.userDogsSpinner);
        userName.setText(email);
        userNumber.setText(password);
        btnSelectDog = (Button) findViewById(R.id.btnSelectDog);
        btnAddDog = (Button) findViewById(R.id.btnAddDog);
        etNameDog = (EditText) findViewById(R.id.etNameDog);
        btnSelectDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity();
            }
        });
        btnAddDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDog();
            }
        });


        //Create an ArrayAdapter which use the Dogs_array and Adapt it on the spinner item.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.dogs_array,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        userDogs.setAdapter(adapter);
    }


    private void addDog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_add_dog,null);
        builder.setView(view);

        final EditText etName = (EditText) view.findViewById(R.id.edit_text_name);
        final EditText etIdMaster = (EditText) view.findViewById(R.id.edit_text_idMaster);

        progressDialog.setTitle("Add dog");
        progressDialog.setMessage("Please wait ....");
        progressDialog.show();

        builder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                String name = etName.getText().toString();
                String idMaster = etIdMaster.getText().toString();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(AccountActivity.this, "Name", Toast.LENGTH_SHORT).show();
                }else{
                    insertDog(name,idMaster);
                    //getUserId(login);

                }
            }
        });
        builder.show();
    }

    private void insertDog(String name, String idMaster){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<SimpleModel> call = apiService.insertDog(name,idMaster);
        call.enqueue(new Callback<SimpleModel>() {
            @Override
            public void onResponse(Call<SimpleModel> call, Response<SimpleModel> response) {

                SimpleModel simpleModel = response.body();

                //check the status code
                if(simpleModel.getStatus()==1){
                    Toast.makeText(AccountActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else{
                    Toast.makeText(AccountActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SimpleModel> call, Throwable t) {
                Toast.makeText(AccountActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void getDogsFromOneUser(){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<List<Dog>> call = apiService.getAllDogs(52);

        call.enqueue(new Callback<List<Dog>>() {
            @Override
            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                List<Dog> dogList = response.body();

                //Creating an String array for the ListView
              //  String[] dogs = new String[dogList.size()];

                //looping through all the dogs and inserting the names inside the string array
                for (Dog d : dogList) {
                    //dogs[i] = dogList.get(i).getId()+"";
                    //displaying the string array into listview
                    Log.d("name", d.getName());

                }
            }

            @Override
            public void onFailure(Call<List<Dog>> call, Throwable t) {
            }
        });
    }
    private void changeActivity(){
        String nomChien = userDogs.getSelectedItem().toString();
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra(NOMCHIEN, nomChien);
        startActivity(intent);
    }
}