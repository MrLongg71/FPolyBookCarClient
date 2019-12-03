package vn.fpoly.fpolybookcarclient.model.food.restaurant;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.BreakFast_MenuFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodMenu;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;
import vn.fpoly.fpolybookcarclient.presenter.food.restaurant.PresenterRestaurant;

public class ModelRestaurant {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private ArrayList<Restaurant>arrRestaurant = new ArrayList<>();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private ArrayList<FoodMenu> arrFoodMenu = new ArrayList<>();

    public void dowloadListRestaurant(final PresenterRestaurant presenterRestaurant){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataNews = dataSnapshot.child("Restaurant");
                for (DataSnapshot valueNews : dataNews.getChildren()){
                    final Restaurant restaurant = valueNews.getValue(Restaurant.class);
                    restaurant.setKey(valueNews.getKey());
                    storageReference.child("ImageRestaurant").child(restaurant.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String URL = uri.toString();
                            restaurant.setImage(URL);
                        }
                    });
                    arrRestaurant.add(restaurant);
                }
                presenterRestaurant.resultGetListRestaurant(arrRestaurant);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }

    public void dowloadListFood(final ArrayList<String>arrKeyRestaurant, final PresenterRestaurant presenterRestaurant){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i <arrKeyRestaurant.size(); i ++ ){
                    DataSnapshot dataFood = dataSnapshot.child("Food").child(arrKeyRestaurant.get(i));
                    for (DataSnapshot valueFood : dataFood.getChildren() ){
                        final FoodMenu foodMenu = valueFood.getValue(FoodMenu.class);

                        storageReference.child("ImageRestaurant").child(foodMenu.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                    foodMenu.setImage(uri.toString());
                            }
                        });
                        arrFoodMenu.add(foodMenu);
                    }

                }
                presenterRestaurant.resultGetListFood(arrFoodMenu);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        databaseReference.addValueEventListener(valueEventListener);
    }



}
