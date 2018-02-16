package araikovichinc.barbershop.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;

import java.util.ArrayList;

import araikovichinc.barbershop.api.ServerApi;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.pojo.HairstyleCategoryCard;
import araikovichinc.barbershop.utils.DBHelper;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Tigran on 12.02.2018.
 */

public class HairstyleCategoryModel {

    private ServerApi api;
    private DBHelper db;
    private boolean isdLoading = false;

    public HairstyleCategoryModel(ServerApi api, DBHelper db){
        this.api = api;
        this.db = db;
    }

    public void loadCategoriesFromServer(int genderId, Callback callback){
        Call<ArrayList<HairstyleCategoryCard>> call = api.getHairstyleCategories(genderId);
        call.enqueue(callback);
    }

    public void saveCardToDb(ArrayList<HairstyleCategoryCard> cards){
        SaveCategoryCardsTask task = new SaveCategoryCardsTask();
        task.execute(cards);
    }

    public void getCardsFromDb(int genderId, LoadCallBack callBack){
        LoadCategoryCardsTask task = new LoadCategoryCardsTask(genderId, callBack);
        task.execute();
    }

    public boolean isIsdLoading(){
        return isdLoading;
    }

    class SaveCategoryCardsTask extends AsyncTask<ArrayList<HairstyleCategoryCard>, Void, Void> {

        @Override
        protected Void doInBackground(ArrayList<HairstyleCategoryCard>... params) {
            ContentValues contentValues = new ContentValues();
            SQLiteDatabase database = db.getWritableDatabase();
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

    class LoadCategoryCardsTask extends AsyncTask<Void, Void, ArrayList<HairstyleCategoryCard>>{

        LoadCallBack callBack;
        int genderId;

        public LoadCategoryCardsTask(int genderId, LoadCallBack callBack){
            this.callBack = callBack;
            this.genderId = genderId;
        }

        @Override
        protected ArrayList<HairstyleCategoryCard> doInBackground(Void... params) {
            isdLoading = true;
            ArrayList<HairstyleCategoryCard> cards = new ArrayList<>();
            Cursor cursor = db.getReadableDatabase().query("category", null, null, null, null, null, null);
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
            isdLoading = false;
            if(cards!= null && cards.size() > 0)
                callBack.onLoadSuccess(cards);
            else
                callBack.onFail("Null table");
        }
    }
}
