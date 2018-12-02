package com.asa.eve.app.history;

import com.asa.eve.app.listener.input.OutputItemDescription;

/**
 * @author andrew_asa
 * @date 2018/12/2.
 */
public class HistoryItem {

    private long time;

    private OutputItemDescription description;

    public long getTime() {

        return time;
    }

    public void setTime(long time) {

        this.time = time;
    }

    public OutputItemDescription getDescription() {

        return description;
    }

    public void setDescription(OutputItemDescription description) {

        this.description = description;
    }
}
