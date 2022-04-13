package org.libTAU4j.alerts;

import org.libTAU4j.swig.communication_log_alert;

/**
 * This alert is posted by some session wide event. Its main purpose is
 * trouble shooting and debugging. It's not enabled by the default alert
 * mask and is enabled by the ``alert::session_log_notification`` bit.
 *
 * @author gubatron
 * @author aldenml
 */
public final class CommLogAlert extends AbstractAlert<communication_log_alert> {

    String msg;
    CommLogAlert(communication_log_alert alert) {
        super(alert);
        this.msg = alert.log_message();
    }

    /**
     * Returns the log message.
     *
     * @return the message
     */
    public String logMessage() {
        return this.msg;
    }
}
