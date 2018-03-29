package araikovichinc.barbershop.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tigran on 17.02.2018.
 */

public class Reservation implements Serializable{
    private String reservationId;

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    private int day;
    private int month;
    private int year;
    private int timeFromHour;
    private int timeFromMin;
    private int timeToMin;
    private int timeToHour;
    private int totalSum;

    private HairdresserModel hairdresser;
    private ArrayList<ServiceModel> services;

    public Reservation(int day, int month, int year, HairdresserModel hairdresser, ArrayList<ServiceModel> services) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hairdresser = hairdresser;
        this.services = services;
    }

    public Reservation(){
        services = new ArrayList<>();
        hairdresser = new HairdresserModel();
    }


    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public void addService(ServiceModel service){
        this.services.add(service);
        Log.d("MyLogs", "service added");
    }

    public int getTimeFromHour() {
        return timeFromHour;
    }

    public void setTimeFromHour(int timeFromHour) {
        this.timeFromHour = timeFromHour;
    }

    public int getTimeFromMin() {
        return timeFromMin;
    }

    public void setTimeFromMin(int timeFromMin) {
        this.timeFromMin = timeFromMin;
    }

    public int getTimeToMin() {
        return timeToMin;
    }

    public void setTimeToMin(int timeToMin) {
        this.timeToMin = timeToMin;
    }

    public int getTimeToHour() {
        return timeToHour;
    }

    public void setTimeToHour(int timeToHour) {
        this.timeToHour = timeToHour;
    }

    public void deleteHairdresser(){
        this.hairdresser.setId(0);
        this.hairdresser.setName(null);
        this.hairdresser.setPhoto(null);
    }

    public HairdresserModel getHairdresser() {
        return hairdresser;
    }

    public void setHairdresser(int hairdresserId, String name, String photo) {
        this.hairdresser.setPhoto(photo);
        this.hairdresser.setName(name);
        this.hairdresser.setId(hairdresserId);
    }

    public ArrayList<ServiceModel> getServices() {
        return services;
    }

    public void setServices(ArrayList<ServiceModel> services) {
        this.services = services;
    }

    public void deleteService(int serviceId) {
        for(int i = 0; i<services.size(); i++){
            if(services.get(i).getId() == serviceId){
                totalSum-=services.get(i).getPrice();
                services.remove(i);
                break;
            }
        }
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public void setHairdresser(HairdresserModel hairdresser) {
        this.hairdresser = hairdresser;
    }

}
