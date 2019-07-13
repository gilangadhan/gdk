package com.dicoding.myhero.hero;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicoding.myhero.R;
import com.dicoding.myhero.model.Hero;
import com.dicoding.myhero.model.HeroesData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeroFragment extends Fragment {
    private ArrayList<Hero> list = new ArrayList<>();
    private RecyclerView rvHeroes;
    public HeroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hero, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // ngenalin view
        rvHeroes = view.findViewById(R.id.rv_heroes);
        rvHeroes.setHasFixedSize(true);

        list.addAll(HeroesData.getListData());
        showRecyclerCardView();
    }

    private void showRecyclerCardView() {
        rvHeroes.setLayoutManager(new LinearLayoutManager(getActivity()));
        HeroAdapter cardViewHeroAdapter = new HeroAdapter(list);
        rvHeroes.setAdapter(cardViewHeroAdapter);
    }
}
