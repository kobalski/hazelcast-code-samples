package com.hazelcast.HazelcastSessionReplication;

import com.hazelcast.web.WebFilter;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import java.util.logging.Logger;

public class MyServletContextAttributesListener implements ServletContextAttributeListener {

    Logger logger = Logger.getLogger(MyServletContextAttributesListener.class.getName());

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        logger.info(servletContextAttributeEvent.getName());
        if (servletContextAttributeEvent.getName().equals(WebFilter.WEB_FILTER_ATTRIBUTE_KEY)) {
            SessionAttributeListener attributeListener = new SessionAttributeListener();
            servletContextAttributeEvent.getServletContext().addListener(attributeListener);
        }
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {

    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {

    }
}
