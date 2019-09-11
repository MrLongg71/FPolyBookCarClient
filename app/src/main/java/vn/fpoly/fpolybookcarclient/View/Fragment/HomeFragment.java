package vn.fpoly.fpolybookcarclient.View.Fragment;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.Adapter.Home.CakeBannerAdapter;
import vn.fpoly.fpolybookcarclient.Adapter.Home.ChallengeBannerAdapter;
import vn.fpoly.fpolybookcarclient.Adapter.Home.PlaceBannerAdapter;
import vn.fpoly.fpolybookcarclient.Model.ObjectClass.CakesBanner;
import vn.fpoly.fpolybookcarclient.Model.ObjectClass.ChallengeBanner;
import vn.fpoly.fpolybookcarclient.Model.ObjectClass.PlaceBanner;
import vn.fpoly.fpolybookcarclient.R;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView1, recyclerView2,recyclerView3;
    private  ArrayList<ChallengeBanner> arrayChanllenge = new ArrayList<>();
    private  ArrayList<CakesBanner> arrCake = new ArrayList<>();
    private ArrayList<PlaceBanner>arrPlace = new ArrayList<>();
    private CakeBannerAdapter cakeBannerAdapter;
    private ChallengeBannerAdapter bannerAdapter;
    private PlaceBannerAdapter placeBannerAdapter;
    private ImageView imgIconCar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);


        recyclerView1.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);
        recyclerView3.setHasFixedSize(true);

        itemChallengeBanner();
        itemCakeBanner();
        itemPlacebanner();
        imgIconCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "CAR ", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void dataChallengeBanner() {
        arrayChanllenge.add(new ChallengeBanner(R.drawable.banner1));
        arrayChanllenge.add(new ChallengeBanner(R.drawable.banner2));
        arrayChanllenge.add(new ChallengeBanner(R.drawable.banner3));
        arrayChanllenge.add(new ChallengeBanner(R.drawable.banner4));
        arrayChanllenge.add(new ChallengeBanner(R.drawable.banner5));

    }

    private void dataCakeBanner(){
        arrCake.add(new CakesBanner(getString(R.string.caketitle1),getString(R.string.cakdetaile1),R.drawable.cake1));
        arrCake.add(new CakesBanner(getString(R.string.caketitle2),getString(R.string.cakedetail2),R.drawable.cake2));
        arrCake.add(new CakesBanner(getString(R.string.caketitle3),getString(R.string.cakedetail3),R.drawable.cake3));
        arrCake.add(new CakesBanner(getString(R.string.caketitle4),getString(R.string.cakedetail4),R.drawable.cake5));
        arrCake.add(new CakesBanner(getString(R.string.caketitle5),getString(R.string.cakedetail5),R.drawable.cak4));

    }

    private void dataPlaceBanner(){
        arrPlace.add(new PlaceBanner(getString(R.string.placetitle1),getString(R.string.placedetail1),R.drawable.place1));
        arrPlace.add(new PlaceBanner(getString(R.string.placetitle2),getString(R.string.placedetail2),R.drawable.place3));
        arrPlace.add(new PlaceBanner(getString(R.string.placetitle3),getString(R.string.placedetail3),R.drawable.place5));
        arrPlace.add(new PlaceBanner(getString(R.string.placetitle4),getString(R.string.placedetail4),R.drawable.place2));
        arrPlace.add(new PlaceBanner(getString(R.string.placetitle5),getString(R.string.placedetail6),R.drawable.place4));

    }

    private void initView(View view) {
        recyclerView1 = view.findViewById(R.id.reycelviewItem1);
        recyclerView2 = view.findViewById(R.id.reycelviewItem2);
        recyclerView3 = view.findViewById(R.id.reycelviewItem3);
        imgIconCar    = view.findViewById(R.id.imgIconCarHome);
    }

    private void itemChallengeBanner() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager);
        dataChallengeBanner();
        bannerAdapter = new ChallengeBannerAdapter(arrayChanllenge, getActivity(), R.layout.row_challenge_banner);
        recyclerView1.setAdapter(bannerAdapter);
    }

    private void itemCakeBanner() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager);
        dataCakeBanner();
        cakeBannerAdapter = new CakeBannerAdapter(getActivity(), R.layout.row_cake_banner, arrCake);
        recyclerView2.setAdapter(cakeBannerAdapter);
    }

    private void itemPlacebanner(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layoutManager);
        dataPlaceBanner();
        placeBannerAdapter = new PlaceBannerAdapter(getActivity(),R.layout.row_place_banner,arrPlace);
        recyclerView3.setAdapter(placeBannerAdapter);
    }

}
