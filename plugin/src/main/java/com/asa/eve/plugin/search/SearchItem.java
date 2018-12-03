package com.asa.eve.plugin.search;

/**
 * @author andrew_asa
 * @date 2018/12/3.
 */
public class SearchItem {

    private String searchText;

    private String allText;

    private String queryUrl;

    public String getSearchText() {

        return searchText;
    }

    public void setSearchText(String searchText) {

        this.searchText = searchText;
    }

    public String getAllText() {

        return allText;
    }

    public void setAllText(String allText) {

        this.allText = allText;
    }

    public String getQueryUrl() {

        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {

        this.queryUrl = queryUrl;
    }
}
