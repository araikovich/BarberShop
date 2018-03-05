package araikovichinc.barbershop.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tigran on 17.02.2018.
 */

public class Reservation implements Serializable{
    private int day;
    private int month;
    private int year;
    private int timeFrom;
    private int timeTo;
    private int totalSum;

    private HairdresserModel hairdresser;
    private ArrayList<ServiceModel> services;

    public Reservation(int day, int month, int year, int timeFrom, int timeTo, HairdresserModel hairdresser, ArrayList<ServiceModel> services) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hairdresser = hairdresser;
        this.services = services;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
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


    public void addSrvice(ServiceModel service){
        this.services.add(service);

    }
    public int getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(int timeFrom) {
        this.timeFrom = timeFrom;
    }

    public int getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(int timeTo) {
        this.timeTo = timeTo;
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
                services.remove(i);
                totalSum-=services.get(i).getPrice();
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
