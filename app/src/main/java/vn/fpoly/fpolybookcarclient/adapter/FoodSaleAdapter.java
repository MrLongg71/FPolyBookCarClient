package vn.fpoly.fpolybookcarclient.adapter;

import android.content.Context;
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

public class FoodSaleAdapter extends RecyclerView.Adapter<FoodSaleAdapter.ViewHolder> {

    private List<FoodSale> arrFoodSale;
    private Context context;
    private int layout;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public FoodSaleAdapter(List<FoodSale> arrFoodSale, Context context, int layout) {
        this.arrFoodSale = arrFoodSale;
        this.context = context;
        this.layout = layout;
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
        FoodSale foodSale = arrFoodSale.get(position);
        holder.txtTitle.setText(foodSale.getTitle());
        holder.txtDetail.setText(foodSale.getDetail());
        holder.txtDistance.setText(foodSale.getDistance());
        storageReference.child("ImageNewsFoodSale").child(foodSale.getArrImage().get(0)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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
        TextView txtDistance,txtTitle,txtDetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView    = itemView.findViewById(R.id.imgFood1);
            txtDistance         = itemView.findViewById(R.id.txtDistanceFood1);
            txtDetail           = itemView.findViewById(R.id.txtDetailFood1);
            txtTitle            = itemView.findViewById(R.id.txtTitleFood1);
        }
    }
}
