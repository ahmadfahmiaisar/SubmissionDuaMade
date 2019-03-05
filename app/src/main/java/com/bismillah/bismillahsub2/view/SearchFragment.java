package com.bismillah.bismillahsub2.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.bismillah.bismillahsub2.R;
import com.bismillah.bismillahsub2.adapter.ItemAdapter;
import com.bismillah.bismillahsub2.api.ApiService;
import com.bismillah.bismillahsub2.api.ClientMovie;
import com.bismillah.bismillahsub2.model.ItemResponseMovie;
import com.bismillah.bismillahsub2.model.ModelResponseMovie;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {
    private ArrayList<ItemResponseMovie>movieArrayList;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.rv_list_movie);
        searchView = view.findViewById(R.id.searchMovie);

        movieArrayList = new ArrayList<>();
        Locale locale = getResources().getConfiguration().locale;
        if (locale.toString().equalsIgnoreCase("en_US")){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    ApiService apiService = ClientMovie.getServiceRetrofit();
                    apiService.getSearchMovie(query).enqueue(new Callback<ModelResponseMovie>() {
                        @Override
                        public void onResponse(Call<ModelResponseMovie> call, Response<ModelResponseMovie> response) {
                            if (response.isSuccessful()){
                                movieArrayList = response.body().getResults();
                                itemAdapter = new ItemAdapter(getActivity(), movieArrayList);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                recyclerView.setAdapter(itemAdapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelResponseMovie> call, Throwable t) {
                            Toast.makeText(getActivity(), "gagal search", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        } else {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    ApiService apiService = ClientMovie.getServiceRetrofit();
                    apiService.getSearchMovie(query).enqueue(new Callback<ModelResponseMovie>() {
                        @Override
                        public void onResponse(Call<ModelResponseMovie> call, Response<ModelResponseMovie> response) {
                            if (response.isSuccessful()){
                                movieArrayList = response.body().getResults();
                                itemAdapter = new ItemAdapter(getActivity(), movieArrayList);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                recyclerView.setAdapter(itemAdapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelResponseMovie> call, Throwable t) {
                            Toast.makeText(getActivity(), "gagal search", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return view;
    }

}
