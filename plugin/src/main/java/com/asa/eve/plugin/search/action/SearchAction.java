package com.asa.eve.plugin.search.action;

import com.asa.eve.app.listener.input.OutputItemDescription;
import com.asa.eve.app.listener.input.OutputItemDescriptionHelper;
import com.asa.eve.internalimp.ui.UIManager;
import com.asa.eve.internalimp.ui.icon.ClassPathResourceIcon;
import com.asa.eve.plugin.AbstractPluginInputAction;
import com.asa.eve.plugin.search.SearchItem;
import com.asa.eve.plugin.start.AppInfo;
import com.asa.eve.plugin.start.AppSearchService;
import com.asa.eve.structure.ui.IconTextPanel;
import com.asa.eve.structure.ui.TextPanel;
import com.asa.eve.structure.ui.UICreator;
import com.asa.eve.utils.DesktopUtils;
import com.asa.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrew_asa
 * @date 2018/12/3.
 */
public class SearchAction extends AbstractPluginInputAction {

    private static final String DEFAULT_START_KEY = "s:";

    private String url = "https://www.google.com/#q={}&*";

    private static final String DEFUALT_ICON_PATH = "search/google.png";


    @Override
    public String getActionName() {

        return "SearchAction";
    }

    @Override
    public List<OutputItemDescription> analysis(String text) {

        List<OutputItemDescription> descriptions = new ArrayList<>();
        String searchText = getActionText(text);
        SearchItem searchItem = new SearchItem();
        searchItem.setAllText(text);
        searchItem.setSearchText(searchText);
        searchItem.setQueryUrl(StringUtils.messageFormat(url, searchText));
        OutputItemDescription itemDescription = OutputItemDescriptionHelper.safeCreateDefaultItem(searchItem);
        OutputItemDescriptionHelper.connectActionAndOutputItemDescription(this, itemDescription, text);
        descriptions.add(itemDescription);
        return descriptions;
    }

    @Override
    public IconTextPanel describe(OutputItemDescription description) {

        return OutputItemDescriptionHelper.safeDescribe(description, SearchItem.class, app -> {
            UICreator uiCreator = UIManager.getInstance().getUICreator();
            IconTextPanel iconTextPanel = uiCreator.createIconTextPanel();
            iconTextPanel.setText("google");
            iconTextPanel.setIcon(new ClassPathResourceIcon(DEFUALT_ICON_PATH));
            return iconTextPanel;
        });
    }

    @Override
    public void apply(OutputItemDescription description) {

        OutputItemDescriptionHelper.safeApply(description, SearchItem.class, searchItem -> {
            DesktopUtils.openBrowser(searchItem.getQueryUrl());
        });
    }

    @Override
    public String getPluginActionKey() {

        return DEFAULT_START_KEY;
    }
}
