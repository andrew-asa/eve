package com.asa.eve.plugin.start.action;

import com.asa.eve.app.listener.input.AbstractInputAction;
import com.asa.eve.app.listener.input.Desire;
import com.asa.eve.app.listener.input.OutputItemDescription;
import com.asa.eve.app.listener.input.OutputItemDescriptionHelper;
import com.asa.eve.constant.AppConstant;
import com.asa.eve.internalimp.ui.UIManager;
import com.asa.eve.plugin.start.AppInfo;
import com.asa.eve.plugin.start.AppSearchService;
import com.asa.eve.plugin.start.AppService;
import com.asa.eve.structure.ui.TextPanel;
import com.asa.eve.structure.ui.UICreator;
import com.asa.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrew_asa
 * @date 2018/11/28.
 */
public class StartAppAction extends AbstractInputAction {

    private WordParser wordParser = new WordParser();


    public StartAppAction() {

    }

    @Override
    public Desire desire(String key) {

        return Desire.WILLING;
    }

    @Override
    public List<OutputItemDescription> analysis(String text) {

        List<OutputItemDescription> descriptions = new ArrayList<>();
        // 如果指定的是特定的插件不进行处理 
        if (text == null || text.contains(AppConstant.COMMON.PLUGIN_SEPARATOR)) {
            return descriptions;
        }
        AppService appService = AppSearchService.getInstance().getAppService();
        appService.getInstallApp()
                .stream()
                .filter(app -> {
                    //
                    String appName = wordParser.toPinyin(app.getName());
                    return wordParser.similarity(text, appName) >= 0.1;
                })
                .sorted((app1, app2) -> {
                    if (wordParser.similarity(text, app1.getName()) > wordParser.similarity(text, app2.getName())) {
                        return -1;
                    }
                    return 1;
                })
                .forEach(app -> {
                    OutputItemDescription itemDescription = OutputItemDescriptionHelper.safeCreateDefaultItem(app);
                    OutputItemDescriptionHelper.connectActionAndOutputItemDescription(this, itemDescription, text);
                    ListUtils.safeAdd(descriptions, itemDescription);
                });
        return descriptions;
    }

    @Override
    public TextPanel describe(OutputItemDescription description) {

        return OutputItemDescriptionHelper.safeDescribe(description, AppInfo.class, app -> {
            UICreator creator = UIManager.getInstance().getUICreator();
            TextPanel label = creator.createTextPanel();
            label.setText(app.getName());
            return label;
        });
    }

    @Override
    public void detailDescription(OutputItemDescription description) {

    }

    @Override
    public void apply(OutputItemDescription description) {

        OutputItemDescriptionHelper.safeApply(description, AppInfo.class, app -> {
            AppSearchService.getInstance().getAppService().launch(app);
        });
    }

    @Override
    public String getActionName() {

        return "startApp";
    }
}
