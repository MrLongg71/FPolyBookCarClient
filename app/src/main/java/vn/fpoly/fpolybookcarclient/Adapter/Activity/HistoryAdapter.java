package vn.fpoly.fpolybookcarclient.Adapter.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.fpoly.fpolybookcarclient.Model.ObjectClass.HistoryBookCar;
import vn.fpoly.fpolybookcarclient.R;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<HistoryBookCar> arrHistory;
    private Context context;
    private int layout;

    public HistoryAdapter(List<HistoryBookCar> arrHistory, Context context, int layout) {
        this.arrHistory = arrHistory;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryBookCar historyBookCar = arrHistory.get(position);
        holder.imgItemHistory.setImageResource(historyBookCar.getImage());
        holder.txtTitleHistory.setText(historyBookCar.getPlace());
        holder.txtGenreHistory.setText(historyBookCar.getGenre());
        holder.txtDateHistory.setText(historyBookCar.getDate());
    }

    @Override
    public int getItemCount() {
        return arrHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgItemHistory;
        TextView txtTitleHistory,txtGenreHistory,txtDateHistory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItemHistory  = itemView.findViewById(R.id.imgIconCarHistory);
            txtTitleHistory = itemView.findViewById(R.id.txttitleHistoryItem);
            txtGenreHistory = itemView.findViewById(R.id.txtgenreHistoryItem);
            txtDateHistory  = itemView.findViewById(R.id.txtdateHistoryItem);
        }
    }
}
