package com.chameli.rtb.web.configuration;

import com.chameli.rtb.ejb.AdminItemFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.chameli.rtb.web")
public class SpringConfig {

    private static final String PREFIX = "java:global/rtb-ear-1.0-SNAPSHOT/rtb-ejb-1.0-SNAPSHOT";

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        final InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public JndiObjectFactoryBean adminItemFacade() {
        final JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName(PREFIX + "." + AdminItemFacade.JNDI_NAME + "Bean");
        jndiObjectFactoryBean.setResourceRef(true);
        return jndiObjectFactoryBean;
    }
}
