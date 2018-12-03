package com.asa.eve.app.history;

import com.asa.eve.app.listener.input.OutputItemDescription;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrew_asa
 * @date 2018/12/2.
 * 历史记录中心
 */
public class HistoryCentre {

    private static HistoryCentre INSTANCE;


    private HistoryCentre() {

    }

    public static HistoryCentre getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new HistoryCentre();
        }
        return INSTANCE;
    }

    public void addHistory(OutputItemDescription history) {

    }

    public List<HistoryItem> findHistoryByActionName(String name) {

        List<HistoryItem> ret = new ArrayList<>();
        return ret;
    }

    public List<HistoryItem> findHistoryByTime(long start, long end) {

        List<HistoryItem> ret = new ArrayList<>();
        return ret;
    }
}
