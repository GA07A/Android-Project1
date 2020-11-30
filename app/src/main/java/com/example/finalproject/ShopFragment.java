package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.finalproject.R;
import com.example.finalproject.item;
import com.example.finalproject.receyleviewadapter;

import java.util.ArrayList;


public class ShopFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShopFragment() {
        // Required empty public constructor
    }



    RecyclerView recyclerView;
    EditText edit_search;
    receyleviewadapter recyclerviewadapter;
    private ArrayList<item> show;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_shop, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        edit_search = (EditText) view.findViewById(R.id.editsearch);
        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inflater(editable.toString());
            }
        });


        show = new ArrayList<>();
        show.add(new item(" خبز", "خبز الشوفان مع بذور السمسم", R.drawable.pr));
        show.add(new item("فطائر", "كاروسان وفطائر البيتزا", R.drawable.ca));
        show.add(new item("خبز عربي", "خبز 'مفرود'", R.drawable.pr2));
        show.add(new item(" خبز ", "خبز بماء الفاكهة المخمر", R.drawable.br2));
        show.add(new item("المحاصيل ", "بعض المحاصيل الشهية", R.drawable.v));
        show.add(new item(" المحاصيل ", "بعض المحاصيل الشهية", R.drawable.f));
        show.add(new item("التمور", " صفاوي، خلاص، نانة، نبتة علي", R.drawable.da));
        show.add(new item("التمور", " سكري، عنبر،عجوة", R.drawable.d));


        recyclerviewadapter = new receyleviewadapter(show);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerviewadapter);
        recyclerviewadapter.notifyDataSetChanged();
        return view;

    }

    private void inflater(String query) {
        ArrayList<item> filtered = new ArrayList<>();
        for (item i : show) {

            if (i.getTital().toLowerCase().contains( query.toLowerCase() )) {
                filtered.add( i );
            }

        }
        recyclerviewadapter.filterlist( filtered );



    }


}