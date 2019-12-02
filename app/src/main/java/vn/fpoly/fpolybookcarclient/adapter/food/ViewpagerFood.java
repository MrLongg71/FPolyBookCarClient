package vn.fpoly.fpolybookcarclient.adapter.food;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodPager;

public class ViewpagerFood extends PagerAdapter {
    private Context context;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private List<FoodPager>arrFood;

    public ViewpagerFood(Context context, List<FoodPager> arrFood) {
        this.context = context;
        this.arrFood = arrFood;
    }

    @Override
    public int getCount() {
        return arrFood.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_row_viewpagerfood, container, false);
        final RoundedImageView imageView = view.findViewById(R.id.imgFood);
        FoodPager food = arrFood.get(position);
        storageReference.child("Imagefood").child(food.getArrImage().get(0)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String URL = uri.toString();
                Glide.with(context).load(URL).placeholder(R.drawable.error_center_x).centerCrop().into(imageView);
            }
        });
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view,0);
        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view1 = (View) object;
        viewPager.removeView(view1);
    }

}
