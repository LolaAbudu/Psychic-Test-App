package org.pursuit.psychictest.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.pursuit.psychictest.FragmentInterface;
import org.pursuit.psychictest.R;

import java.util.HashMap;
import java.util.Random;

public class NextFragment extends Fragment {

    private static final String FRAGMENT_STRING_KEY = "String_key";

    private FragmentInterface fragmentInterface;

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;

    private String mParam1;

    private int[] flowersList = {R.drawable.red_rose, R.drawable.yellow_rose, R.drawable.white_flower, R.drawable.bouquet_of_roses};
    private int[] chocolateList = {R.drawable.dark_chocolate, R.drawable.milk_chocolate, R.drawable.white_chocolate, R.drawable.milk_white_dark_chocolate};
    private int[] diamondList = {R.drawable.dark_diamond, R.drawable.clear_diamond, R.drawable.pink_diamonds, R.drawable.yellow_diamond};

    public NextFragment() {
        // Required empty public constructor
    }

    public static NextFragment newInstance(String themeString) {
        NextFragment fragment = new NextFragment();
        Bundle args = new Bundle();
        args.putString(FRAGMENT_STRING_KEY, themeString);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInterface) {
            fragmentInterface = (FragmentInterface) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(FRAGMENT_STRING_KEY)) {
            mParam1 = getArguments().getString(FRAGMENT_STRING_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_next, container, false);
        image1 = rootview.findViewById(R.id.image_view_image1);
        image2 = rootview.findViewById(R.id.image_view_image2);
        image3 = rootview.findViewById(R.id.image_view_image3);
        image4 = rootview.findViewById(R.id.image_view_image4);

        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(getContext(), mParam1, Toast.LENGTH_SHORT).show();
        Log.d("theme", mParam1);

        if (mParam1.equals("Flowers")) {
            image1.setImageResource(flowersList[0]);
            image2.setImageResource(flowersList[1]);
            image3.setImageResource(flowersList[2]);
            image4.setImageResource(flowersList[3]);
        }
        if (mParam1.equals("Chocolate")) {
            image1.setImageResource(chocolateList[0]);
            image2.setImageResource(chocolateList[1]);
            image3.setImageResource(chocolateList[2]);
            image4.setImageResource(chocolateList[3]);
        }
        if (mParam1.equals("Diamonds")) {
            image1.setImageResource(diamondList[0]);
            image2.setImageResource(diamondList[1]);
            image3.setImageResource(diamondList[2]);
            image4.setImageResource(diamondList[3]);
        }

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int imageSelected = 0;
                fragmentInterface.showResultFragment(imageSelected, randomImageSelection());
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int imageSelected = 1;
                fragmentInterface.showResultFragment(imageSelected, randomImageSelection());
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int imageSelected = 2;
                fragmentInterface.showResultFragment(imageSelected, randomImageSelection());
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int imageSelected = 3;
                fragmentInterface.showResultFragment(imageSelected, randomImageSelection());
            }
        });
    }

    //HashMap<Integer, Integer> allImages = new HashMap<>();
    public int randomImageSelection() {

        Random rand = new Random();
        int randomComputerChoice = rand.nextInt(4);
        return randomComputerChoice;
    }
}
