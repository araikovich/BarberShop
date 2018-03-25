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

import java.util.Calendar;
import java.util.Date;

import araikovichinc.barbershop.R;
import araikovichinc.barbershop.callbacks.OnAddFeedbackFragmentsActionListener;

/**
 * Created by Tigran on 23.03.2018.
 */

public class AddFeedbackTextFragment extends Fragment implements View.OnClickListener{

    Button backBtn, doneBtn;
    EditText textEditText;
    OnAddFeedbackFragmentsActionListener callback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_feedback_text, container, false);
        backBtn = (Button)v.findViewById(R.id.add_feedback_text_btn_back);
        doneBtn = (Button)v.findViewById(R.id.add_feedback_btn_done);
        textEditText = (EditText)v.findViewById(R.id.add_feedback_text_edit_text);

        backBtn.setOnClickListener(this);
        doneBtn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (OnAddFeedbackFragmentsActionListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(callback!=null)
            callback = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_feedback_text_btn_back:
                callback.onAddTextBackClick();
                break;
            case R.id.add_feedback_btn_done:
                Date date = Calendar.getInstance().getTime();
                callback.onAddTextDoneClick(textEditText.getText().toString(), date.getDay(), date.getMonth(), date.getYear());
                break;
        }
    }
}
