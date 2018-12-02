package com.asa.eve.app.module;

import com.asa.eve.exception.UIStartException;
import com.asa.eve.structure.app.module.Module;
import com.asa.eve.structure.app.module.ModuleConfigure;
import com.asa.eve.structure.app.module.ModuleInfo;
import com.asa.local.InterProviderFactory;
import com.asa.utils.ListUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author andrew_asa
 * @date 2018/11/29.
 */
public class ModuleManager {

    public static final String CONFIGURE_PATH = "modules.json";

    private ModuleConfigure moduleConfigure;

    private List<Module> modules = new ArrayList<>();

    public void start() {

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(CONFIGURE_PATH);
        ObjectMapper mapper = new ObjectMapper();
        try {
            moduleConfigure = mapper.readValue(inputStream, ModuleConfigure.class);
        } catch (IOException e) {
            throw new UIStartException(InterProviderFactory.getProvider().getLocaleText("EVE-App_Module_Conf_ERROR"));
        }
        List<ModuleInfo> modulesInfo = moduleConfigure.getModules();
        ListUtils.ensureNotNull(modulesInfo).forEach(moduleInfo -> {
            String moduleClazzName = moduleInfo.getClassName();
            try {
                Class<?> moduleClazz = Class.forName(moduleClazzName);
                Module module = (Module) moduleClazz.newInstance();
                modules.add(module);
                module.start();
            } catch (Exception e) {
                e.printStackTrace();
                throw new UIStartException(InterProviderFactory.getProvider().getLocaleText("EVE-App_Module_Init_ERROR", moduleInfo.getName()));
            }
        });
    }

}
