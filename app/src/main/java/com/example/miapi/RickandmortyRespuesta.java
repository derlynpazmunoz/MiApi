package com.example.miapi;

import java.util.ArrayList;
import java.util.List;

public class RickandmortyRespuesta {
    private List<Rickandmorty> results = new ArrayList<Rickandmorty>();

    public List<Rickandmorty> getResults() {
        return results;
    }

    public void setResults(List<Rickandmorty> results) {
        this.results = results;
    }
}
