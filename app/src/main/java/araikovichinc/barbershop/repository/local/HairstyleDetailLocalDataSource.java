package araikovichinc.barbershop.repository.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.HairstyleDetailDataSource;
import araikovichinc.barbershop.pojo.HairstyleDetailCard;
import araikovichinc.barbershop.utils.DBHelper;

/**
 * Created by Tigran on 24.02.2018.
 */

public class HairstyleDetailLocalDataSource implements HairstyleDetailDataSource {

    private DBHelper dbHelper;

    @Inject
    public HairstyleDetailLocalDataSource(Context context){
        dbHelper = new DBHelper(context);
    }


    @Override
    public void loadHairstyleDetail(int hairstyleId, LoadCallBack callBack) {
        LoadDetailCardsTask task = new LoadDetailCardsTask(hairstyleId, callBack);
        task.execute();
    }

    @Override
    public void saveHairstyleDetail(ArrayList<HairstyleDetailCard> cards) {
        SaveDetailCardsTask task = new SaveDetailCardsTask();
        task.execute(cards);
    }

    private class SaveDetailCardsTask extends AsyncTask<ArrayList<HairstyleDetailCard>, Void, Void> {

        @Override
        protected Void doInBackground(ArrayList<HairstyleDetailCard>... params) {
            ContentValues contentValues = new ContentValues();
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            database.delete("detail", null, null);
            for(int i = 0; i<params[0].size(); i++){
                contentValues.put("hairstyleId", params[0].get(i).getHairstyleId());
                contentValues.put("imageUrl", params[0].get(i).getImageUrl());
                database.insert("detail", null, contentValues);
                contentValues.clear();
            }
            return null;
        }

    }

    private class LoadDetailCardsTask extends AsyncTask<Void, Void, ArrayList<HairstyleDetailCard>>{

        LoadCallBack callBack;
        int hairstyleId;

        public LoadDetailCardsTask(int hairstyleId, LoadCallBack callBack){
            this.callBack = callBack;
            this.hairstyleId = hairstyleId;
        }

        @Override
        protected ArrayList<HairstyleDetailCard> doInBackground(Void... params) {
            ArrayList<HairstyleDetailCard> cards = new ArrayList<>();
            Cursor cursor = dbHelper.getReadableDatabase().query("detail", null, null, null, null, null, null);
            while (cursor.moveToNext()){
                if(cursor.getInt(cursor.getColumnIndex("hairstyleId")) != hairstyleId){
                    return null;
                }
                HairstyleDetailCard card = new HairstyleDetailCard();
                card.setHairstyleId(cursor.getInt(cursor.getColumnIndex("hairstyleId")));
                card.setImageUrl(cursor.getString(cursor.getColumnIndex("imageUrl")));
                cards.add(card);
            }
            cursor.close();
            return cards;
        }

        @Override
        protected void onPostExecute(ArrayList<HairstyleDetailCard> cards) {
            super.onPostExecute(cards);
            if(cards != null && cards.size() > 0)
                callBack.onLoadSuccess(cards);
            else
                callBack.onFail("Null table");
        }
    }
}
