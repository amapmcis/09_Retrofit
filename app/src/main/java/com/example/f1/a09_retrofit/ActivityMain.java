package com.example.f1.a09_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.f1.a09_retrofit.model.Repo;
import com.example.f1.a09_retrofit.rest.RetrofitApiFactory;
import com.example.f1.a09_retrofit.rest.RetrofitApiInterface;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMain extends AppCompatActivity {

    private Realm realm;
    private RetrofitApiInterface api;

    private RecyclerView recyclerView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a Realm instance for this thread
        realm = Realm.getDefaultInstance();

        // Setup the RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true); // this setting should improve performance
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivityMain.this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get list of all students
        RealmResults<Repo> listOfRepos = realm.where(Repo.class).findAll();

        // Specify the Adapter fot the RecyclerView
        adapter = new MyAdapter(listOfRepos);
        recyclerView.setAdapter(adapter);

        // Init retrofit API:
        api = RetrofitApiFactory.getRetrofitApiInterface();
        Call<List<Repo>> call = api.listRepos("amapmcis");  // call for amapcis's repos

        // Executing the call:
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.body() != null) {  // check if something is back
                    final List<Repo> listOfRepos = response.body();  // get list of repos
                    if (!realm.isClosed()) {    // check if realm is not closed
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realm.copyToRealmOrUpdate(listOfRepos); // update database
                                adapter.notifyDataSetChanged();         // list on screen notify
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Toast.makeText(ActivityMain.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
