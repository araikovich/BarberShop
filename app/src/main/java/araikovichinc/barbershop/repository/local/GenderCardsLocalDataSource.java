package araikovichinc.barbershop.repository.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

import javax.inject.Inject;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.GenderCardsDataSource;
import araikovichinc.barbershop.pojo.GenderCard;
import araikovichinc.barbershop.utils.DBHelper;

/**
 * Created by Tigran on 23.02.2018.
 */

public class GenderCardsLocalDataSource implements GenderCardsDataSource {

    private DBHelper dbHelper;

    @Inject
    public GenderCardsLocalDataSource(Context context){
        dbHelper = new DBHelper(context);
    }

    @Override
    public void getCards(LoadCallBack callback) {
        LoadGenderCardsTask task = new LoadGenderCardsTask(callback);
        task.execute();
    }

    @Override
    public void saveCardsToDb(ArrayList<GenderCard> cards) {
        SaveGenderCardsTask task = new SaveGenderCardsTask();
        task.execute(cards);
    }

    private class SaveGenderCardsTask extends AsyncTask<ArrayList<GenderCard>, Void, Void> {

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

    private class LoadGenderCardsTask extends AsyncTask<Void, Void, ArrayList<GenderCard>>{

        LoadCallBack callBack;

        public LoadGenderCardsTask(LoadCallBack callBack){
            this.callBack = callBack;
        }

        @Override
        protected ArrayList<GenderCard> doInBackground(Void... params) {
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
            if(cards.size() > 0)
                callBack.onLoadSuccess(cards);
            else
                callBack.onFail("Null table");
        }
    }


}
