package vn.fpoly.fpolybookcarclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodSale;
import vn.fpoly.fpolybookcarclient.model.objectClass.PopularFood;
import vn.fpoly.fpolybookcarclient.view.Activity.MainActivity;

public class PopularFoodAdapter extends RecyclerView.Adapter<PopularFoodAdapter.ViewHolder> {
    private List<PopularFood> arrFoodSale;
    private Context context;
    private int layout;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public PopularFoodAdapter(List<PopularFood> arrFoodSale, Context context, int layout) {
        this.arrFoodSale = arrFoodSale;
        this.context = context;
        this.layout = layout;

    }

    @NonNull
    @Override
    public PopularFoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PopularFoodAdapter.ViewHolder holder, int position) {
        PopularFood popularFood = arrFoodSale.get(position);
        holder.txtTitle.setText(popularFood.getTitle());
        storageReference.child("Imagepoppularcuisines").child(popularFood.getArrImage().get(0)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String URL = uri.toString();
                Glide.with(context).load(URL).into(holder.roundedImageView);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrFoodSale.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView roundedImageView;
        TextView txtTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView    = itemView.findViewById(R.id.imgFood4);
            txtTitle            = itemView.findViewById(R.id.txtFood4);
        }
    }
}
