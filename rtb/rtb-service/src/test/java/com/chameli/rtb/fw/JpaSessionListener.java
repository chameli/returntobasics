package com.chameli.rtb.fw;

import org.eclipse.persistence.queries.Call;
import org.eclipse.persistence.sessions.SessionEvent;
import org.eclipse.persistence.sessions.SessionEventAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JpaSessionListener extends SessionEventAdapter {
    private static final Logger logger = LoggerFactory.getLogger(JpaSessionListener.class);

    private int numberOfQueries;

    @Override
    public void preExecuteQuery(SessionEvent event) {
        Call datasourceCall = event.getQuery().getDatasourceCall();
        if (datasourceCall != null) {
            String logString = datasourceCall.getLogString(event.getQuery().getAccessor());
            logger.debug("Call: {}", logString);
            numberOfQueries++;
        } else {
            logger.warn("What to do with event {}???", event);
        }
    }

    public void reset() {
        numberOfQueries = 0;
    }

    public int getNumberOfQueries() {
        return numberOfQueries;
    }
}
