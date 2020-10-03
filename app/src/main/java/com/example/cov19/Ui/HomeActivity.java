package com.example.cov19.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.cov19.Fragments.UserFragment;
import com.example.cov19.Fragments.MapsFragment;
import com.example.cov19.Fragments.NewsFragment;
import com.example.cov19.Fragments.StatsFragment;
import com.example.cov19.Fragments.SymptomsFragment;
import com.example.cov19.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {
    /**.
    * Bottom Navigation View
     */
   private BottomNavigationView bottomNavigationView;
    /**.
     * Database Reference FireBase
     */
   private DatabaseReference reference;
    /**.
     * Firebase User
     */
   private  FirebaseUser firebaseUser;

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(firebaseUser.getUid());

        bottomNavigationView =  findViewById(R.id.bottom_navigation);
        bottomNavigationView
                .setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new StatsFragment())
                .commit();

    }
    /**
     *
     */
   private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(
                        @NonNull final MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.nav_stats:
                            selectedFragment = new StatsFragment();
                            break;
                        case R.id.nav_news:
                            selectedFragment = new NewsFragment();
                            break;
                        case R.id.nav_Map:
                            selectedFragment = new MapsFragment();
                            break;
                        case R.id.nav_symptoms:
                            selectedFragment = new SymptomsFragment();
                            break;
                        case R.id.nav_chat:
                            selectedFragment = new UserFragment();
                            break;
                        default:
                            selectedFragment = new StatsFragment();
                    }

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                    return true;
                }
            };

    private  void status(final String status) {
        reference = FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(firebaseUser.getUid());
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("status", status);
        reference.updateChildren(hashMap);
    }

    @Override
    protected final void onResume() {
        super.onResume();
        status("online");
    }
    @Override
    protected final void onPause() {
        super.onPause();
        status("offline");
    }
}