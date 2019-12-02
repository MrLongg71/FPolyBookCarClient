package vn.fpoly.fpolybookcarclient.adapter.food;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodCategories;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private List<FoodCategories> arrFoodCategoris;
    private int layout;
    private Context context;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public CategoriesAdapter(List<FoodCategories> arrFoodCategoris, int layout, Context context) {
        this.arrFoodCategoris = arrFoodCategoris;
        this.layout = layout;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoriesAdapter.ViewHolder holder, int position) {
        FoodCategories foodCategories = arrFoodCategoris.get(position);
        holder.txtTitle.setText(foodCategories.getTitle());
        storageReference.child("Imageexplorecategories").child(foodCategories.getArrImage().get(0)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String URL = uri.toString();
                Glide.with(context).load(URL).into(holder.imageView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrFoodCategoris.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView   = itemView.findViewById(R.id.imgCategories);
            txtTitle    = itemView.findViewById(R.id.txtTitleCategories);
        }
    }
}
