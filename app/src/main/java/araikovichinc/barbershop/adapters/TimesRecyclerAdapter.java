package araikovichinc.barbershop.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.R;
import araikovichinc.barbershop.pojo.TimeModel;

/**
 * Created by Tigran on 05.03.2018.
 */

public class TimesRecyclerAdapter extends RecyclerView.Adapter<TimesRecyclerAdapter.TimeCardViewHolder> {

    private ArrayList<TimeModel> times;
    private int pressedPos;
    private int curPos;
    private OnTimeClickListener onTimeClickListener;

    public interface OnTimeClickListener{
        void onTimeClick(int timeFromHour, int timeFromMin, int timeToHour, int timeToMin);
    }

    public TimesRecyclerAdapter(){
        this.times = new ArrayList<>();
        pressedPos = -1;
    }

    @Override
    public TimeCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_card_item, parent,false);
        return new TimeCardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TimeCardViewHolder holder, int position) {
        holder.relativeLayout.setBackgroundColor(MyApp.getAppContext().getResources().getColor(R.color.colorBlack));
        Date date = new Date(0,0,0,times.get(position).getTimeFromHours(), times.get(position).getTimeFromMin());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.GERMAN);
        holder.timeFromText.setText(simpleDateFormat.format(date));
        date = new Date(0,0,0,times.get(position).getTimeToHours(), times.get(position).getTimeToMin());
        holder.timeToText.setText(simpleDateFormat.format(date));
    }

    public void setTimes(ArrayList<TimeModel> times){
            pressedPos = -1;
            this.times.clear();
            this.times.addAll(times);
            notifyDataSetChanged();
    }

    public void setOnTimeClickListener(OnTimeClickListener onTimeClickListener){
        this.onTimeClickListener = onTimeClickListener;
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    public class TimeCardViewHolder extends RecyclerView.ViewHolder {

        TextView timeFromText, timeToText;
        RelativeLayout relativeLayout;

        public TimeCardViewHolder(View itemView) {
            super(itemView);

            timeFromText = (TextView)itemView.findViewById(R.id.time_from_text);
            timeToText = (TextView)itemView.findViewById(R.id.time_to_text);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.time_relative_layout);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() == pressedPos) {
                        onTimeClickListener.onTimeClick(times.get(getAdapterPosition()).getTimeFromHours(),
                                times.get(getAdapterPosition()).getTimeFromMin(),
                                times.get(getAdapterPosition()).getTimeToHours(),
                                times.get(getAdapterPosition()).getTimeToMin());
                    } else {
                        if(pressedPos!=-1){
                            notifyItemChanged(pressedPos);
                        }else {
                            pressedPos = getAdapterPosition();
                        }
                        relativeLayout.setBackground(MyApp.getAppContext().getResources().getDrawable(R.drawable.pink_black_gradient));
                        pressedPos = getAdapterPosition();
                        onTimeClickListener.onTimeClick(times.get(getAdapterPosition()).getTimeFromHours(),
                                times.get(getAdapterPosition()).getTimeFromMin(),
                                times.get(getAdapterPosition()).getTimeToHours(),
                                times.get(getAdapterPosition()).getTimeToMin());
                    }
                }
            });
        }
    }
}
