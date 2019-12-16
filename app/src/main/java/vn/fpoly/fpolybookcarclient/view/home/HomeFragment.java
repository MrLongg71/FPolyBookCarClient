package vn.fpoly.fpolybookcarclient.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

import vn.fpoly.fpolybookcarclient.adapter.home.NewsBannerAdapter;
import vn.fpoly.fpolybookcarclient.adapter.home.ChallengeBannerAdapter;
import vn.fpoly.fpolybookcarclient.adapter.home.HintFoodAdapter;
import vn.fpoly.fpolybookcarclient.model.objectClass.ChallengeBanner;
import vn.fpoly.fpolybookcarclient.model.objectClass.HintFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.News;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.presenter.home.newsbanner.PresenterNewsBanner;
import vn.fpoly.fpolybookcarclient.presenter.home.challengebanner.PresenterChallengerBanner;
import vn.fpoly.fpolybookcarclient.presenter.home.eatwhat.PresenterEatWhat;
import vn.fpoly.fpolybookcarclient.service.MessagingService;
import vn.fpoly.fpolybookcarclient.view.activity.HomeActivity;
import vn.fpoly.fpolybookcarclient.view.food.food_home.FoodFragment;
import vn.fpoly.fpolybookcarclient.view.maps.GoogleMapActivity;


public class HomeFragment extends Fragment implements View.OnClickListener, IViewCakeBanner, IViewEatWhat, IViewChallengeBanner {

    private RecyclerView recyclerViewChallenge, recyclerViewCake, recyclerViewPlace, recyclerViewFood, recyclerViewEatWhat;
    private PresenterNewsBanner presenterNewsBanner;
    private PresenterEatWhat presenterEatWhat;
    private PresenterChallengerBanner presenterChallengerBanner;
    private NewsBannerAdapter newsBannerAdapter;
    private ChallengeBannerAdapter bannerAdapter;
    private HintFoodAdapter hintFoodAdapter;
    private SpinKitView progressBarCake,progressBarPlace,progressBarFood,progressBarEatWhat;
    private TextView txtOverFlowMenuChallenge,txtOverFlowMenuCakeBanner,txtOverFlowMenuPlaceBanner,txtOverFlowMenuFoodBanner;
    private LinearLayout layoutChooseCarHome, layoutChooseBikeHome, layoutChooseFoodHome, layoutChooseGiftHome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);

        recyclerViewChallenge.setHasFixedSize(true);
        recyclerViewCake.setHasFixedSize(true);
        recyclerViewPlace.setHasFixedSize(true);
        recyclerViewFood.setHasFixedSize(true);
        recyclerViewEatWhat.setHasFixedSize(true);

        txtOverFlowMenuChallenge.setOnClickListener(this);
        txtOverFlowMenuCakeBanner.setOnClickListener(this);
        txtOverFlowMenuPlaceBanner.setOnClickListener(this);
        txtOverFlowMenuFoodBanner.setOnClickListener(this);


        presenterEatWhat.getListEatWhat();
        presenterNewsBanner.getListCakeBanner();

        presenterChallengerBanner.getListChallenge();

        layoutChooseCarHome.setOnClickListener(this);
        layoutChooseFoodHome.setOnClickListener(this);
        layoutChooseBikeHome.setOnClickListener(this);
        layoutChooseGiftHome.setOnClickListener(this);

        return view;
    }

    private void initView(View view) {
        recyclerViewChallenge                           = view.findViewById(R.id.reycelviewItem1);
        recyclerViewCake                                = view.findViewById(R.id.reycelviewItem2);
        recyclerViewPlace                               = view.findViewById(R.id.reycelviewItem3);
        recyclerViewFood                                = view.findViewById(R.id.reycelviewItem4);
        recyclerViewEatWhat                             =   view.findViewById(R.id.reycelviewItem5);

        txtOverFlowMenuChallenge                        =  view.findViewById(R.id.menuOverflow1);
        txtOverFlowMenuCakeBanner                       =  view.findViewById(R.id.menuOverflow2);
        txtOverFlowMenuPlaceBanner                      =  view.findViewById(R.id.menuOverflow3);
        txtOverFlowMenuFoodBanner                       =  view.findViewById(R.id.menuOverflow4);

        layoutChooseCarHome                             = view.findViewById(R.id.layoutChooseCarHome);
        layoutChooseBikeHome                            = view.findViewById(R.id.layoutChooseBikeHome);
        layoutChooseFoodHome                            = view.findViewById(R.id.layoutChooseFoodHome);
        layoutChooseGiftHome                            = view.findViewById(R.id.layoutChooseGiftHome);

        presenterNewsBanner = new PresenterNewsBanner(this);
        presenterEatWhat                                = new PresenterEatWhat(this);
        presenterChallengerBanner                       = new PresenterChallengerBanner(this);

        progressBarCake                                 = view.findViewById(R.id.progressbarItem2);
        progressBarPlace                                = view.findViewById(R.id.progressbarItem3);
        progressBarFood                                 = view.findViewById(R.id.progressbarItem4);
        progressBarEatWhat                              = view.findViewById(R.id.progressbarItem5);
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
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conten,new FoodFragment()).commit();
                HomeActivity.navView.setVisibility(View.GONE);
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
        PopupMenu popupMenu = new PopupMenu(getActivity(), txtOverFlowMenuChallenge);
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
        PopupMenu popupMenu = new PopupMenu(getActivity(), txtOverFlowMenuCakeBanner);
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
        PopupMenu popupMenu = new PopupMenu(getActivity(), txtOverFlowMenuPlaceBanner);
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
        PopupMenu popupMenu = new PopupMenu(getActivity(), txtOverFlowMenuFoodBanner);
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
    public void displayListEatWhat(ArrayList<HintFood> arrHintFood) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerViewEatWhat.setLayoutManager(layoutManager);
        progressBarEatWhat.setVisibility(View.GONE);
        hintFoodAdapter = new HintFoodAdapter(getActivity(), R.layout.row_hintfood_banner, arrHintFood);
        recyclerViewEatWhat.setAdapter(hintFoodAdapter);
    }

    @Override
    public void displayListChallenge(ArrayList<ChallengeBanner> arrChallenge) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerViewChallenge.setLayoutManager(layoutManager);
        bannerAdapter = new ChallengeBannerAdapter(arrChallenge, getActivity(), R.layout.row_challenge_banner);
        recyclerViewChallenge.setAdapter(bannerAdapter);
    }


    @Override
    public void displayListCake(ArrayList<News> arrCake, ArrayList<News> arrPlace, ArrayList<News> arrFood) {
        recyclerViewCake.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recyclerViewPlace.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recyclerViewPlace.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        progressBarCake.setVisibility(View.GONE);
        progressBarFood.setVisibility(View.GONE);
        progressBarPlace.setVisibility(View.GONE);

        newsBannerAdapter = new NewsBannerAdapter(getActivity(),arrCake, true);
        recyclerViewCake.setAdapter(newsBannerAdapter);
        newsBannerAdapter.notifyDataSetChanged();

        NewsBannerAdapter newsBannerPlace = new NewsBannerAdapter(getActivity(),arrPlace, true);
        recyclerViewPlace.setAdapter(newsBannerPlace);
        newsBannerPlace.notifyDataSetChanged();

        NewsBannerAdapter newsBannerFood = new NewsBannerAdapter(getActivity(),arrFood,true);
        recyclerViewFood.setAdapter(newsBannerFood);
        newsBannerFood.notifyDataSetChanged();

    }

    @Override
    public void onResume() {
        super.onResume();
        HomeActivity.navView.setVisibility(View.VISIBLE);
    }

}



