package com.example.tourguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class MainActivity extends AppCompatActivity implements OnFragmentChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.nav);
        navigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_SELECTED);
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnNavigationItemSelectedListener(navigationListener);
        navigationView.setSelectedItemId(0);
        changeFragment(new HomeFragment(), false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.nav_home:
                    changeFragment(new HomeFragment(), true);
                    return true;

                case R.id.nav_contact:
                    changeFragment(new ContactFragment(), true);
                    return true;

                case R.id.nav_about:
                    changeFragment(new AboutFragment(), true);
                    return true;
            }

            return false;
        }
    };

    public void changeFragment(Fragment fragment, boolean backTrack) {

         FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.container, fragment);
         if (backTrack)
             transaction.addToBackStack(null);

           transaction.commit();
    }

    public void startDetails(String name) {

        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }

    @Override
    public void onFragmentChange(Fragment fragment) {

    }
}