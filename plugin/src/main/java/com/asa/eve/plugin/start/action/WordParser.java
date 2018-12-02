package com.asa.eve.plugin.start.action;

import com.asa.eve.plugin.start.pinyinhelper.Pinyin;
import com.asa.utils.StringUtils;

/**
 * @author andrew_asa
 * @date 2018/11/29.
 */
public class WordParser {

    /**
     * 相似度
     *
     * @param str1
     * @param str2
     * @return
     */
    public float similarity(String str1, String str2) {
        //计算两个字符串的长度。
        int len1 = str1.length();
        int len2 = str2.length();
        //建立上面说的数组，比字符长度大一个空间
        int[][] dif = new int[len1 + 1][len2 + 1];
        //赋初值，步骤B。
        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }
        //计算两个字符是否一样，计算左上的值
        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char c1 = str1.charAt(i - 1);
                char c2 = str2.charAt(j - 1);
                String cs1 = new String(new char[]{c1});
                String cs2 = new String(new char[]{c2});
                if (str1.charAt(i - 1) == str2.charAt(j - 1) || cs1.equalsIgnoreCase(cs2)) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                //取三个值中最小的
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
                                dif[i - 1][j] + 1);
            }
        }
        float similarity = 1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
        //LoggerFactory.getLogger().info("[{}] : [{}] similarity {}", str1, str2, similarity);
        return similarity;
    }

    private int min(int... is) {

        int min = Integer.MAX_VALUE;
        for (int i : is) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }

    /**
     * 编辑距离
     * @param str1
     * @param str2
     * @return
     */
    public int editDistanceIgnoreCase(String str1, String str2) {

        if (str1.equals(str2)) {
            return 0;
        }
        //dp[i][j]表示源串A位置i到目标串B位置j处最低需要操作的次数
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= str2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                char c1 = str1.charAt(i - 1);
                char c2 = str2.charAt(j - 1);
                String cs1 = new String(new char[]{c1});
                String cs2 = new String(new char[]{c2});
                if (str1.charAt(i - 1) == str2.charAt(j - 1) || cs1.equalsIgnoreCase(cs2)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1,
                                        Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1));
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }

    public String toPinyin(String str) {

        return Pinyin.toPinyin(str, StringUtils.EMPTY);
    }
}