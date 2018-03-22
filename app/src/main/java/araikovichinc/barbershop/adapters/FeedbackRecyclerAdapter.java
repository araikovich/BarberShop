package araikovichinc.barbershop.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.pojo.FeedbackModel;

/**
 * Created by Tigran on 17.03.2018.
 */

public class FeedbackRecyclerAdapter extends RecyclerView.Adapter<FeedbackRecyclerAdapter.FeedbackViewHolder> {

    ArrayList<FeedbackModel> feedback;

    public FeedbackRecyclerAdapter(){
        feedback = new ArrayList<>();
    }

    @Override
    public FeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_item, parent, false);
        return new FeedbackViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FeedbackViewHolder holder, int position) {
        holder.ratingBar.setRating(feedback.get(position).getRating());
        holder.title.setText(feedback.get(position).getTitle());
        holder.text.setText(feedback.get(position).getText());
        Log.d("MyLogs", feedback.get(position).getYear() + "");
        Date date = new Date(feedback.get(position).getYear(),feedback.get(position).getMonth(),feedback.get(position).getDay(), 0, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM",Locale.US);
        holder.date.setText(simpleDateFormat.format(date) + "."+ feedback.get(position).getYear());
    }

    @Override
    public int getItemCount() {
        return feedback.size();
    }

    public void setFeedback(ArrayList<FeedbackModel> feedback){
        this.feedback.clear();
        this.feedback.addAll(feedback);
        notifyDataSetChanged();
    }

    public class FeedbackViewHolder extends RecyclerView.ViewHolder {

        RatingBar ratingBar;
        TextView date;
        TextView title;
        TextView text;

        public FeedbackViewHolder(View itemView) {
            super(itemView);
            ratingBar = (RatingBar)itemView.findViewById(R.id.feedback_rating);
            date = (TextView)itemView.findViewById(R.id.feedback_date);
            title = (TextView)itemView.findViewById(R.id.feedback_title);
            text = (TextView)itemView.findViewById(R.id.feedback_text);
        }
    }
}
