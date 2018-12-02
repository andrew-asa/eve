package com.asa.eve.plugin.start.action;

import com.asa.log.LoggerFactory;
import com.asa.utils.ListUtils;
import org.junit.Test;

import java.util.List;

/**
 * @author andrew_asa
 * @date 2018/11/29.
 */
public class WordParserTest {

    @Test
    public void similarity() throws Exception {

        WordParser parser = new WordParser();
        String str1 = "mWeb";
        List<String> list =
                ListUtils.arrayToList(
                        "m", "mw", "mwe", "mweb",
                        "mW", "Mw", "MW",
                        "Mwe", "mWe", "mwE", "MWe", "MwE", "mWE", "MWE",
                        "Mweb", "mWeb", "mwEb", "mweB",
                        "MWeb", "MwEb", "MweB",
                        "mWEb", "mWeB",
                        "mwEB",
                        "mWEB", "MwEB", "MWeB", "MWEb",
                        "MWEB"

                );
        list.forEach(str2 -> {
            LoggerFactory.getLogger().info("{} : {} similarity {}", str1, str2, parser.similarity(str1, str2));
        });
        list.forEach(str2 -> {
            LoggerFactory.getLogger().info("{} : {} edit Distant Length {}", str1, str2, parser.editDistanceIgnoreCase(str1, str2));
        });
    }

    @Test
    public void toPinyin() {

        WordParser parser = new WordParser();
        ListUtils.arrayToList("微信","中文","terminal").forEach(str -> {
            LoggerFactory.getLogger().info("{} to pinyin {}", str, parser.toPinyin(str));
        });
    }

}