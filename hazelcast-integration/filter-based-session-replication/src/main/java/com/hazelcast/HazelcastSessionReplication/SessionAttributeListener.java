package com.hazelcast.HazelcastSessionReplication;

import com.hazelcast.web.SessionListener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.logging.Logger;

public class SessionAttributeListener implements HttpSessionAttributeListener {

    Logger logger = Logger.getLogger(SessionListener.class.getName());

    public SessionAttributeListener() {
        logger.info("ibrahim");
    }

    public void attributeAdded(HttpSessionBindingEvent se) {
        HttpSession session = se.getSession();
        String id = session.getId();
        String name = se.getName();
        String value = (String) se.getValue();
        String source = se.getSource().getClass().getName();
        String message = new StringBuffer("Attribute bound to session in ")
                .append(source).append("\nThe attribute name: ").append(name)
                .append("\n").append("The attribute value:").append(value)
                .append("\n").append("The session ID: ").append(id).toString();

        logger.info(message);
    }

    public void attributeRemoved(HttpSessionBindingEvent se) {

        HttpSession session = se.getSession();
        String id = session.getId();
        String name = se.getName();
        if (name == null)
            name = "Unknown";
        String value = (String) se.getValue();
        String source = se.getSource().getClass().getName();
        String message = new StringBuffer("Attribute unbound from session in ")
                .append(source).append("\nThe attribute name: ").append(name)
                .append("\n").append("The attribute value: ").append(value)
                .append("\n").append("The session ID: ").append(id).toString();

       logger.info(message);
    }

    public void attributeReplaced(HttpSessionBindingEvent se) {

        String source = se.getSource().getClass().getName();
        String message = new StringBuffer("Attribute replaced in session  ")
                .append(source).toString();
        logger.info(message);
    }
}

