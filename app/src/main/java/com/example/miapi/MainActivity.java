package com.example.miapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.miapi.api.RickapiService;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ListaRickandmortyAdapter listaRickandmortyAdapter;
    Retrofit retrofit;

    private static final String TAG ="RickAndMorty";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.card_recycler_view);
        listaRickandmortyAdapter = new ListaRickandmortyAdapter(this);
        recyclerView.setAdapter(listaRickandmortyAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,true);
        recyclerView.setLayoutManager(linearLayoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



    }

    private void obtenerDatos2(){
        RickapiService service = retrofit.create(RickapiService.class);
        Call<RickandmortyRespuesta> rickandmortyRespuestaCall = service.obtenerListaRickandmorty();

        rickandmortyRespuestaCall.enqueue(new Callback<RickandmortyRespuesta>(){
            @Override
            public void onResponse(Call<RickandmortyRespuesta> call, Response<RickandmortyRespuesta> response){

                if (response.isSuccessful()) {
                    RickandmortyRespuesta rickandmortyRespuesta = response.body();
                    List<Rickandmorty> listaRickandmorty = rickandmortyRespuesta.getResults();
                    for (int i = 0; i < listaRickandmorty.size(); i++) {
                        Rickandmorty r = listaRickandmorty.get(i);
                        Log.e(TAG, "rickandmorty:" + r.getName());
                    }

                    listaRickandmortyAdapter.add((ArrayList<Rickandmorty>) listaRickandmorty);
                }else{
                    Log.e(TAG, "onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RickandmortyRespuesta> call, Throwable t) {

                 Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });


    }

    private void obtenerDatos(){
        RickapiService service = retrofit.create(RickapiService.class);
        Call<RickandmortyRespuesta> rickandmortyRespuestaCall = service.obtenerListaRickandmorty();
        rickandmortyRespuestaCall.enqueue(new Callback<RickandmortyRespuesta>(){

            @Override
            public void onResponse(Call<RickandmortyRespuesta> call, Response<RickandmortyRespuesta> response) {
                if (response.isSuccessful()) {
                    RickandmortyRespuesta rickandmortyRespuesta = response.body();
                    List<Rickandmorty> listaRickandmorty = rickandmortyRespuesta.getResults();
                    for (int i = 0; i < listaRickandmorty.size(); i++) {
                        Rickandmorty r = listaRickandmorty.get(i);
                        Log.e(TAG, "rickandmorty: " + r.getName());
                    }

                    listaRickandmortyAdapter.add((ArrayList<Rickandmorty>) listaRickandmorty);
                }
                else{
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<RickandmortyRespuesta> call, Throwable t) {

                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
