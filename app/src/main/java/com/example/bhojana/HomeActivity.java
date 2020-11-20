package com.example.bhojana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    EditText search;
    ImageButton navigation,searchbtn,profilebtn;
    RecyclerView dealsrecycleview, restaurants;
    ResAdapter resAdapter;
    List<Restaurant> restaurantlist;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        search = findViewById(R.id.searchhome);
        navigation = findViewById(R.id.nav);
        searchbtn = findViewById(R.id.searchbtn);
        profilebtn = findViewById(R.id.profilebtn);
        dealsrecycleview = findViewById(R.id.dealsrecycleview);
        restaurants = findViewById(R.id.restaurantlist);



        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchRestaurant(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }

    private void searchRestaurant(String name){
        Query query = FirebaseDatabase.getInstance().getReference("Restaurant_List").orderByChild("Restaurant_name").startAt(name).endAt(name+"\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                restaurantlist.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                    restaurantlist.add(restaurant);
                }
                resAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}