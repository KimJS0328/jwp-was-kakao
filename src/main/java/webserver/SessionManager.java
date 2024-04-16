package webserver;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    private static final Map<String, Session> SESSIONS = new HashMap<>();

    public static void addSession(Session session) {
        SESSIONS.put(session.getId(), session);
    }

    public static Session findSession(String id) {
        return SESSIONS.get(id);
    }

    public static void removeSession(String id) {
        SESSIONS.remove(id);
    }

    private SessionManager() {
    }
}
