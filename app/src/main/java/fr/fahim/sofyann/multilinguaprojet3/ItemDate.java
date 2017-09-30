package fr.fahim.sofyann.multilinguaprojet3;

/**
 * Created by Sofyann on 19/09/2017.
 */

public class ItemDate {
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minutes;
    private String duration;
    private String event;
    private String objectId;

    public ItemDate(int day, int month, int year, int hour, int minutes, String duration, String event, String objectId){
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minutes = minutes;
        this.duration = duration;
        this.event = event;
        this.objectId = objectId;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getDuration() {
        return duration;
    }

    public String getEvent() {
        return event;
    }

    public String getObjectId() {
        return objectId;
    }
}
