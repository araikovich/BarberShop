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
import araikovichinc.barbershop.callbacks.CategoryOnClickListener;
import araikovichinc.barbershop.pojo.HairstyleCategoryCard;
import araikovichinc.barbershop.pojo.HairstyleDetailCard;

/**
 * Created by Tigran on 14.02.2018.
 */

public class HairstyleCategoryRecyclerAdapter extends RecyclerView.Adapter<HairstyleCategoryRecyclerAdapter.HairstyleCategoryViewHolder> {

    Context context;
    ArrayList<HairstyleCategoryCard> cards = new ArrayList<>();
    CategoryOnClickListener listener;
    private boolean loaded = false;

    public HairstyleCategoryRecyclerAdapter(Context context){
        this.context = context;
    }

    @Override
    public HairstyleCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
        return new HairstyleCategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HairstyleCategoryViewHolder holder, int position) {
        Glide.with(context).load(cards.get(position).getImageUrl()).into(holder.photo);
        holder.title.setText(cards.get(position).getTitle());
        holder.hairstyleId = cards.get(position).getId();
        holder.cardPosition = position;
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void setCards(ArrayList<HairstyleCategoryCard> cards){
        loaded = true;
        this.cards.clear();
        this.cards.addAll(cards);
        notifyDataSetChanged();
    }

    public void setContext(Context context){
        this.context = context;
    }

    public boolean isLoaded(){
        return loaded;
    }

    public void setOnClickListener(CategoryOnClickListener listener){
        this.listener = listener;
    }

    public class HairstyleCategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;
        TextView title;
        int hairstyleId, cardPosition;

        public HairstyleCategoryViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView)itemView.findViewById(R.id.category_photo);
            title = (TextView) itemView.findViewById(R.id.category_title);
            photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(hairstyleId, title.getText().toString());
                }
            });

        }
    }
}
