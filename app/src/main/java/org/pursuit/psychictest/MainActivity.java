package org.pursuit.psychictest;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.pursuit.psychictest.database.ResultDatabaseHelper;
import org.pursuit.psychictest.fragments.MainFragment;
import org.pursuit.psychictest.fragments.NextFragment;
import org.pursuit.psychictest.fragments.ResultFragment;

public class MainActivity extends AppCompatActivity implements FragmentInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = MainFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, mainFragment);
        fragmentTransaction.commit();
    }

    //be specific with parameter names
    @Override
    public void showNextFragment(String theme) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, NextFragment.newInstance(theme))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showResultFragment(int userChoice, int cpuChoice) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, ResultFragment.newInstance(userChoice, cpuChoice))
                .addToBackStack(null)
                .commit();
    }
}
