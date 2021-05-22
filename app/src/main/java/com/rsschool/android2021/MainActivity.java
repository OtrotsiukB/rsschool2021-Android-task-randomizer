package com.rsschool.android2021;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements i_openFragment {

    public int numerForBack = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();
        // TODO: invoke function which apply changes of the transaction
    }

    private void openSecondFragment(int min, int max) {
        final Fragment secondFragment = SecondFragment.newInstance(min,max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void openFirstFragmentViaInteface(int previousNumber) {
        openFirstFragment(previousNumber);
    }

    @Override
    public void openSecondFragmentViaInteface(int min, int max) {
        openSecondFragment(min,max);
    }

    @Override
    public void showToast(@NotNull String text) {
        Toast toast = Toast.makeText(getApplicationContext(),
                text, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void setnumerForBack(int number) {
        numerForBack=number;
    }

    @Override
    public int getnumerForBack() {
        return numerForBack;
    }
}
