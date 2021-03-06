
package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Visit2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Visit2Fragment extends Fragment {
    ImageSlider imageSlider ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Visit2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Visit2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Visit2Fragment newInstance(String param1, String param2) {
        Visit2Fragment fragment = new Visit2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_visit2, container, false);

//       obj.v.findViewByid(R.id.slider);
        imageSlider = v.findViewById(R.id.slide);


        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.moen2, "تغذية الحيوانات من حصاد المزرعة  "));
        slideModels.add(new SlideModel(R.drawable.morn6, "\"تغذية الحيوانات من حصاد المزرعة "));
        slideModels.add(new SlideModel(R.drawable.morn4, "شروق الشمس في المزرعة "));
        slideModels.add(new SlideModel(R.drawable._2_copy, "حقول البرسيم "));


        imageSlider.setImageList(slideModels,true);
        return v;
    }
}

