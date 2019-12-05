package vn.fpoly.fpolybookcarclient.model.home.newsbanner;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.News;
import vn.fpoly.fpolybookcarclient.presenter.home.newsbanner.PresenterNewsBanner;

public class ModelNewsBanner {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private ArrayList<News> newsCakeBannerList = new ArrayList<>();
    private ArrayList<News> newsPlaceBannerList = new ArrayList<>();
    private ArrayList<News> newsFoodBannerList = new ArrayList<>();

    public void dowloaLisCake(final PresenterNewsBanner presenterNewsBanner) {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataNewsCake = dataSnapshot.child("NewsCake");
                DataSnapshot dataNewsPlace = dataSnapshot.child("NewsPlace");


                for (DataSnapshot valueNewsCake : dataNewsCake.getChildren()) {
                    final News newsCakeBanner = valueNewsCake.getValue(News.class);
                    newsCakeBannerList.add(newsCakeBanner);
                }

                for (DataSnapshot valuePlace : dataNewsPlace.getChildren()) {
                    News newsPlaceBanner = valuePlace.getValue(News.class);
                    newsPlaceBannerList.add(newsPlaceBanner);

                }


                presenterNewsBanner.resultGetListCake(newsCakeBannerList, newsPlaceBannerList, newsFoodBannerList);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(eventListener);

    }


}
