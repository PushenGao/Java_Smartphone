package model;

/**
 * Created by Jackyliz on 4/10/15.
 */
public class HistoryRecord {
    private String totalTime;
    private String totalDistance;
    private String totalConsumption;

    public String getTotalTime() {
        return totalTime;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public String getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setTotalConsumption(String totalConsumption) {
        this.totalConsumption = totalConsumption;
    }
}
