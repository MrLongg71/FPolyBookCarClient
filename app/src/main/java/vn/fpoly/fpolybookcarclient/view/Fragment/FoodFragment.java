package vn.fpoly.fpolybookcarclient.view.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.adapter.BreakFastAdapter;
import vn.fpoly.fpolybookcarclient.adapter.CategoriesAdapter;
import vn.fpoly.fpolybookcarclient.adapter.FoodSaleAdapter;
import vn.fpoly.fpolybookcarclient.adapter.PopularFoodAdapter;
import vn.fpoly.fpolybookcarclient.adapter.ViewpagerFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.BreakFast;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodPager;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodCategories;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodSale;
import vn.fpoly.fpolybookcarclient.model.objectClass.PopularFood;
import vn.fpoly.fpolybookcarclient.presenter.breakfast.PresenterBreakFast;
import vn.fpoly.fpolybookcarclient.presenter.food.PresenterFood;
import vn.fpoly.fpolybookcarclient.presenter.foodcategories.PresenterFoodCategories;
import vn.fpoly.fpolybookcarclient.presenter.foodsale.PresenterFoodSale;
import vn.fpoly.fpolybookcarclient.presenter.popularfood.PresenterPopularFood;

public class FoodFragment extends Fragment implements IViewFood,IViewFoodCategories,IViewFoodSale,IViewBreakFast,IViewPopularFood {
    private ViewpagerFood viewpagerFood;
    private ViewPager viewPager;
    private PresenterFood presenterFood;
    private int currentPage = 0;
    private int NUM_PAGES = 10;
    private PopularFoodAdapter popularFoodAdapter;
    private PresenterPopularFood presenterPopularFood;
    private PresenterFoodSale presenterFoodSale;
    private Timer timer;
    private PresenterBreakFast presenterBreakFast;
    private BreakFastAdapter breakFastAdapter;
    private FoodSaleAdapter foodSaleAdapter;
    private CategoriesAdapter categoriesAdapter;
    private PresenterFoodCategories presenterFoodCategories;
    private RecyclerView recyclerViewFood1,reycelviewFood2,reycelviewFood3,reycelviewFood5;
    private SharedPreferences sharedPreferences;
    private String locationLatitude ;
    private String locationLongitude ;
    private TextView txtAdress;
    private Geocoder geocoder;
    private List<Address>arrAddresss;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        initView(view);
        geocoder  = new Geocoder(getActivity(), Locale.getDefault());

        sharedPreferences = getActivity().getSharedPreferences("toado", 0);

        locationLatitude = sharedPreferences.getString("locationlatitude","");
        locationLongitude = sharedPreferences.getString("locationlongitude","");

       if (locationLatitude != null && locationLongitude != null ){

           try {
               double latitude = Double.parseDouble(locationLatitude);
               double longitude = Double.parseDouble(locationLongitude);
               arrAddresss = geocoder.getFromLocation(latitude,longitude,1);
               String addres = arrAddresss.get(0).getAddressLine(0);
               txtAdress.setText(addres);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }else {
           txtAdress.setText("");
       }

        presenterFood.getListFood();
        presenterFoodCategories.getListFoodCategories();
        presenterFoodSale.getListFoodSale();
        presenterPopularFood.getListPopularFood();
        presenterBreakFast.getListBreakFast();

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES - 1) {
                    currentPage = 0;

                }
                viewPager.setCurrentItem(currentPage++, true);

            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 0, 3000);
        return view;
    }
    private void initView(View view){
        viewPager               = view.findViewById(R.id.viewpager);
        presenterFood           = new PresenterFood(this);
        recyclerViewFood1       = view.findViewById(R.id.reycelviewFood1);
        presenterFoodCategories = new PresenterFoodCategories(this);
        presenterFoodSale       = new PresenterFoodSale(this);
        reycelviewFood2         = view.findViewById(R.id.reycelviewFood2);
        reycelviewFood3         = view.findViewById(R.id.reycelviewFood3);
        reycelviewFood5         = view.findViewById(R.id.reycelviewFood5);
        presenterBreakFast      = new PresenterBreakFast(this);
        presenterPopularFood    = new PresenterPopularFood(this);
        txtAdress               = view.findViewById(R.id.txtAddress);
    }


    @Override
    public void displayFood(ArrayList<FoodPager> arrFood) {
        viewpagerFood = new ViewpagerFood(getActivity(), arrFood);
        viewPager.setAdapter(viewpagerFood);
    }

    @Override
    public void displayFoodCategories(ArrayList<FoodCategories> arrFoodCategories) {
        recyclerViewFood1.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerViewFood1.setLayoutManager(linearLayoutManager);
        categoriesAdapter = new CategoriesAdapter(arrFoodCategories, R.layout.custom_row_categories, getActivity());
        recyclerViewFood1.setAdapter(categoriesAdapter);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayFoodSale(ArrayList<FoodSale> arrFoodSale) {
        reycelviewFood2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        reycelviewFood2.setLayoutManager(linearLayoutManager);
        foodSaleAdapter = new FoodSaleAdapter(arrFoodSale, getActivity(), R.layout.custom_row_newsfood1);
        reycelviewFood2.setAdapter(foodSaleAdapter);
        foodSaleAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayBreakFast(ArrayList<BreakFast> arrBreakFast) {
        reycelviewFood3.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        reycelviewFood3.setLayoutManager(linearLayoutManager);
        breakFastAdapter = new BreakFastAdapter(getActivity(),R.layout.custom_row_newsfood2,arrBreakFast);
        reycelviewFood3.setAdapter(breakFastAdapter);
        breakFastAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayPopularFood(ArrayList<PopularFood> arrPopularFood) {
        reycelviewFood5.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        reycelviewFood5.setLayoutManager(linearLayoutManager);
        popularFoodAdapter = new PopularFoodAdapter(arrPopularFood,getActivity(),R.layout.custom_row_popular);
        reycelviewFood5.setAdapter(popularFoodAdapter);
        popularFoodAdapter.notifyDataSetChanged();
    }
}
