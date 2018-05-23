package com.chameli.rtb.fw;

import com.chameli.rtb.fw.GuiceJpaLiquibaseManager.Config;
import com.chameli.rtb.fw.GuiceJpaLiquibaseManager.DdlGeneration;

public class ConfigHelper {

    public static boolean isLiquibased(Config config) {
        return DdlGeneration.LIQUIBASE.equals(config.ddlGeneration());
    }

}
