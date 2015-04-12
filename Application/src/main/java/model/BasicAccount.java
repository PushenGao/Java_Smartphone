package model;

import java.util.HashMap;

/**
 * Created by Jackyliz on 4/11/15.
 */
public abstract class BasicAccount {
    private String gender;
    private String name;
    private HistoryRecord historyRecord;
    private HashMap<String, ChatRecord> map;

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public HistoryRecord getHistoryRecord() {
        return historyRecord;
    }

    public HashMap<String, ChatRecord> getMap() {
        return map;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHistoryRecord(HistoryRecord historyRecord) {
        this.historyRecord = historyRecord;
    }

    public void setMap(HashMap<String, ChatRecord> map) {
        this.map = map;
    }
}
