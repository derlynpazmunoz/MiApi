package com.example.miapi.api;

import com.example.miapi.RickandmortyRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RickapiService {

    @GET("rickandmorty")
    Call<RickandmortyRespuesta> obtenerListaRickandmorty();
}
