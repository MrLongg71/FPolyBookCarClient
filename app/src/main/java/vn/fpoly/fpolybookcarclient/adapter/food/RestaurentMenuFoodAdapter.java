package vn.fpoly.fpolybookcarclient.adapter.food;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class RestaurentMenuFoodAdapter extends RecyclerView.Adapter<RestaurentMenuFoodAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private ArrayList<FoodMenu> foodMenuArrayList;
    final StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public RestaurentMenuFoodAdapter(Context context, int layout, ArrayList<FoodMenu> foodMenuArrayList) {
        this.context = context;
        this.layout = layout;
        this.foodMenuArrayList = foodMenuArrayList;
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

                    }
                });

        holder.layoutItemMenuFoodRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //interface

            }
        });
    }

    @Override
    public int getItemCount() {
        return foodMenuArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutItemMenuFoodRes;
        RoundedImageView imgFoodItemMenuRes;
        TextView txtNameFoodItemMenuRes,txtDetailFoodItemMenuRes,txtPriceFoodItemMenuRes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItemMenuFoodRes = itemView.findViewById(R.id.layoutItemMenuFoodRes);
            imgFoodItemMenuRes = itemView.findViewById(R.id.imgFoodItemMenuRes);
            txtNameFoodItemMenuRes = itemView.findViewById(R.id.txtNameFoodItemMenuRes);
            txtDetailFoodItemMenuRes = itemView.findViewById(R.id.txtDetailFoodItemMenuRes);
            txtPriceFoodItemMenuRes = itemView.findViewById(R.id.txtPriceFoodItemMenuRes);
        }
    }
}
