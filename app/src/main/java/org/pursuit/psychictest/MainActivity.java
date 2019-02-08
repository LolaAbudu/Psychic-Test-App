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

    @Override
    public void showNextFragment(String text) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, NextFragment.newInstance(text))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showResultFragment(int param1, int param2) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, ResultFragment.newInstance(param1, param2))
                .addToBackStack(null)
                .commit();
    }
}
