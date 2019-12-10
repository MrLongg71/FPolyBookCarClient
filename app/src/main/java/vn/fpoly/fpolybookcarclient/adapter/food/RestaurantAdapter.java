package vn.fpoly.fpolybookcarclient.adapter.food;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Logger;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;
import vn.fpoly.fpolybookcarclient.view.food.food_home.CallbackRestaurantAdpater;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private List<Restaurant> arrRestaurant;
    private Context context;
    private int layout;
    private CallbackRestaurantAdpater callbackRestaurantAdpater;
    private String distance = "";
    final StorageReference storageReference = FirebaseStorage.getInstance().getReference();


    public RestaurantAdapter(List<Restaurant> arrRestaurant, Context context, int layout,CallbackRestaurantAdpater callbackRestaurantAdpater) {
        this.arrRestaurant = arrRestaurant;
        this.context = context;
        this.layout = layout;
        this.callbackRestaurantAdpater = callbackRestaurantAdpater;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Restaurant restaurant = arrRestaurant.get(position);
        holder.txtTitle.setText(restaurant.getName());
        holder.txtDetail.setText(restaurant.getDetail());
        storageReference.child("ImageRestaurant").child(restaurant.getImage())
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                restaurant.setImage(uri.toString());
                Glide.with(context).load(restaurant.getImage()).centerCrop().into(holder.roundedImageView);
            }
        });
        Glide.with(context).load(restaurant.getImage()).centerCrop().into(holder.roundedImageView);
        distanceTo(position,holder.txtDistance);

        holder.layoutRestaurantFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbackRestaurantAdpater.onClickRestautantMenu(restaurant,distance);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrRestaurant.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutRestaurantFood;
        RoundedImageView roundedImageView;
        TextView txtDistance,txtTitle,txtDetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutRestaurantFood = itemView.findViewById(R.id.layoutRestaurantFood);
            roundedImageView    = itemView.findViewById(R.id.imgFood1);
            txtDistance         = itemView.findViewById(R.id.txtDistanceFood1);
            txtDetail           = itemView.findViewById(R.id.txtDetailFood1);
            txtTitle            = itemView.findViewById(R.id.txtTitleFood1);
        }
    }
    private void distanceTo(int postion, TextView txtDistance){

        SharedPreferences sharedPreferences = context.getSharedPreferences("toado", 0);

        String locationLatitude = sharedPreferences.getString("locationlatitude","");
        String locationLongitude = sharedPreferences.getString("locationlongitude","");
        Location locationCurrent = new Location("");
        locationCurrent.setLatitude(Double.parseDouble(locationLatitude));
        locationCurrent.setLongitude(Double.parseDouble(locationLongitude));

        Location locationRestaurant = new Location("");
        locationRestaurant.setLatitude(arrRestaurant.get(postion).getLatitude());
        locationRestaurant.setLongitude(arrRestaurant.get(postion).getLongitude());
        DecimalFormat dcf = new DecimalFormat("#.#");
        distance = String.valueOf(dcf.format((locationCurrent.distanceTo(locationRestaurant))/1000));
        txtDistance.setText(distance +" km");

    }
}
