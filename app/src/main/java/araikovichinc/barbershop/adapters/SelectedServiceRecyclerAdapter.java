package araikovichinc.barbershop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.pojo.ServiceModel;

/**
 * Created by Tigran on 04.03.2018.
 */

public class SelectedServiceRecyclerAdapter extends RecyclerView.Adapter<SelectedServiceRecyclerAdapter.SelectedServiceViewHolder> {

    private ArrayList<ServiceModel> selectedServices;

    public SelectedServiceRecyclerAdapter(){
        selectedServices = new ArrayList<>();
    }

    @Override
    public SelectedServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_selected_item, parent, false);
        return new SelectedServiceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SelectedServiceViewHolder holder, int position) {
        holder.selectedServicePrice.setText(Integer.toString(selectedServices.get(position).getPrice()));
        holder.selectedServiceTitle.setText(selectedServices.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return selectedServices.size();
    }

    public void setSelectedService(ServiceModel service){
        this.selectedServices.add(service);
        notifyDataSetChanged();
    }

    public void deleteService(int serviceId){
        for(int i = 0; i < selectedServices.size(); i++){
            if(selectedServices.get(i).getId() == serviceId){
                selectedServices.remove(i);
                notifyDataSetChanged();
                break;
            }
        }
    }

    public void clearList(){
        selectedServices.clear();

        notifyDataSetChanged();
    }

    public class SelectedServiceViewHolder extends RecyclerView.ViewHolder {

        TextView selectedServiceTitle, selectedServicePrice;

        public SelectedServiceViewHolder(View itemView) {
            super(itemView);
            selectedServiceTitle = (TextView)itemView.findViewById(R.id.service_item_title);
            selectedServicePrice = (TextView)itemView.findViewById(R.id.service_item_price);
        }
    }
}
