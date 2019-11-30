package com.zlead.shiro.redis;

import com.puqian.util.RedisCacheClient;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hejie on 2016/12/29.
 */
public class RedisSessionDAO extends AbstractSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

    private RedisCacheClient redisCacheClient;

    private String keyPrefix = "shiro_redis_session:";

    private int expireSeconds = 1800;


    private void saveSession(Session session) throws UnknownSessionException{
        if(session == null || session.getId() == null){
            logger.error("session or session id is null");
            return;
        }
        byte[] value =  SerializeUtils.serialize((SimpleSession)session);


        String key = getKey(session.getId());

        session.setTimeout(expireSeconds * 1000);


        redisCacheClient.set(key, value, Long.parseLong(String.valueOf(expireSeconds)));

    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);


        logger.debug("doCreate id: {} , session: {}" , getKey(session.getId()), session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if(sessionId == null){
            logger.error("session id is null");
            return null;
        }

        String key = getKey(sessionId);
        byte [] objectData = redisCacheClient.get(key);
        if(objectData == null){
            return null;
        }
        Session session = (Session)SerializeUtils.deserialize(objectData);


        if(logger.isDebugEnabled()){
            logger.debug("doReadSession id: {} , session: {}" , key, session);
        }

        return session;
    }

    private String getKey(Serializable sessionId){
        return this.keyPrefix + sessionId;
    }



    @Override
    public void update(Session session) throws UnknownSessionException {
        this.saveSession(session);
        if(logger.isDebugEnabled()){
            logger.debug("doUpdate id: {} , session: {}" ,getKey(session.getId()), session);
        }
    }

    @Override
    public void delete(Session session) {
        if(session == null || session.getId() == null){
            logger.error("session or session id is null");
            return;
        }

        String key = getKey(session.getId());

        if(logger.isDebugEnabled()){
            logger.debug("doDelete id: {} , session: {}" , key, session);
        }

        redisCacheClient.delete(key);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<Session>();

        Set<String> keys = redisCacheClient.keys(this.keyPrefix + "*");
        if(keys != null && keys.size()>0){
            for(String key:keys){
                byte [] objectData = redisCacheClient.get(key);
                    Session s = (Session)SerializeUtils.deserialize(objectData);
                    sessions.add(s);
            }
        }
        return sessions;

    }

    public void setRedisCacheClient(RedisCacheClient redisCacheClient) {
        this.redisCacheClient = redisCacheClient;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
