package araikovichinc.barbershop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.pojo.TimeModel;

/**
 * Created by Tigran on 05.03.2018.
 */

public class TimesRecyclerAdapter extends RecyclerView.Adapter<TimesRecyclerAdapter.TimeCardViewHolder> {

    ArrayList<TimeModel> times;
    RecyclerView recyclerView;

    public TimesRecyclerAdapter(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
        this.times = new ArrayList<>();
    }

    @Override
    public TimeCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_card_item, parent,false);
        return new TimeCardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TimeCardViewHolder holder, int position) {
        holder.dateText.setText(times.get(position).getDay()
                                + "."+times.get(position).getMonth()
                                + "."+times.get(position).getYear());
        holder.timeFromText.setText(times.get(position).getTimeFromHours()
                                +":" + times.get(position).getTimeFromMin());
        holder.timeToText.setText(times.get(position).getTimeToHours()
                +":" + times.get(position).getTimeToMin());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TimeCardViewHolder extends RecyclerView.ViewHolder {

        TextView timeFromText, timeToText, dateText;

        public TimeCardViewHolder(View itemView) {
            super(itemView);

            timeFromText = (TextView)itemView.findViewById(R.id.time_from_text);
            timeToText = (TextView)itemView.findViewById(R.id.time_to_text);
            dateText = (TextView)itemView.findViewById(R.id.date_text);
        }
    }
}
