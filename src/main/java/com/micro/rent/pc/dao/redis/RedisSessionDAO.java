package com.micro.rent.pc.dao.redis;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import com.micro.rent.pc.util.JedisUtil;


/**
 *
 * @author user
 *
 */
public class RedisSessionDAO extends AbstractSessionDAO {
    private String keyPrefix = "shiro_session:";
    private Long MIN = 30L;

    @Override
    public void delete(Session session) {
        String key = getSessionKey(session);
        JedisUtil.del(key);
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.update(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        String key = keyPrefix + sessionId;
        return (Session) JedisUtil.get(key);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<String> set = JedisUtil.keys(keyPrefix + "*");
        Set<Session> sessions = new HashSet<Session>();
        for (String key : set) {
            sessions.add((Session) JedisUtil.get(key));
        }
        return sessions;
    }

    private String getSessionKey(Session session) {
        return keyPrefix + session.getId();
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        String key = getSessionKey(session);
        JedisUtil.set(key, session, MIN, TimeUnit.MINUTES);
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

}
