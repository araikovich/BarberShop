package araikovichinc.barbershop.repository.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

import araikovichinc.barbershop.callbacks.LoadCallBack;
import araikovichinc.barbershop.datasource.ReservationModelDataSource;
import araikovichinc.barbershop.pojo.Reservation;
import araikovichinc.barbershop.pojo.ServiceModel;
import araikovichinc.barbershop.utils.DBHelper;

/**
 * Created by Tigran on 27.03.2018.
 */

public class ReservationModelLocalDataSource implements ReservationModelDataSource {

    private DBHelper dbHelper;

    @Inject
    public ReservationModelLocalDataSource(Context context){
        dbHelper = new DBHelper(context);
    }

    @Override
    public void saveReservation(Reservation reservation) {
        SaveReservationTask task = new SaveReservationTask();
        task.execute(reservation);
    }

    @Override
    public void loadReservationList(LoadCallBack callBack) {
        LoadReservationListTask task = new LoadReservationListTask(callBack);
        task.execute();
    }

    @Override
    public void loadReservation(String reservationId, LoadCallBack callBack) {
        LoadReservationTask task = new LoadReservationTask(callBack);
        task.execute(reservationId);
    }

    private class SaveReservationTask extends AsyncTask<Reservation, Void, Void> {

        @Override
        protected Void doInBackground(Reservation... params) {
            ContentValues contentValues = new ContentValues();
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            String uuid = UUID.randomUUID().toString();
            contentValues.put("reservationId", uuid);
            contentValues.put("day", params[0].getDay());
            contentValues.put("month", params[0].getMonth());
            contentValues.put("year", params[0].getYear());
            contentValues.put("timeFromHour", params[0].getTimeFromHour());
            contentValues.put("timeFromMin", params[0].getTimeFromMin());
            contentValues.put("timeToMin", params[0].getTimeToMin());
            contentValues.put("timeToHour", params[0].getTimeToHour());
            contentValues.put("totalSum", params[0].getTotalSum());
            contentValues.put("timeToHour", params[0].getTimeToHour());
            contentValues.put("hairdresserName", params[0].getHairdresser().getName());
            contentValues.put("hairdresserPhoto", params[0].getHairdresser().getPhoto());
            database.insert("reservation", null,  contentValues);
            contentValues.clear();
            for(int i = 0; i<params[0].getServices().size(); i++){
                contentValues.put("reservationId", uuid);
                contentValues.put("title", params[0].getServices().get(i).getTitle());
                contentValues.put("price", params[0].getServices().get(i).getPrice());
                contentValues.put("time", params[0].getServices().get(i).getTime());
                database.insert("my_services", null, contentValues);
                contentValues.clear();
            }
            database.close();
            return null;
        }
    }

    private class LoadReservationListTask extends AsyncTask<Void, Void, ArrayList<Reservation>>{

        private LoadCallBack callBack;

        public LoadReservationListTask(LoadCallBack callBack){
            this.callBack = callBack;
        }

        @Override
        protected ArrayList<Reservation> doInBackground(Void... voids) {
            ArrayList<Reservation> reservations = new ArrayList<>();
            Cursor cursor = dbHelper.getReadableDatabase().query("reservation", null, null, null, null, null, null);
            while (cursor.moveToNext()){
                Reservation reservation = new Reservation();
                reservation.setReservationId(cursor.getString(cursor.getColumnIndex("reservationId")));
                reservation.setDay(cursor.getInt(cursor.getColumnIndex("day")));
                reservation.setMonth(cursor.getInt(cursor.getColumnIndex("month")));
                reservation.setYear(cursor.getInt(cursor.getColumnIndex("year")));
                reservation.setTimeFromHour(cursor.getInt(cursor.getColumnIndex("timeFromHour")));
                reservation.setTimeFromMin(cursor.getInt(cursor.getColumnIndex("timeFromMin")));
                reservation.setTimeToHour(cursor.getInt(cursor.getColumnIndex("timeToHour")));
                reservation.setTimeToMin(cursor.getInt(cursor.getColumnIndex("timeToMin")));
                reservation.setTotalSum(cursor.getInt(cursor.getColumnIndex("totalSum")));
                reservation.getHairdresser().setName(cursor.getString(cursor.getColumnIndex("hairdresserName")));
                reservation.getHairdresser().setPhoto(cursor.getString(cursor.getColumnIndex("hairdresserPhoto")));
                reservations.add(reservation);
            }
            cursor.close();
            return reservations;
        }

        @Override
        protected void onPostExecute(ArrayList<Reservation> reservations) {
            super.onPostExecute(reservations);
            if(reservations != null && reservations.size() > 0)
                callBack.onLoadSuccess(reservations);
            else
                callBack.onFail("Null table");
        }
    }

    private class LoadReservationTask extends AsyncTask<String, Void, Reservation>{

        private LoadCallBack callBack;

        public LoadReservationTask(LoadCallBack callBack){
            this.callBack = callBack;
        }

        @Override
        protected Reservation doInBackground(String... integers) {
            Reservation reservation = new Reservation();
            Cursor cursor = dbHelper.getReadableDatabase().query("reservation", null, "reservationId = ?", new String[]{integers[0]}, null, null, null);
            cursor.moveToFirst();
            if(cursor !=null){
                reservation.setDay(cursor.getInt(cursor.getColumnIndex("day")));
                reservation.setMonth(cursor.getInt(cursor.getColumnIndex("month")));
                reservation.setYear(cursor.getInt(cursor.getColumnIndex("year")));
                reservation.setTimeFromHour(cursor.getInt(cursor.getColumnIndex("timeFromHour")));
                reservation.setTimeFromMin(cursor.getInt(cursor.getColumnIndex("timeFromMin")));
                reservation.setTimeToHour(cursor.getInt(cursor.getColumnIndex("timeToHour")));
                reservation.setTimeToMin(cursor.getInt(cursor.getColumnIndex("timeToMin")));
                reservation.setTotalSum(cursor.getInt(cursor.getColumnIndex("totalSum")));
                reservation.getHairdresser().setName(cursor.getString(cursor.getColumnIndex("hairdresserName")));
                reservation.getHairdresser().setPhoto(cursor.getString(cursor.getColumnIndex("hairdresserPhoto")));
                Cursor serviceCursor = dbHelper.getReadableDatabase().query("my_services", null, "reservationId = ?", new String[]{cursor.getString(cursor.getColumnIndex("reservationId"))}, null, null, null);
                while (serviceCursor.moveToNext()){
                    ServiceModel serviceModel = new ServiceModel();
                    serviceModel.setPrice(serviceCursor.getInt(serviceCursor.getColumnIndex("price")));
                    serviceModel.setTime(serviceCursor.getInt(serviceCursor.getColumnIndex("time")));
                    serviceModel.setTitle(serviceCursor.getString(serviceCursor.getColumnIndex("title")));
                    reservation.addService(serviceModel);
                }
                serviceCursor.close();
                cursor.close();
                return reservation;
            }else
                return null;
        }

        @Override
        protected void onPostExecute(Reservation reservation) {
            super.onPostExecute(reservation);
            if(reservation !=null)
                callBack.onLoadSuccess(reservation);
            else
                callBack.onFail("Fail!");
        }
    }
}
