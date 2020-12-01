package net.gfg.retrofitexample;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    //injecting retrofit
    @Inject Retrofit retrofit;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //injecting here
        ((MyApplication) getApplication()).getNetComponent().inject(this);


        listView = (ListView) findViewById(R.id.listViewCountries);

        getCountries();
    }

    private void getCountries() {
        Api api = retrofit.create(Api.class);
        Call<List<Country>> call = api.getCountries();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                List<Country> countryList = response.body();

                //Creating an String array for the ListView
                String[] countries = new String[countryList.size()];

                //looping through all the countries and inserting the names inside the string array
                for (int i = 0; i < countryList.size(); i++) {
                    countries[i] = countryList.get(i).getName();
                }

                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, countries));
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
