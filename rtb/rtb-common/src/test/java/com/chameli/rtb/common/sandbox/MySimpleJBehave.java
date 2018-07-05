package com.chameli.rtb.common.sandbox;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

@RunWith(JUnitReportingRunner.class)
public class MySimpleJBehave extends JUnitStories {

    public MySimpleJBehave() {
        JUnitReportingRunner.recommendedControls(configuredEmbedder());
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new MySquareSteps());
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration();
    }

    @Override
    protected List<String> storyPaths() {
        return Collections.singletonList("mysquare.story");
    }

}
