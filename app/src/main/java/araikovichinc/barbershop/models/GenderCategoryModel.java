package araikovichinc.barbershop.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

import araikovichinc.barbershop.api.ServerApi;
import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.pojo.GenderCard;
import araikovichinc.barbershop.utils.DBHelper;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Tigran on 12.02.2018.
 */

public class GenderCategoryModel {

    ServerApi api;
    DBHelper dbHelper;
    boolean isLoading = false;

    public GenderCategoryModel(ServerApi api, DBHelper dbHelper){
        this.api = api;
        this.dbHelper = dbHelper;
    }

    public void loadGenderCards(Callback callback){
        Call<ArrayList<GenderCard>> cardCall = api.getSexCategories();
        cardCall.enqueue(callback);
    }

    public boolean isLoading(){
        return isLoading;
    }

    public void setLoading(boolean loading){
        this.isLoading = loading;
    }

    public void saveGenderCards(ArrayList<GenderCard> cards){
        SaveGenderCardsTadk tadk = new SaveGenderCardsTadk();
        tadk.execute(cards);
    }

    public void loadGenderCardsFormDb(LoadCallBack callBack){
        LoadGenderCardsTask task = new LoadGenderCardsTask(callBack);
        task.execute();
    }

    class SaveGenderCardsTadk extends AsyncTask<ArrayList<GenderCard>, Void, Void>{

        @Override
        protected Void doInBackground(ArrayList<GenderCard>... params) {
            ContentValues contentValues = new ContentValues();
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            database.delete("genders", null, null);
            for(int i = 0; i<params[0].size(); i++){
                contentValues.put("id", params[0].get(i).getSexId());
                contentValues.put("title", params[0].get(i).getTitle());
                contentValues.put("imageUrl", params[0].get(i).getImageUrl());
                database.insert("genders", null, contentValues);
                contentValues.clear();
            }
            return null;
        }

    }

    class LoadGenderCardsTask extends AsyncTask<Void, Void, ArrayList<GenderCard>>{

        LoadCallBack callBack;

        public LoadGenderCardsTask(LoadCallBack callBack){
            this.callBack = callBack;
        }

        @Override
        protected ArrayList<GenderCard> doInBackground(Void... params) {
            isLoading = true;
            ArrayList<GenderCard> cards = new ArrayList<>();
            Cursor cursor = dbHelper.getReadableDatabase().query("genders", null, null, null, null, null, null);
            while (cursor.moveToNext()){
                GenderCard card = new GenderCard();
                card.setSexId(cursor.getInt(cursor.getColumnIndex("id")));
                card.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                card.setImageUrl(cursor.getString(cursor.getColumnIndex("imageUrl")));
                cards.add(card);
            }
            cursor.close();
            return cards;
        }

        @Override
        protected void onPostExecute(ArrayList<GenderCard> cards) {
            super.onPostExecute(cards);
            isLoading = false;
            if(cards.size() > 0)
                callBack.onLoadSuccess(cards);
            else
                callBack.onFail("Null table");
        }
    }

}
