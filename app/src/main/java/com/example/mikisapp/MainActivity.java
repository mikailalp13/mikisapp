package com.example.mikisapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mikisapp.fragments.KonusmaFragment;
import com.example.mikisapp.fragments.KullaniciFragment;
import com.example.mikisapp.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView profile_image;
    BottomNavigationView bnb;
    TextView kullaniciadi;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    private MenuItem item;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profile_image = findViewById(R.id.profile_image);
        bnb = findViewById(R.id.bnb);

        kullaniciadi = findViewById(R.id.kullaniciadi);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                kullaniciadi.setText(user.getUsername());
                if (user != null && "default".equals(user.getImageURL())) {
                    profile_image.setImageResource(R.drawable.user);
                } else {
                    if (user != null && user.getImageURL() != null) {
                        Glide.with(MainActivity.this).load(user.getImageURL()).into(profile_image);
                    } else {

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Veri çekme hatası: " + error.getMessage());
            }
        });


        bnb.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.logout){
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(MainActivity.this, Baslangic.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else if(id == R.id.chats) {
                    Fragment fragment2 = new KonusmaFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fla,fragment2).commit();
                }else {
                    Fragment fragment3 = new KullaniciFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fla,fragment3).commit();
                }
                return true;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}