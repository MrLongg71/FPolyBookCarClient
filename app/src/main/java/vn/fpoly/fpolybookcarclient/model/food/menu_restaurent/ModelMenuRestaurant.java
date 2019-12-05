package vn.fpoly.fpolybookcarclient.model.food.menu_restaurent;


import android.net.Uri;
import android.os.Handler;
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

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.FoodMenu;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;
import vn.fpoly.fpolybookcarclient.presenter.food.menu_restaurant.PresenterMenuRes;

public class ModelMenuRestaurant  {
    public static void dowloadListMenuFoodRes(final String keyRestaurant, final PresenterMenuRes presenterMenuRes){
        final DatabaseReference dataRoot = FirebaseDatabase.getInstance().getReference();
        final ArrayList<FoodMenu> foodMenuArrayList = new ArrayList<>();
        ValueEventListener valueEventListener =  new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataFood = dataSnapshot.child("Food").child(keyRestaurant);
                if(dataFood.exists()){

                    for(DataSnapshot valueListFood : dataFood.getChildren()){
                        final FoodMenu foodMenu = valueListFood.getValue(FoodMenu.class);

                                foodMenuArrayList.add(foodMenu);

                    }
                    presenterMenuRes.resultListMenuFood(foodMenuArrayList,true);



                }else{
                    presenterMenuRes.resultListMenuFood(null,false);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                presenterMenuRes.resultListMenuFood(null,false);
            }
        };
        dataRoot.addValueEventListener(valueEventListener);


    }


}
