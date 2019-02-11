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
import android.widget.Toast;

import org.pursuit.psychictest.FragmentInterface;
import org.pursuit.psychictest.R;

import java.util.Random;

public class NextFragment extends Fragment implements View.OnClickListener {

    private static final String KEY_THEME_STRING = "key_theme";

    private FragmentInterface fragmentInterface;

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private int cpuChoice;

    private String theme;

    //by putting the category of the drawable as the first word in its id it makes it easier to reference should you forget what the exact name is. This also keeps your drawables nicely organized cause all related drawables would be right next to each other and ordered alphabetically!
    private int[] flowersList = {R.drawable.flower_red_rose, R.drawable.flower_yellow_rose, R.drawable.flower_white, R.drawable.flower_bouquet_of_roses};
    private int[] chocolateList = {R.drawable.chocolate_dark, R.drawable.chocolate_milk, R.drawable.chocolate_white, R.drawable.chocolate_milk_white_dark};
    private int[] diamondList = {R.drawable.diamond_dark, R.drawable.diamond_clear, R.drawable.diamond_pink, R.drawable.diamond_yellow};

    public NextFragment() {
        // Required empty public constructor
    }

    public static NextFragment newInstance(String themeString) {
        NextFragment fragment = new NextFragment();
        Bundle args = new Bundle();
        args.putString(KEY_THEME_STRING, themeString);
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
        if (getArguments() != null && getArguments().containsKey(KEY_THEME_STRING)) {
            theme = getArguments().getString(KEY_THEME_STRING);
        }
        cpuChoice = new Random().nextInt(4);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_next, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(getContext(), theme, Toast.LENGTH_SHORT).show();
        Log.d("theme", theme);

        //find the views after they've been created
        image1 = view.findViewById(R.id.image_view_image1);
        image2 = view.findViewById(R.id.image_view_image2);
        image3 = view.findViewById(R.id.image_view_image3);
        image4 = view.findViewById(R.id.image_view_image4);

        if (theme.equals("Flowers")) {
            image1.setImageResource(flowersList[0]);
            image2.setImageResource(flowersList[1]);
            image3.setImageResource(flowersList[2]);
            image4.setImageResource(flowersList[3]);
        }
        if (theme.equals("Chocolate")) {
            image1.setImageResource(chocolateList[0]);
            image2.setImageResource(chocolateList[1]);
            image3.setImageResource(chocolateList[2]);
            image4.setImageResource(chocolateList[3]);
        }
        if (theme.equals("Diamonds")) {
            image1.setImageResource(diamondList[0]);
            image2.setImageResource(diamondList[1]);
            image3.setImageResource(diamondList[2]);
            image4.setImageResource(diamondList[3]);
        }

        //because all these image views require the same logic when they're clicked we can just move the logic to a switch case and eliminate creating a listener for each one. A click listener is an anonymous class that gets created witch each click. By implementing the interface we don't have to create these objects, saving memory and improving performance cause a quick comparison check is faster than an object creation.
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_view_image1:
                fragmentInterface.showResultFragment(0, cpuChoice);
                break;
            case R.id.image_view_image2:
                fragmentInterface.showResultFragment(1, cpuChoice);
                break;
            case R.id.image_view_image3:
                fragmentInterface.showResultFragment(2, cpuChoice);
                break;
            case R.id.image_view_image4:
                fragmentInterface.showResultFragment(3, cpuChoice);
                break;
        }
    }
}
