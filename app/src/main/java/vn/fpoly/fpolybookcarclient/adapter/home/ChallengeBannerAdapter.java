package vn.fpoly.fpolybookcarclient.adapter.home;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.ObjectKey;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.ChallengeBanner;
import vn.fpoly.fpolybookcarclient.R;

public class ChallengeBannerAdapter extends RecyclerView.Adapter<ChallengeBannerAdapter.ViewHolder>{
    private List<ChallengeBanner> arrBanner;
    private Context context;
    private int layout;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public ChallengeBannerAdapter(List<ChallengeBanner> arrBanner, Context context, int layout) {
        this.arrBanner = arrBanner;
        this.context = context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ChallengeBanner banner = arrBanner.get(position);
        storageReference.child("Imagenewschallenge").child(banner.getArrImage().get(position)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String URL = uri.toString();
                Uri uri1 = uri.parse(URL);
                Glide.with(context).load(uri1).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.DATA).into(holder.imgBanner);
//                Picasso.get().load(uri1).into(holder.imgBanner.setTa);
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrBanner.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView imgBanner;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBanner = itemView.findViewById(R.id.imgRowBanner);
            this.setIsRecyclable(true);
        }
    }
}
