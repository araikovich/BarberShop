package araikovichinc.barbershop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.R;
import araikovichinc.barbershop.pojo.Reservation;

/**
 * Created by Tigran on 27.03.2018.
 */

public class MyBooksRecyclerAdapter extends RecyclerView.Adapter<MyBooksRecyclerAdapter.MyBookViewHolder> {


    public interface OnMyReservationClickListener{
        void onReservationItemClick(String reservationId);
    }

    private OnMyReservationClickListener clickListener;

    private ArrayList<Reservation> reservations;
    private Context context;

    public MyBooksRecyclerAdapter(Context context){
        reservations = new ArrayList<>();
        this.context = context;
    }

    @Override
    public MyBookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item, parent, false);
        return new MyBookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyBookViewHolder holder, int position) {
        Date date = new Date(0,0,0,reservations.get(position).getTimeFromHour(), reservations.get(position).getTimeFromMin());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.GERMAN);
        holder.timeFrom.setText(simpleDateFormat.format(date));
        date = new Date(0,0,0,reservations.get(position).getTimeToHour(), reservations.get(position).getTimeToMin());
        holder.timeTo.setText(simpleDateFormat.format(date));
        holder.date.setText(reservations.get(position).getDay()+"."+reservations.get(position).getMonth()+"."+reservations.get(position).getYear());
        Log.d("MyLogs", reservations.get(position).getReservationId() + "");
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public void setReservations(ArrayList<Reservation> reservations){
        this.reservations.clear();
        this.reservations.addAll(reservations);
        notifyDataSetChanged();
    }

    public void setClickListener(OnMyReservationClickListener onMyReservationClickListener){
        this.clickListener = onMyReservationClickListener;
    }

    public class MyBookViewHolder extends RecyclerView.ViewHolder {

        TextView timeFrom, timeTo, date;

        public MyBookViewHolder(View itemView) {
            super(itemView);
            timeFrom = (TextView)itemView.findViewById(R.id.my_reserv_time_from);
            timeTo = (TextView)itemView.findViewById(R.id.my_reserv_time_to);
            date = (TextView)itemView.findViewById(R.id.my_reserv_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onReservationItemClick(reservations.get(getAdapterPosition()).getReservationId());
                }
            });
        }
    }
}
