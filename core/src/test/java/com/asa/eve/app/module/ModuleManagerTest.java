package com.asa.eve.app.module;

import com.asa.eve.structure.app.module.ModuleConfigure;
import com.asa.eve.structure.app.module.ModuleInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * @author andrew_asa
 * @date 2018/11/29.
 */
public class ModuleManagerTest {


    @Test
    public void start() throws Exception {

        ObjectMapper mapper = new XmlMapper();
        ModuleConfigure configure = new ModuleConfigure();
        ModuleInfo info = new ModuleInfo();
        List<ModuleInfo> modules = new ArrayList<>();
        info.setName("plugin");
        info.setClassName("com.asa.eve.plugin.PluginModule");
        modules.add(info);
        configure.setModules(modules);
        mapper.writerWithDefaultPrettyPrinter().writeValueAsString(configure);
        mapper.writeValueAsString(configure);
    }

}