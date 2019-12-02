package vn.fpoly.fpolybookcarclient.adapter.home;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.internal.ar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.net.URL;
import java.util.List;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.model.objectClass.News;

public class NewsBannerAdapter extends RecyclerView.Adapter<NewsBannerAdapter.ViewHolder> {
    private Context context;
    private List<News> arrCake;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private boolean ischeck;


    public NewsBannerAdapter(Context context, List<News> arrCake, boolean ischeck) {
        this.context = context;
        this.arrCake = arrCake;
        this.ischeck = ischeck;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_cake_banner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News cakesBanner = arrCake.get(position);
        holder.txtTitle.setText(cakesBanner.getTitle());
        holder.txtDetail.setText(cakesBanner.getDetail());
        setImageCake(holder.imgBanner, cakesBanner);
    }

    @Override
    public int getItemCount() {

        return arrCake.size();

    }

    private void setImageCake(final ImageView imageCake, final News cakesBanner) {
        storageReference.child("Imagenewscake").child(cakesBanner.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String URL = uri.toString();
                Glide.with(context).load(URL).into(imageCake);
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
