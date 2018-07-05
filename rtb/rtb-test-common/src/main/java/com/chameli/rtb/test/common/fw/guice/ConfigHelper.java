package com.chameli.rtb.test.common.fw.guice;

import com.chameli.rtb.test.common.fw.guice.GuiceJpaLiquibaseManager.Config;
import com.chameli.rtb.test.common.fw.guice.GuiceJpaLiquibaseManager.DdlGeneration;

public class ConfigHelper {

    public static boolean isLiquibased(Config config) {
        return DdlGeneration.LIQUIBASE.equals(config.ddlGeneration());
    }

}
