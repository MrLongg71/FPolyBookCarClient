package vn.fpoly.fpolybookcarclient.adapter.food;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodMenu;
import vn.fpoly.fpolybookcarclient.view.food.menu_restaurant.CallbackRestaurantMenuFood;

public class RestaurantMenuFoodAdapter extends RecyclerView.Adapter<RestaurantMenuFoodAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private ArrayList<FoodMenu> foodMenuArrayList;
    private final StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private CallbackRestaurantMenuFood callbackRestaurantMenuFood;
    public RestaurantMenuFoodAdapter(Context context, int layout, ArrayList<FoodMenu> foodMenuArrayList, CallbackRestaurantMenuFood callbackRestaurantMenuFood) {
        this.context = context;
        this.layout = layout;
        this.foodMenuArrayList = foodMenuArrayList;
        this.callbackRestaurantMenuFood = callbackRestaurantMenuFood;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final FoodMenu foodMenu = foodMenuArrayList.get(position);


        holder.txtNameFoodItemMenuRes.setText(foodMenu.getName());
        holder.txtDetailFoodItemMenuRes.setText(foodMenu.getDescription());
        holder.txtPriceFoodItemMenuRes.setText(foodMenu.getPrice());

        storageReference.child("ImageRestaurantMenuFood").child(foodMenu.getImage()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Glide.with(context).load(uri.toString()).centerCrop().into(holder.imgFoodItemMenuRes);
                        foodMenu.setImage(uri.toString());
                    }
                });

        holder.layoutItemMenuFoodRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbackRestaurantMenuFood.onClickItemMenuToCart(foodMenuArrayList.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return foodMenuArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutItemMenuFoodRes;
        RoundedImageView imgFoodItemMenuRes;
        TextView txtNameFoodItemMenuRes,txtDetailFoodItemMenuRes,txtPriceFoodItemMenuRes;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItemMenuFoodRes = itemView.findViewById(R.id.layoutItemMenuFoodRes);
            imgFoodItemMenuRes = itemView.findViewById(R.id.imgFoodItemMenuRes);
            txtNameFoodItemMenuRes = itemView.findViewById(R.id.txtNameFoodItemMenuRes);
            txtDetailFoodItemMenuRes = itemView.findViewById(R.id.txtDetailFoodItemMenuRes);
            txtPriceFoodItemMenuRes = itemView.findViewById(R.id.txtPriceFoodItemMenuRes);
        }
    }
}
