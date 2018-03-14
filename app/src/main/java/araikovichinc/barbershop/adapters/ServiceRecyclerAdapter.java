package araikovichinc.barbershop.adapters;

import android.bluetooth.BluetoothClass;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import araikovichinc.barbershop.MyApp;
import araikovichinc.barbershop.R;
import araikovichinc.barbershop.pojo.ServiceModel;

/**
 * Created by Tigran on 03.03.2018.
 */

public class ServiceRecyclerAdapter extends RecyclerView.Adapter<ServiceRecyclerAdapter.ServiceViewHolder> {

    private ArrayList<ServiceModel> services;
    private ArrayList<Integer> previous;
    private OnServiceClickListener onServiceClickListener;

    public ServiceRecyclerAdapter(){
        previous = new ArrayList<>();
        services = new ArrayList<>();
    }

    public interface OnServiceClickListener{
        void onServiceClick(ServiceModel service);
        void onAgainClick(int serviceId);
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item, parent, false);
        return new ServiceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ServiceViewHolder holder, int position) {
        Drawable drawable = MyApp.getAppContext().getResources().getDrawable(R.drawable.hrivnia);
        drawable.setColorFilter(MyApp.getAppContext().getResources().getColor(R.color.colorWhite), PorterDuff.Mode.MULTIPLY);
        holder.title.setText(services.get(position).getTitle());
        holder.price.setText(Integer.toString(services.get(position).getPrice()));
        holder.card.setBackgroundColor(MyApp.getAppContext().getResources().getColor(R.color.colorBlack));
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public void setOnServiceClickListener(OnServiceClickListener listener){
        this.onServiceClickListener = listener;
    }

    public void setServices(ArrayList<ServiceModel> services){
        this.previous.clear();
        this.services.clear();
        this.services.addAll(services);
        Log.d("MyLogs", "services set  " + services.get(0).getPrice());
        notifyDataSetChanged();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {

        TextView title, price;
        RelativeLayout card;

        public ServiceViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.service_title);
            price = (TextView) itemView.findViewById(R.id.service_price);
            card = (RelativeLayout) itemView.findViewById(R.id.service_card);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isPressed = false;
                    for (int i = 0; i<previous.size(); i++) {
                        int pressed = previous.get(i);
                        if(pressed == getAdapterPosition()) {
                            isPressed = true;
                            previous.remove(i);
                            break;
                        }
                    }
                    if(isPressed){
                        card.setBackgroundColor(MyApp.getAppContext().getResources().getColor(R.color.colorBlack));
                        onServiceClickListener.onAgainClick(services.get(getAdapterPosition()).getId());
                    }else {
                        card.setBackground(MyApp.getAppContext().getResources().getDrawable(R.drawable.pink_black_gradient));
                        previous.add(getAdapterPosition());
                        onServiceClickListener.onServiceClick(services.get(getAdapterPosition()));
                    }
                }
            });
        }
    }
}
