package araikovichinc.barbershop.repository.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.HairstyleCategoryDataSource;
import araikovichinc.barbershop.pojo.HairstyleCategoryCard;
import araikovichinc.barbershop.utils.DBHelper;

/**
 * Created by Tigran on 24.02.2018.
 */

public class HairstyleCategoryLocalDataSource implements HairstyleCategoryDataSource {

    DBHelper dbHelper;

    @Inject
    public HairstyleCategoryLocalDataSource(Context context){
        dbHelper = new DBHelper(context);
    }

    @Override
    public void getHairstyleCategories(int genderId, LoadCallBack callBack) {
        LoadCategoryCardsTask task = new LoadCategoryCardsTask(genderId, callBack);
        task.execute();
    }

    @Override
    public void saveCategories(ArrayList<HairstyleCategoryCard> cards) {
        SaveCategoryCardsTask task = new SaveCategoryCardsTask();
        task.execute(cards);
    }

    private class SaveCategoryCardsTask extends AsyncTask<ArrayList<HairstyleCategoryCard>, Void, Void> {

        @Override
        protected Void doInBackground(ArrayList<HairstyleCategoryCard>... params) {
            ContentValues contentValues = new ContentValues();
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            database.delete("category", null, null);
            for(int i = 0; i<params[0].size(); i++){
                contentValues.put("id", params[0].get(i).getId());
                contentValues.put("genderId", params[0].get(i).getSexId());
                contentValues.put("title", params[0].get(i).getTitle());
                contentValues.put("imageUrl", params[0].get(i).getImageUrl());
                database.insert("category", null, contentValues);
                contentValues.clear();
            }
            return null;
        }

    }

    private class LoadCategoryCardsTask extends AsyncTask<Void, Void, ArrayList<HairstyleCategoryCard>>{

        LoadCallBack callBack;
        int genderId;

        public LoadCategoryCardsTask(int genderId, LoadCallBack callBack){
            this.callBack = callBack;
            this.genderId = genderId;
        }

        @Override
        protected ArrayList<HairstyleCategoryCard> doInBackground(Void... params) {
            ArrayList<HairstyleCategoryCard> cards = new ArrayList<>();
            Cursor cursor = dbHelper.getReadableDatabase().query("category", null, null, null, null, null, null);
            while (cursor.moveToNext()){
                if(cursor.getInt(cursor.getColumnIndex("genderId")) != genderId){
                    return null;
                }
                HairstyleCategoryCard card = new HairstyleCategoryCard();
                card.setSexId(cursor.getInt(cursor.getColumnIndex("genderId")));
                card.setSexId(cursor.getInt(cursor.getColumnIndex("id")));
                card.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                card.setImageUrl(cursor.getString(cursor.getColumnIndex("imageUrl")));
                cards.add(card);
            }
            cursor.close();
            return cards;
        }

        @Override
        protected void onPostExecute(ArrayList<HairstyleCategoryCard> cards) {
            super.onPostExecute(cards);
            if(cards!= null && cards.size() > 0)
                callBack.onLoadSuccess(cards);
            else
                callBack.onFail("Null table");
        }
    }
}
