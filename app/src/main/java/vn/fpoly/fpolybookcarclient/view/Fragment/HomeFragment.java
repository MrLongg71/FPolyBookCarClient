package vn.fpoly.fpolybookcarclient.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.adapter.Home.CakeBannerAdapter;
import vn.fpoly.fpolybookcarclient.adapter.Home.ChallengeBannerAdapter;
import vn.fpoly.fpolybookcarclient.adapter.Home.FoodBannerAdapter;
import vn.fpoly.fpolybookcarclient.adapter.Home.HintFoodAdapter;
import vn.fpoly.fpolybookcarclient.adapter.Home.PlaceBannerAdapter;
import vn.fpoly.fpolybookcarclient.model.objectClass.CakesBanner;
import vn.fpoly.fpolybookcarclient.model.objectClass.ChallengeBanner;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodBanner;
import vn.fpoly.fpolybookcarclient.model.objectClass.HintFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.PlaceBanner;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.view.maps.GoogleMapActivity;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4, recyclerView5;

    private ArrayList<ChallengeBanner> arrayChanllenge  = new ArrayList<>();
    private ArrayList<CakesBanner> arrCake              = new ArrayList<>();
    private ArrayList<PlaceBanner> arrPlace             = new ArrayList<>();
    private ArrayList<FoodBanner> arrFood               = new ArrayList<>();
    private ArrayList<HintFood> arrHintFood             = new ArrayList<>();
    private CakeBannerAdapter cakeBannerAdapter;
    private ChallengeBannerAdapter bannerAdapter;
    private PlaceBannerAdapter placeBannerAdapter;
    private FoodBannerAdapter foodBannerAdapter;
    private HintFoodAdapter hintFoodAdapter;
    private ImageView imgIconCar;
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


        itemChallengeBanner();
        itemCakeBanner();
        itemPlacebanner();

        layoutChooseCarHome.setOnClickListener(this);

        itemFoodBanner();
        itemHintFoodBanner();


        return view;
    }



    private void dataChallengeBanner() {
        arrayChanllenge.add(new ChallengeBanner(R.drawable.banner1));
        arrayChanllenge.add(new ChallengeBanner(R.drawable.banner2));
        arrayChanllenge.add(new ChallengeBanner(R.drawable.banner3));
        arrayChanllenge.add(new ChallengeBanner(R.drawable.banner4));
        arrayChanllenge.add(new ChallengeBanner(R.drawable.banner5));

    }

    private void dataCakeBanner() {
        arrCake.add(new CakesBanner(getString(R.string.caketitle1), getString(R.string.cakdetaile1), R.drawable.cake1));
        arrCake.add(new CakesBanner(getString(R.string.caketitle2), getString(R.string.cakedetail2), R.drawable.cake2));
        arrCake.add(new CakesBanner(getString(R.string.caketitle3), getString(R.string.cakedetail3), R.drawable.cake3));
        arrCake.add(new CakesBanner(getString(R.string.caketitle4), getString(R.string.cakedetail4), R.drawable.cake5));
        arrCake.add(new CakesBanner(getString(R.string.caketitle5), getString(R.string.cakedetail5), R.drawable.cak4));

    }

    private void dataPlaceBanner() {
        arrPlace.add(new PlaceBanner(getString(R.string.placetitle1), getString(R.string.placedetail2), R.drawable.place1));
        arrPlace.add(new PlaceBanner(getString(R.string.placetitle2), getString(R.string.placedetail2), R.drawable.place3));
        arrPlace.add(new PlaceBanner(getString(R.string.placetitle3), getString(R.string.placedetail3), R.drawable.place5));
        arrPlace.add(new PlaceBanner(getString(R.string.placetitle4), getString(R.string.placedetail4), R.drawable.place2));
        arrPlace.add(new PlaceBanner(getString(R.string.placetitle5), getString(R.string.placedetail6), R.drawable.place4));

    }

    private void initView(View view) {
        recyclerView1       = view.findViewById(R.id.reycelviewItem1);
        recyclerView2       = view.findViewById(R.id.reycelviewItem2);
        recyclerView3       = view.findViewById(R.id.reycelviewItem3);
        recyclerView4       = view.findViewById(R.id.reycelviewItem4);
        recyclerView5       = view.findViewById(R.id.reycelviewItem5);
        txtOverFlowMenu1    = view.findViewById(R.id.menuOverflow1);
        txtOverFlowMenu2    =  view.findViewById(R.id.menuOverflow2);
        txtOverFlowMenu3    = view.findViewById(R.id.menuOverflow3);
        txtOverFlowMenu4    =  view.findViewById(R.id.menuOverflow4);

        layoutChooseCarHome  = view.findViewById(R.id.layoutChooseCarHome);
        layoutChooseBikeHome = view.findViewById(R.id.layoutChooseBikeHome);
        layoutChooseFoodHome = view.findViewById(R.id.layoutChooseFoodHome);
        layoutChooseGiftHome = view.findViewById(R.id.layoutChooseGiftHome);
    }

    private void dataFoodBanner () {
            arrFood.add(new FoodBanner("Combo nướng + bia ", "Chỉ trên BookFood! Thử ngay Combo nướng + bia giảm giá 20%!", R.drawable.food1));
            arrFood.add(new FoodBanner("Phở Hùng Vương đặc biệt", "Chỉ trên BookFood! Thử ngay tất cả loại phở giảm giá 20%!", R.drawable.food2));
            arrFood.add(new FoodBanner("Hải sản đặc biệt", "Chỉ trên BookFood!! Thử ngay tất cả món ăn từng bừng khai trương giảm giá hơn 15%", R.drawable.food3));
            arrFood.add(new FoodBanner("Combo nướng hấp dẫn", "Chỉ trên BookFood!! Thử ngay Buffe với giá ưu đãi tuột bô,tẹt quần,chỉ với 195K", R.drawable.food4));
            arrFood.add(new FoodBanner("Gà cuộn phô mai", "Chỉ trên BookFood!! Thử ngay ăn vặt giá rẻ ăn,ăn nhiều càng ưu đãi nhiều", R.drawable.food5));

        }

        private void dataHintFootBanner () {
            arrHintFood.add(new HintFood(getString(R.string.titlehintfood1), getString(R.string.detailhintfood1), R.drawable.hint1, getString(R.string.minutehintfood1), "5.0"));
            arrHintFood.add(new HintFood(getString(R.string.titlehintfood2), getString(R.string.detailhintfood2), R.drawable.hint2, getString(R.string.minutehintfood2), "4.2"));
            arrHintFood.add(new HintFood(getString(R.string.titlehintfood3), getString(R.string.detailhintfood3), R.drawable.hint3, getString(R.string.minutehintfood3), "4.8"));
            arrHintFood.add(new HintFood(getString(R.string.titlehintfood4), getString(R.string.detailhintfood4), R.drawable.hint4, getString(R.string.minutehintfood4), "3.7"));
            arrHintFood.add(new HintFood(getString(R.string.titlehintfood5), getString(R.string.detailhintfood5), R.drawable.hint5, getString(R.string.minutehintfood5), "4.1"));

        }


        private void itemChallengeBanner () {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
            recyclerView1.setLayoutManager(layoutManager);
            dataChallengeBanner();
            bannerAdapter = new ChallengeBannerAdapter(arrayChanllenge, getActivity(), R.layout.row_challenge_banner);
            recyclerView1.setAdapter(bannerAdapter);
        }

        private void itemCakeBanner () {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
            recyclerView2.setLayoutManager(layoutManager);
            dataCakeBanner();
            cakeBannerAdapter = new CakeBannerAdapter(getActivity(), R.layout.row_cake_banner, arrCake);
            recyclerView2.setAdapter(cakeBannerAdapter);
        }

        private void itemPlacebanner () {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
            recyclerView3.setLayoutManager(layoutManager);
            dataPlaceBanner();
            placeBannerAdapter = new PlaceBannerAdapter(getActivity(), R.layout.row_place_banner, arrPlace);
            recyclerView3.setAdapter(placeBannerAdapter);
        }


        @Override
        public void onClick (View v){
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

        private void itemFoodBanner () {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
            recyclerView4.setLayoutManager(layoutManager);
            dataFoodBanner();
            foodBannerAdapter = new FoodBannerAdapter(getActivity(), R.layout.row_food_banner, arrFood);
            recyclerView4.setAdapter(foodBannerAdapter);
        }

        private void itemHintFoodBanner () {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
            recyclerView5.setLayoutManager(layoutManager);
            dataHintFootBanner();
            hintFoodAdapter = new HintFoodAdapter(getActivity(), R.layout.row_hintfood_banner, arrHintFood);
            recyclerView5.setAdapter(hintFoodAdapter);
        }

        private void creatOptionMenu1 () {
            PopupMenu popupMenu = new PopupMenu(getActivity(),txtOverFlowMenu1 );
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
        private void creatOptionMenu2 () {
        PopupMenu popupMenu = new PopupMenu(getActivity(),txtOverFlowMenu2 );
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
        private void creatOptionMenu3 () {
        PopupMenu popupMenu = new PopupMenu(getActivity(),txtOverFlowMenu3 );
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
        private void creatOptionMenu4 () {
        PopupMenu popupMenu = new PopupMenu(getActivity(),txtOverFlowMenu4 );
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

    }

