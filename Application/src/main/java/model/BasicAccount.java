package model;

import java.util.HashMap;

/**
 * Created by Jackyliz on 4/11/15.
 */
public abstract class BasicAccount {
    private String name;
    private String age;
    private String gender;
    private HistoryRecord historyRecord;

    public BasicAccount() {

    }

    public BasicAccount(String name, String age, String gender, HistoryRecord historyRecord, HashMap<String, ChatRecord> charRecordMap) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.historyRecord = historyRecord;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getAge() { return age; }

    public HistoryRecord getHistoryRecord() {
        return historyRecord;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(String age) { this.age = age; }

    public void setName(String name) {
        this.name = name;
    }

    public void setHistoryRecord(HistoryRecord historyRecord) {
        this.historyRecord = historyRecord;
    }

}
