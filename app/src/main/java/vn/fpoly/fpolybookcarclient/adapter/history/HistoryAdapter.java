package vn.fpoly.fpolybookcarclient.adapter.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.model.objectClass.HistoryBookCar;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private List<HistoryBookCar>arrHistory;

    public HistoryAdapter(Context context, int layout, List<HistoryBookCar> arrHistory) {
        this.context = context;
        this.layout = layout;
        this.arrHistory = arrHistory;
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

    }

    @Override
    public int getItemCount() {
        return arrHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtDate,txttitle,txtdetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView       = itemView.findViewById(R.id.imgIconCarHistory);;
            txtDate         = itemView.findViewById(R.id.txttitleHistoryItem);
            txttitle       = itemView.findViewById(R.id.txttitleHistoryItem);
            txtdetail       = itemView.findViewById(R.id.txtgenreHistoryItem);
        }
    }
}
