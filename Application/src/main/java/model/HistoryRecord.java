package model;

import com.google.gson.Gson;

/**
 * Created by Jackyliz on 4/10/15.
 */
public class HistoryRecord {
    private String userId;
    private String totalTime;
    private String totalDistance;
    private String lastLocation;

    public HistoryRecord(){

    }

    public HistoryRecord(String myUserID, String myTotalTime, String myTotalDistance){
        userId = myUserID;
        totalTime = myTotalTime;
        totalDistance = myTotalDistance;
        lastLocation = "100000werun1000000";
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }

    public String getUserid() {
        return userId;
    }

    public void setUserid(String userid) {
        this.userId = userid;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public String getTotalDistance() {
        return totalDistance;
    }


    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void addDistance(String newDistance){
        long curDistance = Long.parseLong(totalDistance);
        long newdistance = Long.parseLong(newDistance);
        curDistance = curDistance + newdistance;
        totalDistance = String.valueOf(curDistance);
    }

    public void addTime(int newTime){
        long curTime = Long.parseLong(totalTime);
        curTime = curTime + newTime;
        totalTime = String.valueOf(curTime);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
