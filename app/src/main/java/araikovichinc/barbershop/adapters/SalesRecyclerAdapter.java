package araikovichinc.barbershop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.pojo.SaleModel;

/**
 * Created by Tigran on 14.03.2018.
 */

public class SalesRecyclerAdapter extends RecyclerView.Adapter<SalesRecyclerAdapter.SaleViewHolder> {

    ArrayList<SaleModel> sales;
    Context context;

    public SalesRecyclerAdapter(Context context){
        this.context = context;
        sales = new ArrayList<>();
    }

    @Override
    public SaleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_item, parent, false);
        return new SaleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SaleViewHolder holder, int position) {
        Glide.with(context).load(sales.get(position).getImage()).into(holder.photo);
        holder.title.setText(sales.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return sales.size();
    }

    public void setSales(ArrayList<SaleModel> sales){
        this.sales.clear();
        this.sales.addAll(sales);
        notifyDataSetChanged();
    }

    public class SaleViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;
        TextView title;

        public SaleViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView)itemView.findViewById(R.id.sale_image);
            title = (TextView)itemView.findViewById(R.id.sale_title);
        }
    }
}
