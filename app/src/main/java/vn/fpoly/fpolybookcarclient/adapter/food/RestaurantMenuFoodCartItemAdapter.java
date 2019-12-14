package vn.fpoly.fpolybookcarclient.adapter.food;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.model.objectClass.BillFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodMenu;
import vn.fpoly.fpolybookcarclient.view.food.menu_restaurant.CallbackRestaurantMenuFoodCartItem;

public class RestaurantMenuFoodCartItemAdapter extends RecyclerView.Adapter<RestaurantMenuFoodCartItemAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private ArrayList<FoodMenu> foodMenuArrayList;
    private CallbackRestaurantMenuFoodCartItem cartItem;
    private ArrayList<BillFood> billFoodArrayList;
    public RestaurantMenuFoodCartItemAdapter(Context context, int layout, ArrayList<FoodMenu> foodMenuArrayList,ArrayList<BillFood> billFoodArrayList,CallbackRestaurantMenuFoodCartItem cartItem) {
        this.context = context;
        this.layout = layout;
        this.foodMenuArrayList = foodMenuArrayList;
        this.billFoodArrayList = billFoodArrayList;
        this.cartItem = cartItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final FoodMenu foodMenu = foodMenuArrayList.get(position);
        Glide.with(context).load(foodMenu.getImage()).into(holder.imgFoodItemMenuRes);
        holder.txtNameFoodItemMenuRes.setText(foodMenu.getName());
        holder.txtPriceFoodItemMenuRes.setText(foodMenu.getPrice());
        holder.txtNumberCart.setText(""+billFoodArrayList.get(position).getAmountBuy());
//        holder.txtNumberCart.setText(String.format("%d", billFoodArrayList.get(position).getAmountBuy()));

        setAmoutCart(holder,position);


    }

    private void setAmoutCart(final ViewHolder holder, final int position) {
        holder.txtIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItem.initIncreasing(position,holder.txtNumberCart);
            }
        });
        holder.txtReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItem.initReduction(position,holder.txtNumberCart);
            }
        });

        holder.imgDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billFoodArrayList.remove(position);
                notifyDataSetChanged();
            }
        });

    }


    @Override
    public int getItemCount() {
        return foodMenuArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView imgFoodItemMenuRes;
        TextView txtNameFoodItemMenuRes,txtPriceFoodItemMenuRes,txtNumberCart,txtIncrease,txtReduce;
        ImageView imgDeleteItem;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoodItemMenuRes = itemView.findViewById(R.id.imgFoodItemMenuResCart);
            txtNameFoodItemMenuRes = itemView.findViewById(R.id.txtNameFoodItemMenuResCart);
            txtPriceFoodItemMenuRes = itemView.findViewById(R.id.txtPriceFoodItemMenuResCart);
            txtNumberCart = itemView.findViewById(R.id.numberCart);
            txtIncrease =  itemView.findViewById(R.id.increase);
            txtReduce = itemView.findViewById(R.id.reduce);
            imgDeleteItem = itemView.findViewById(R.id.imgDeleteItem);
        }
    }
}
