package vn.fpoly.fpolybookcarclient.view.food.food_home;

import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.github.ybq.android.spinkit.SpinKitView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import vn.fpoly.fpolybookcarclient.Constans;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.adapter.food.Categories_MenuFoodAdapter;
import vn.fpoly.fpolybookcarclient.adapter.food.RestaurantAdapter;
import vn.fpoly.fpolybookcarclient.adapter.food.ViewpagerFood;
import vn.fpoly.fpolybookcarclient.library.CallBackFragment;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodPager;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodCategories;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;
import vn.fpoly.fpolybookcarclient.presenter.food.foodviewpager.PresenterFood;
import vn.fpoly.fpolybookcarclient.presenter.food.foodcategories.PresenterFoodCategories;
import vn.fpoly.fpolybookcarclient.presenter.food.restaurant.PresenterRestaurant;
import vn.fpoly.fpolybookcarclient.view.food.menu_restaurant.MenuRestaurantFragment;

public class FoodFragment extends Fragment implements IViewFood, IViewFoodCategories,
        IViewRestaurant, CallbackRestaurantAdpater {
    private ViewpagerFood viewpagerFood;
    private ViewPager viewPager;
    private PresenterFood presenterFood;
    private int currentPage = 0;
    private int NUM_PAGES = 10;
    private PresenterRestaurant presenterRestaurant;
    private Timer timer;
    private RestaurantAdapter foodSaleAdapter;
    private Categories_MenuFoodAdapter categoriesMenuFoodAdapter;
    private PresenterFoodCategories presenterFoodCategories;
    private RecyclerView recyclerViewFoodCategories,recyclerViewRestaurant,recyclerViewBreakFast,recyclerViewMenuFood;
    private SharedPreferences sharedPreferences;
    private String locationLatitude ;
    private String locationLongitude ;
    private TextView txtAdress;
    private Geocoder geocoder;
    private List<Address>arrAddresss;
    private ArrayList<Restaurant> restaurantArrayList = new ArrayList<>();
    private FragmentManager fragmentManager;
    private String addresCurrent = "";
    private SpinKitView progressbarCate,progressbarfoodSale,progressbarBreakFast,progressbarMenufood;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        initView(view);
        CallBackFragment.CallbackHome(view,fragmentManager);
        geocoder  = new Geocoder(getActivity(), Locale.getDefault());

        sharedPreferences = getActivity().getSharedPreferences("toado", 0);

        locationLatitude = sharedPreferences.getString("locationlatitude","");
        locationLongitude = sharedPreferences.getString("locationlongitude","");

       if (locationLatitude != null && locationLongitude != null ){
           double latitude = Double.parseDouble(locationLatitude);
           double longitude = Double.parseDouble(locationLongitude);
           try {

               arrAddresss = geocoder.getFromLocation(latitude, longitude, 1);
               String addres = arrAddresss.get(0).getAddressLine(0);
               String address[] = addres.split(",");
               addresCurrent = address[0] + " Quận " + address[2];

               txtAdress.setText(addresCurrent);
           } catch (IOException e) {
               e.printStackTrace();
           }
       } else {
           txtAdress.setText("");
       }
        presenterFood.getListFood();
        presenterFoodCategories.getListFoodCategories();
        presenterRestaurant.getListRestaurant();


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
        fragmentManager                                     = getActivity().getSupportFragmentManager();
        viewPager                                           = view.findViewById(R.id.viewpager);
        presenterFood                                       = new PresenterFood(this);
        recyclerViewFoodCategories                          = view.findViewById(R.id.reycelviewFood1);
        presenterFoodCategories                             = new PresenterFoodCategories(this);
        presenterRestaurant                                 = new PresenterRestaurant(this);
        recyclerViewRestaurant                              = view.findViewById(R.id.reycelviewFood2);
        recyclerViewBreakFast                               = view.findViewById(R.id.reycelviewFood3);
        recyclerViewMenuFood                                = view.findViewById(R.id.reycelviewFood5);
        txtAdress                                           = view.findViewById(R.id.txtAddress);
        progressbarCate                                     = view.findViewById(R.id.progressbarCategorie);
        progressbarBreakFast                                = view.findViewById(R.id.progressbarBreakFast);
        progressbarfoodSale                                 = view.findViewById(R.id.progressbarfoodsale);
        progressbarMenufood                                 = view.findViewById(R.id.progressbarMenutype);
    }


    @Override
    public void displayFood(ArrayList<FoodPager> arrFood) {
        viewpagerFood = new ViewpagerFood(getActivity(), arrFood);
        viewPager.setAdapter(viewpagerFood);
    }


    @Override
    public void displayRestaurant(ArrayList<Restaurant> arrRestaurant) {
        progressbarfoodSale.setVisibility(View.GONE);
        recyclerViewRestaurant.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerViewRestaurant.setLayoutManager(linearLayoutManager);
        foodSaleAdapter = new RestaurantAdapter(arrRestaurant, getActivity(), R.layout.custom_row_restaurantprice,this);
        recyclerViewRestaurant.setAdapter(foodSaleAdapter);
        foodSaleAdapter.notifyDataSetChanged();

    }

    @Override
    public void displayFoodMenu(ArrayList<Restaurant> arrRestaurant) {
        restaurantArrayList.addAll(arrRestaurant);
        Collections.reverse(restaurantArrayList);
        progressbarBreakFast.setVisibility(View.GONE);
        recyclerViewBreakFast.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerViewBreakFast.setLayoutManager(linearLayoutManager);
        foodSaleAdapter = new RestaurantAdapter(restaurantArrayList, getActivity(), R.layout.custom_row_restaurantprice,this);
        recyclerViewBreakFast.setAdapter(foodSaleAdapter);
        foodSaleAdapter.notifyDataSetChanged();
    }


    @Override
    public void displayFoodCategories(ArrayList<FoodCategories> arrFoodCategories, ArrayList<FoodCategories> arrMenuFood) {
        recyclerViewMenuFood.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerViewFoodCategories.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        progressbarCate.setVisibility(View.GONE);
        progressbarMenufood.setVisibility(View.GONE);
        categoriesMenuFoodAdapter = new Categories_MenuFoodAdapter(arrFoodCategories, R.layout.custom_row_categories, getActivity());
        recyclerViewFoodCategories.setAdapter(categoriesMenuFoodAdapter);
        categoriesMenuFoodAdapter.notifyDataSetChanged();

        Categories_MenuFoodAdapter categories_menuFoodAdapter = new Categories_MenuFoodAdapter(arrMenuFood, R.layout.custom_row_menufood, getActivity());
        recyclerViewMenuFood.setAdapter(categories_menuFoodAdapter);
        categories_menuFoodAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClickRestautantMenu(Restaurant restaurant,String distance) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constans.KEY_BUNDEL_RESTAURANT,restaurant);
        bundle.putString(Constans.KEY_BUNDEL_RESTAURANT_DISTANCETO,distance);
        bundle.putString(Constans.KEY_BUNDEL_RESTAURANT_ADDRES_NAME_CURRENT,addresCurrent);

        MenuRestaurantFragment menuRestaurantFragment = new MenuRestaurantFragment();
        menuRestaurantFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.conten,menuRestaurantFragment).addToBackStack(null).commit();

    }


}
