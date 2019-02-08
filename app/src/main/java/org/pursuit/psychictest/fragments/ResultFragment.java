package org.pursuit.psychictest.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.pursuit.psychictest.R;
import org.pursuit.psychictest.database.ResultDatabaseHelper;
import org.pursuit.psychictest.model.Result;

import java.text.DecimalFormat;

public class ResultFragment extends Fragment {

    private static final String ARG_PARAM1 = "user choice";
    private static final String ARG_PARAM2 = "computer choice";

    private ResultDatabaseHelper resultDatabaseHelper;

    private TextView percentageCorrectTextView;
    private TextView resultTextView;

    private int mParam1;
    private int mParam2;


    public ResultFragment() {
        // Required empty public constructor
    }

    public static ResultFragment newInstance(int userChoice, int computerRandomChoice) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, userChoice);
        args.putInt(ARG_PARAM2, computerRandomChoice);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_result, container, false);
        resultTextView = rootview.findViewById(R.id.result_textView);
        percentageCorrectTextView = rootview.findViewById(R.id.percentage_correct_textView);
        resultDatabaseHelper = ResultDatabaseHelper.getSingleInstance(getContext().getApplicationContext());

        if (getArguments() != null && getArguments().containsKey(ARG_PARAM1) && getArguments().containsKey(ARG_PARAM2)) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
//            resultDatabaseHelper.deleteAllResults();
//            resultDatabaseHelper.close();
        }
        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Result result = new Result();

        String correctChoice = getString(R.string.you_are_correct_textView_string);
        String wrongChoice = getString(R.string.you_are_wrong_textView_string);

        boolean isTrue;

        if (mParam1 == mParam2) {
            isTrue = true;
            resultTextView.setText(correctChoice);
            result.setIsCorrect(true);
        } else {
            isTrue = false;
            resultTextView.setText(wrongChoice);
            result.setIsCorrect(false);
        }

        resultDatabaseHelper.addResult(result);
        String percentCorrect = String.valueOf(percentageResult()) + "% of Choices Correct";
        int correctChoices = resultDatabaseHelper.getTrueResults();
        int totalChoices = resultDatabaseHelper.getTotalChoicesMade();
        percentageCorrectTextView.setText(percentCorrect);

        Log.d("database", "Your Choice: " + mParam1 + ", Computer Choice: " + mParam2 +
                ", isCorrect?: " + isTrue + ", totalChances: " + totalChoices + " totalTrue: " + correctChoices + ", percentCorrect: " + percentCorrect);

        //Toast.makeText(getContext(), resultTextView.getText(), Toast.LENGTH_SHORT).show();
    }

    public String percentageResult() {
        int totalChoices = resultDatabaseHelper.getTotalChoicesMade();
        int correctChoices = resultDatabaseHelper.getTrueResults();

        if (totalChoices != 0) {
            DecimalFormat df = new DecimalFormat();
            long percentResult = Math.round(100 / totalChoices) * correctChoices;
            return df.format(percentResult);
        }
        return "0";
    }
}
