package araikovichinc.barbershop.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.callbacks.OnAddFeedbackFragmentsActionListener;

/**
 * Created by Tigran on 22.03.2018.
 */

public class RatingBarFragment extends Fragment {

    Button nextBtn;
    RatingBar ratingBar;

    OnAddFeedbackFragmentsActionListener callback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rating_bar, container, false);
        nextBtn = (Button)v.findViewById(R.id.add_feedback_rating_next);
        ratingBar = (RatingBar)v.findViewById(R.id.add_feedback_rating_bar);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onAddRatingNextClick(ratingBar.getRating());
            }
        });
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(callback == null){
            callback = (OnAddFeedbackFragmentsActionListener) activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (callback!=null){
            callback = null;
        }
    }
}
