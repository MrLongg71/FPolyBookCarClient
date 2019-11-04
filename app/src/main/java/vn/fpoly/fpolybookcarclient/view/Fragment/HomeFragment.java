package vn.fpoly.fpolybookcarclient.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.adapter.home.CakeBannerAdapter;
import vn.fpoly.fpolybookcarclient.adapter.home.ChallengeBannerAdapter;
import vn.fpoly.fpolybookcarclient.adapter.home.FoodBannerAdapter;
import vn.fpoly.fpolybookcarclient.adapter.home.HintFoodAdapter;
import vn.fpoly.fpolybookcarclient.adapter.home.PlaceBannerAdapter;
import vn.fpoly.fpolybookcarclient.model.objectClass.CakesBanner;
import vn.fpoly.fpolybookcarclient.model.objectClass.ChallengeBanner;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodBanner;
import vn.fpoly.fpolybookcarclient.model.objectClass.HintFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.PlaceBanner;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.presenter.cakebanner.PresenterCakeBanner;
import vn.fpoly.fpolybookcarclient.presenter.challengebanner.PresenterChallengerBanner;
import vn.fpoly.fpolybookcarclient.presenter.eatwhatbanner.PresenterEatWhat;
import vn.fpoly.fpolybookcarclient.presenter.foodbanner.PresenterFoodBanner;
import vn.fpoly.fpolybookcarclient.presenter.placebanner.PresenterPlaceBanner;
import vn.fpoly.fpolybookcarclient.view.maps.GoogleMapActivity;


public class HomeFragment extends Fragment implements View.OnClickListener,IViewCakeBanner,IViewPlaceBanner,IViewFoodBanner,IViewEatWhat,IViewChallengeBanner {

    private RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4, recyclerView5;
    private PresenterCakeBanner presenterCakeBanner;
    private PresenterPlaceBanner presenterPlaceBanner;
    private PresenterFoodBanner presenterFoodBanner;
    private PresenterEatWhat presenterEatWhat;
    private PresenterChallengerBanner presenterChallengerBanner;

    private CakeBannerAdapter cakeBannerAdapter;
    private ChallengeBannerAdapter bannerAdapter;
    private PlaceBannerAdapter placeBannerAdapter;
    private FoodBannerAdapter foodBannerAdapter;
    private HintFoodAdapter hintFoodAdapter;
    private ImageView imgIconCar;
    private SpinKitView progressBar2,progressBar3,progressBar4,progressBar5;
    private TextView txtOverFlowMenu1,txtOverFlowMenu2,txtOverFlowMenu3,txtOverFlowMenu4;
    private LinearLayout layoutChooseCarHome, layoutChooseBikeHome, layoutChooseFoodHome, layoutChooseGiftHome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);

        recyclerView1.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);
        recyclerView3.setHasFixedSize(true);
        recyclerView4.setHasFixedSize(true);
        recyclerView5.setHasFixedSize(true);

        txtOverFlowMenu1.setOnClickListener(this);
        txtOverFlowMenu2.setOnClickListener(this);
        txtOverFlowMenu3.setOnClickListener(this);
        txtOverFlowMenu4.setOnClickListener(this);



        presenterEatWhat.getListEatWhat();
        presenterCakeBanner.getListCake();
        presenterPlaceBanner.getListPlace();
        presenterFoodBanner.getListFood();
        presenterChallengerBanner.getListChallenge();

        layoutChooseCarHome.setOnClickListener(this);



        return view;
    }




    private void initView(View view) {
        recyclerView1               = view.findViewById(R.id.reycelviewItem1);
        recyclerView2               = view.findViewById(R.id.reycelviewItem2);
        recyclerView3               = view.findViewById(R.id.reycelviewItem3);
        recyclerView4               = view.findViewById(R.id.reycelviewItem4);
        recyclerView5               = view.findViewById(R.id.reycelviewItem5);

        txtOverFlowMenu1            =  view.findViewById(R.id.menuOverflow1);
        txtOverFlowMenu2            =  view.findViewById(R.id.menuOverflow2);
        txtOverFlowMenu3            =  view.findViewById(R.id.menuOverflow3);
        txtOverFlowMenu4            =  view.findViewById(R.id.menuOverflow4);

        layoutChooseCarHome         = view.findViewById(R.id.layoutChooseCarHome);
        layoutChooseBikeHome        = view.findViewById(R.id.layoutChooseBikeHome);
        layoutChooseFoodHome        = view.findViewById(R.id.layoutChooseFoodHome);
        layoutChooseGiftHome        = view.findViewById(R.id.layoutChooseGiftHome);

        presenterCakeBanner         = new PresenterCakeBanner(this);
        presenterPlaceBanner        = new PresenterPlaceBanner(this);
        presenterFoodBanner         = new PresenterFoodBanner(this);
        presenterEatWhat            = new PresenterEatWhat(this);
        presenterChallengerBanner   = new PresenterChallengerBanner(this);

        progressBar2                = view.findViewById(R.id.progressbarItem2);
        progressBar3                = view.findViewById(R.id.progressbarItem3);
        progressBar4                = view.findViewById(R.id.progressbarItem4);
        progressBar5                = view.findViewById(R.id.progressbarItem5);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutChooseCarHome:
                startActivity(new Intent(getActivity(), GoogleMapActivity.class));
                break;
            case R.id.layoutChooseBikeHome:

                break;
            case R.id.layoutChooseFoodHome:

                break;
            case R.id.layoutChooseGiftHome:
                break;
            case R.id.menuOverflow1:
                creatOptionMenu1();
                break;
            case R.id.menuOverflow2:
                creatOptionMenu2();
                break;
            case R.id.menuOverflow3:
                creatOptionMenu3();
                break;
            case R.id.menuOverflow4:
                creatOptionMenu4();
                break;

        }
    }

    private void creatOptionMenu1() {
        PopupMenu popupMenu = new PopupMenu(getActivity(), txtOverFlowMenu1);
        popupMenu.inflate(R.menu.menuoverflow);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menushare:
                        Toast.makeText(getActivity(), "Share", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menulike:
                        Toast.makeText(getActivity(), "Like", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.menusad:
                        Toast.makeText(getActivity(), "Sad", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });
        popupMenu.show();
    }

    private void creatOptionMenu2() {
        PopupMenu popupMenu = new PopupMenu(getActivity(), txtOverFlowMenu2);
        popupMenu.inflate(R.menu.menuoverflow);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menushare:
                        Toast.makeText(getActivity(), "Share", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menulike:
                        Toast.makeText(getActivity(), "Like", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.menusad:
                        Toast.makeText(getActivity(), "Sad", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });
        popupMenu.show();
    }

    private void creatOptionMenu3() {
        PopupMenu popupMenu = new PopupMenu(getActivity(), txtOverFlowMenu3);
        popupMenu.inflate(R.menu.menuoverflow);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menushare:
                        Toast.makeText(getActivity(), "Share", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menulike:
                        Toast.makeText(getActivity(), "Like", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.menusad:
                        Toast.makeText(getActivity(), "Sad", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });
        popupMenu.show();
    }

    private void creatOptionMenu4() {
        PopupMenu popupMenu = new PopupMenu(getActivity(), txtOverFlowMenu4);
        popupMenu.inflate(R.menu.menuoverflow);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menushare:
                        Toast.makeText(getActivity(), "Share", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menulike:
                        Toast.makeText(getActivity(), "Like", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.menusad:
                        Toast.makeText(getActivity(), "Sad", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public void displayListCake(ArrayList<CakesBanner> arrCakeBanner) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager);
        progressBar2.setVisibility(View.GONE);
        cakeBannerAdapter = new CakeBannerAdapter(getActivity(), R.layout.row_cake_banner, arrCakeBanner, true);
        recyclerView2.setAdapter(cakeBannerAdapter);

    }

    @Override
    public void displayListPlace(ArrayList<PlaceBanner> arrPlaceBanner) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layoutManager);
        progressBar3.setVisibility(View.GONE);
        placeBannerAdapter = new PlaceBannerAdapter(getActivity(), R.layout.row_place_banner, arrPlaceBanner);
        recyclerView3.setAdapter(placeBannerAdapter);


    }

    @Override
    public void displayListFood(ArrayList<FoodBanner> arrFoodBanner) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView4.setLayoutManager(layoutManager);
        progressBar4.setVisibility(View.GONE);
        foodBannerAdapter = new FoodBannerAdapter(getActivity(), R.layout.row_food_banner, arrFoodBanner);
        recyclerView4.setAdapter(foodBannerAdapter);


    }

    @Override
    public void displayListEatWhat(ArrayList<HintFood> arrHintFood) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
            recyclerView5.setLayoutManager(layoutManager);
            progressBar5.setVisibility(View.GONE);
            hintFoodAdapter = new HintFoodAdapter(getActivity(), R.layout.row_hintfood_banner, arrHintFood);
            recyclerView5.setAdapter(hintFoodAdapter);
    }

    @Override
    public void displayListChallenge(ArrayList<ChallengeBanner> arrChallenge) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager);
        bannerAdapter = new ChallengeBannerAdapter(arrChallenge, getActivity(), R.layout.row_challenge_banner);
        recyclerView1.setAdapter(bannerAdapter);
    }
}

