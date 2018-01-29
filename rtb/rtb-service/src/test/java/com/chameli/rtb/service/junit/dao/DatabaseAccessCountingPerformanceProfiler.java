package com.chameli.rtb.service.junit.dao;

import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.queries.DatabaseQuery;
import org.eclipse.persistence.sessions.Record;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.SessionProfiler;
import org.eclipse.persistence.tools.profiler.PerformanceProfiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseAccessCountingPerformanceProfiler extends PerformanceProfiler {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseAccessCountingPerformanceProfiler.class);

    private int numberOfQueryCalls;

    public DatabaseAccessCountingPerformanceProfiler() {
        //logger.warn("Instantiating DatabaseAccessCountingPerformanceProfiler");
    }

    @Override
    public boolean shouldLogProfile() {
        return false;
    }

    @Override
    public void endOperationProfile(String operationName) {
        //logger.warn("endOperationProfile, operationName {}", operationName);
        super.endOperationProfile(operationName);
    }

    @Override
    public void endOperationProfile(String operationName, DatabaseQuery query, int weight) {
        if ("Timer:StatementExecute".equals(operationName)) {
//            logger.warn("{} endOperationProfile, operationName {}, query {}, weight {}", getNestLevel(), operationName, query, weight);
            numberOfQueryCalls++;
        }
        super.endOperationProfile(operationName, query, weight);
    }

    @Override
    public Object profileExecutionOfQuery(DatabaseQuery query, Record row, AbstractSession session) {
//        logger.warn("profileExecutionOfQuery, query {}, row {}, session {}", query, row, session);
        return super.profileExecutionOfQuery(query, row, session);
    }

    @Override
    public void setSession(Session session) {
        //logger.warn("setSession, session {}", session);
        super.setSession(session);
    }

    @Override
    public void startOperationProfile(String operationName) {
        //logger.warn("startOperationProfile, operationName {}", operationName);
        super.startOperationProfile(operationName);
    }

    @Override
    public void startOperationProfile(String operationName, DatabaseQuery query, int weight) {
        //logger.warn("startOperationProfile, operationName {}, query {}, weight {}", operationName, query, weight);
        super.startOperationProfile(operationName, query, weight);
    }

    @Override
    public void update(String operationName, Object value) {
        //logger.warn("update, operationName {}, value {}", operationName, value);
        super.update(operationName, value);
    }

    @Override
    public void occurred(String operationName, AbstractSession session) {
        //logger.warn("occurred, operationName {}, session {}", operationName, session);
        super.occurred(operationName, session);
    }

    @Override
    public void occurred(String operationName, DatabaseQuery query, AbstractSession session) {
        //logger.warn("occurred, operationName {}, query {}, session {}", operationName, query, session);
        super.occurred(operationName, query, session);
    }

    @Override
    public void setProfileWeight(int weight) {
        //logger.warn("setProfileWeight {}", weight);
        super.setProfileWeight(weight);
    }

    @Override
    public int getProfileWeight() {
        //logger.warn("getProfileWeight");
        return SessionProfiler.ALL;
    }

    @Override
    public void initialize() {
        //logger.warn("initialize");
        super.initialize();
    }

    public void reset() {
        numberOfQueryCalls = 0;
    }

    public int getNumberOfQueryCalls() {
        // For some reason endOperationProfile is called twice
        // for what I believe is closest to query calls to the database
        // hence dividing by two and then hope for the best...
        return numberOfQueryCalls / 2;
    }
}
