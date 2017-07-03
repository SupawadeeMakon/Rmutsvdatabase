package rmutsv.makon.ppp.rmutsvdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rmutsv.makon.ppp.rmutsvdatabase.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add Fragment
        if (savedInstanceState == null) {
            MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.relContent, mainFragment)
                    .commit();
        }


    }//main method

}//main class
