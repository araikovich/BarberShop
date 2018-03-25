package araikovichinc.barbershop.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.callbacks.OnAddFeedbackFragmentsActionListener;

/**
 * Created by Tigran on 23.03.2018.
 */

public class AddFeedbackTitleFragment extends Fragment implements View.OnClickListener {

    Button backBtn, nextBtn;
    EditText titleText;
    OnAddFeedbackFragmentsActionListener callback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_write_feedback_title, container, false);
        backBtn = (Button)v.findViewById(R.id.add_feedback_title_back);
        nextBtn = (Button)v.findViewById(R.id.add_feedback_title_next);
        titleText = (EditText)v.findViewById(R.id.add_feedback_title_edit_text);

        backBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_feedback_title_back:
                callback.onAddTitleBackClick();
                break;
            case R.id.add_feedback_title_next:
                callback.onAddTitleNextClick(titleText.getText().toString());
                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (OnAddFeedbackFragmentsActionListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(callback!=null){
            callback = null;
        }
    }
}
