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
import araikovichinc.barbershop.pojo.GenderCard;

/**
 * Created by Tigran on 12.02.2018.
 */

public class GenderCategoriesRecyclerAdapter extends RecyclerView.Adapter<GenderCategoriesRecyclerAdapter.GenderViewHolder> {

    private Context context;
    private ArrayList<GenderCard> cards;
    private CategoryOnClickListener listener;
    private boolean loaded = false;


    public GenderCategoriesRecyclerAdapter(Context context){
        this.context = context;
        cards = new ArrayList<>();
    }

    @Override
    public GenderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
        return new GenderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GenderViewHolder holder, int position) {
        Glide.with(context).load(cards.get(position).getImageUrl()).into(holder.photo);
        holder.title.setText(cards.get(position).getTitle());
        holder.genderId = cards.get(position).getSexId();
        holder.cardPosition = position;
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void setItems(ArrayList<GenderCard> cards){
        loaded = true;
        this.cards.clear();
        this.cards.addAll(cards);
        this.notifyDataSetChanged();
    }

    public void setOnClickListener(CategoryOnClickListener listener){
        this.listener = listener;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public boolean isLoaded(){
        return loaded;
    }

    public class GenderViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;
        TextView title;
        int genderId, cardPosition;

        public GenderViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView)itemView.findViewById(R.id.category_photo);
            title = (TextView) itemView.findViewById(R.id.category_title);
            photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(genderId, title.getText().toString());
                }
            });
        }
    }
}
