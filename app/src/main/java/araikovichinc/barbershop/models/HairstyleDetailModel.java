package araikovichinc.barbershop.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

import araikovichinc.barbershop.api.ServerApi;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.pojo.HairstyleCategoryCard;
import araikovichinc.barbershop.pojo.HairstyleDetailCard;
import araikovichinc.barbershop.utils.DBHelper;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Tigran on 12.02.2018.
 */

public class HairstyleDetailModel {
    ServerApi api;
    DBHelper dbHelper;
    private boolean isdLoading;

    public HairstyleDetailModel(ServerApi api, DBHelper dbHelper) {
        this.api = api;
        this.dbHelper = dbHelper;
    }

    public void loadDetailFromServer(int hairstyleId, Callback callback){
        Call<ArrayList<HairstyleDetailCard>> call = api.getHairstyleDetailing(hairstyleId);
        call.enqueue(callback);
    }

    public void saveDetail(ArrayList<HairstyleDetailCard> cards){
        SaveDetailCardsTask task = new SaveDetailCardsTask();
        task.execute(cards);
    }

    public void loadCardsFromDb(int hairstyleId, LoadCallBack callBack){
        LoadDetailCardsTask task = new LoadDetailCardsTask(hairstyleId, callBack);
        task.execute();
    }

    public boolean isdLoading() {
        return isdLoading;
    }

    class SaveDetailCardsTask extends AsyncTask<ArrayList<HairstyleDetailCard>, Void, Void> {

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

    class LoadDetailCardsTask extends AsyncTask<Void, Void, ArrayList<HairstyleDetailCard>>{

        LoadCallBack callBack;
        int hairstyleId;

        public LoadDetailCardsTask(int hairstyleId, LoadCallBack callBack){
            this.callBack = callBack;
            this.hairstyleId = hairstyleId;
        }

        @Override
        protected ArrayList<HairstyleDetailCard> doInBackground(Void... params) {
            isdLoading = true;
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
            isdLoading = false;
            if(cards != null && cards.size() > 0)
                callBack.onLoadSuccess(cards);
            else
                callBack.onFail("Null table");
        }
    }
}
