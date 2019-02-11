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

import org.pursuit.psychictest.R;
import org.pursuit.psychictest.database.ResultDatabaseHelper;
import org.pursuit.psychictest.model.Result;

import java.text.DecimalFormat;
import java.util.Objects;

public class ResultFragment extends Fragment {

    private static final String KEY_USER_CHOICE = "user choice";
    private static final String KEY_CPU_CHOICE = "computer choice";

    private ResultDatabaseHelper resultDatabaseHelper;

    private TextView percentageCorrectTextView;
    private TextView resultTextView;

    private int userChoice;
    private int cpuChoice;


    public ResultFragment() {
        // Required empty public constructor
    }

    public static ResultFragment newInstance(int userChoice, int computerRandomChoice) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_USER_CHOICE, userChoice);
        args.putInt(KEY_CPU_CHOICE, computerRandomChoice);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //component-related initializations should be done in onCreate
        resultDatabaseHelper = ResultDatabaseHelper.getSingleInstance(Objects.requireNonNull(getContext()).getApplicationContext());

        if (getArguments() != null && getArguments().containsKey(KEY_USER_CHOICE) && getArguments().containsKey(KEY_CPU_CHOICE)) {
            userChoice = getArguments().getInt(KEY_USER_CHOICE);
            cpuChoice = getArguments().getInt(KEY_CPU_CHOICE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        resultTextView = view.findViewById(R.id.result_textView);
        percentageCorrectTextView = view.findViewById(R.id.percentage_correct_textView);

        Result result = new Result();

        String correctChoice = getString(R.string.you_are_correct_textView_string);
        String wrongChoice = getString(R.string.you_are_wrong_textView_string);

        boolean isTrue;

        if (userChoice == cpuChoice) {
            isTrue = true;
            resultTextView.setText(correctChoice);
            result.setIsCorrect(true);
        } else {
            isTrue = false;
            resultTextView.setText(wrongChoice);
            result.setIsCorrect(false);
        }

        resultDatabaseHelper.addResult(result);
        String percentCorrect = String.valueOf(getPercentageResults()) + "% of Choices Correct";
        int correctChoices = resultDatabaseHelper.getTrueResults();
        int totalChoices = resultDatabaseHelper.getTotalChoicesMade();
        percentageCorrectTextView.setText(percentCorrect);

        Log.d("database", "Your Choice: " + userChoice + ", Computer Choice: " + cpuChoice +
                ", isCorrect?: " + isTrue + ", totalChances: " + totalChoices + " totalTrue: " + correctChoices + ", percentCorrect: " + percentCorrect);

    }

    public String getPercentageResults() {
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
