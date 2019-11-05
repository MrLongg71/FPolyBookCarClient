package vn.fpoly.fpolybookcarclient.adapter.home;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.CakesBanner;
import vn.fpoly.fpolybookcarclient.R;

public class CakeBannerAdapter extends RecyclerView.Adapter<CakeBannerAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private List<CakesBanner> arrCake;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private boolean ischeck;
    public CakeBannerAdapter(Context context, int layout, List<CakesBanner> arrCake,boolean ischeck) {
        this.context = context;
        this.layout = layout;
        this.arrCake = arrCake;
        this.ischeck = ischeck;
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
        CakesBanner cakesBanner = arrCake.get(position);
        holder.txtTitle.setText(cakesBanner.getTitle());
        holder.txtDetail.setText(cakesBanner.getDetail());
        setImageCake(holder.imgBanner,cakesBanner);
        if (!ischeck) {
            if (position == 3) {
                int soHinh = arrCake.size() - 4;
                Toast.makeText(context, ""+soHinh, Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public int getItemCount() {
        if(!ischeck){
            return 2;
        }else {
            return arrCake.size();
        }
    }

    private void setImageCake(final ImageView imageCake, CakesBanner cakesBanner){
        storageReference.child("Imagenewscake").child(cakesBanner.getArrImage().get(0)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String URL = uri.toString();
                Glide.with(context).load(URL).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageCake);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView imgBanner;
        ToggleButton toggleLike;
        TextView txtTitle,txtDetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBanner   = itemView.findViewById(R.id.imgItemCake);
            txtDetail   = itemView.findViewById(R.id.txtDetaiCakeItem);
            txtTitle    = itemView.findViewById(R.id.txttitleCakeItem);
            toggleLike  = itemView.findViewById(R.id.toglelikeCake);
        }
    }
}
