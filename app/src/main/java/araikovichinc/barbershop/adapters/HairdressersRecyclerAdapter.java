package araikovichinc.barbershop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.R;
import araikovichinc.barbershop.pojo.HairdresserModel;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Tigran on 02.03.2018.
 */

public class HairdressersRecyclerAdapter extends RecyclerView.Adapter<HairdressersRecyclerAdapter.HairdresserViewHolder> {

    private ArrayList<HairdresserModel> hairdressers;
    private Context context;
    private OnHairdresserClick onClick;
    private int previousPosition;
    private RecyclerView recyclerView;

    public interface OnHairdresserClick{
        void onClick(int hairdresserId, String name, String photo);
        void onClickSamePosition();
    }

    public HairdressersRecyclerAdapter(Context context, RecyclerView recyclerView){
        this.context = context;
        hairdressers = new ArrayList<>();
        previousPosition = -1;
        this.recyclerView = recyclerView;
    }


    @Override
    public HairdresserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hairdresser_item, parent, false);
        return  new HairdresserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HairdresserViewHolder holder, int position) {
        holder.name.setText(hairdressers.get(position).getName());
        Glide.with(context).load(hairdressers.get(position).getPhoto()).into(holder.photo);
        holder.relativeLayout.setBackgroundColor(MyApp.getAppContext().getResources().getColor(R.color.colorBlack));
    }

    @Override
    public int getItemCount() {
        return hairdressers.size();
    }

    public void setHairdressers(ArrayList<HairdresserModel> hairdressers){
        this.hairdressers.clear();
        this.previousPosition = -1;
        this.hairdressers.addAll(hairdressers);
        notifyDataSetChanged();
    }



    public void setOnClick(OnHairdresserClick click){
        this.onClick = click;
    }

    public class HairdresserViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        CircleImageView photo;
        RelativeLayout relativeLayout;

        public HairdresserViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.hairdresser_name);
            photo = (CircleImageView) itemView.findViewById(R.id.hairdresser_photo);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.hairdresser_card);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition() == previousPosition){
                        onClick.onClickSamePosition();
                    }else {
                        View view;
                        RelativeLayout rl;
                        if(previousPosition!=-1) {
                            view = recyclerView.findViewHolderForAdapterPosition(previousPosition).itemView;
                            rl = (RelativeLayout) view.findViewById(R.id.hairdresser_card);
                            rl.setBackgroundColor(MyApp.getAppContext().getResources().getColor(R.color.colorBlack));
                        }
                        view = recyclerView.findViewHolderForAdapterPosition(getAdapterPosition()).itemView;
                        rl = (RelativeLayout) view.findViewById(R.id.hairdresser_card);
                        rl.setBackgroundDrawable(MyApp.getAppContext().getResources().getDrawable(R.drawable.pink_black_gradient));
                        previousPosition = getAdapterPosition();
                        onClick.onClick(hairdressers.get(getAdapterPosition()).getId(), hairdressers.get(getAdapterPosition()).getName(),
                                hairdressers.get(getAdapterPosition()).getPhoto());
                    }
                }
            });
        }
    }
}
