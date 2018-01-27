package com.chameli.rtb.service.junit.dao;

import org.apache.log4j.Logger;
import org.eclipse.persistence.queries.Call;
import org.eclipse.persistence.sessions.SessionEvent;
import org.eclipse.persistence.sessions.SessionEventAdapter;

public class JpaSessionListener extends SessionEventAdapter {
    private static final Logger logger = Logger.getLogger(JpaSessionListener.class);

    private int numberOfQueries;

    @Override
    public void preExecuteQuery(SessionEvent event) {
        Call datasourceCall = event.getQuery().getDatasourceCall();
        String logString = datasourceCall.getLogString(event.getQuery().getAccessor());
        logger.debug("Call: " + logString);
        numberOfQueries++;
    }

    public void reset() {
        numberOfQueries = 0;
    }

    public int getNumberOfQueries() {
        return numberOfQueries;
    }
}
