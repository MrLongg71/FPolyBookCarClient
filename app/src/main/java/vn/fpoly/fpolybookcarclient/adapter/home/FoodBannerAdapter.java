package vn.fpoly.fpolybookcarclient.adapter.home;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.FoodBanner;
import vn.fpoly.fpolybookcarclient.R;

public class FoodBannerAdapter extends RecyclerView.Adapter<FoodBannerAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private List<FoodBanner> arrFood;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public FoodBannerAdapter(Context context, int layout, List<FoodBanner> arrFood) {
        this.context = context;
        this.layout = layout;
        this.arrFood = arrFood;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodBanner foodBanner = arrFood.get(position);
        holder.txtDetail.setText(foodBanner.getDetail());
        holder.txtTitle.setText(foodBanner.getTitle());
        setImage(holder.imgBanner,foodBanner);

    }
    private void setImage(final RoundedImageView image, FoodBanner foodBanner){
        storageReference.child("Imagenewsfood").child(foodBanner.getArrImage().get(0)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String URL = uri.toString();
                Uri uri1 = uri.parse(URL);
                Glide.with(context).load(uri1).into(image);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView imgBanner;
        ToggleButton toggleButtonLike;
        TextView txtTitle, txtDetail, txtbooknow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBanner            = itemView.findViewById(R.id.imgItemFood);
            toggleButtonLike     = itemView.findViewById(R.id.toglelikeFood);
            txtTitle             = itemView.findViewById(R.id.txttitleFoodItem);
            txtDetail            = itemView.findViewById(R.id.txtDetaiFoodItem);
            txtbooknow          = itemView.findViewById(R.id.txtBookNowFood);
        }
    }
}
