package araikovichinc.barbershop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.pojo.HairstyleDetailCard;

/**
 * Created by Tigran on 16.02.2018.
 */

public class HairstyleDetailRecyclerAdapter extends RecyclerView.Adapter<HairstyleDetailRecyclerAdapter.DetailViewHolder> {

    private ArrayList<HairstyleDetailCard> cards = new ArrayList<>();
    private Context context;
    private boolean isLoaded = false;

    public HairstyleDetailRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_card, parent, false);
        return new DetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        Glide.with(context).load(cards.get(position).getImageUrl()).into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setCards(ArrayList<HairstyleDetailCard> cards){
        isLoaded = true;
        this.cards.clear();
        this.cards.addAll(cards);
        notifyDataSetChanged();
    }

    public void setContext(Context context){
        this.context = context;
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;

        public DetailViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView)itemView.findViewById(R.id.detail_photo);
        }
    }
}
