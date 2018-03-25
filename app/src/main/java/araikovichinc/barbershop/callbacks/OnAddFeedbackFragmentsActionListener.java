package araikovichinc.barbershop.callbacks;

/**
 * Created by Tigran on 12.02.2018.
 */

public interface OnAddFeedbackFragmentsActionListener {
    void onAddRatingNextClick(float rating);
    void onAddTitleBackClick();
    void onAddTitleNextClick(String title);
    void onAddTextBackClick();
    void onAddTextDoneClick(String text, int day, int month, int year);
}
