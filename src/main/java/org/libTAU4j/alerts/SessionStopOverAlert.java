package org.libTAU4j.alerts;

import org.libTAU4j.swig.session_stop_over_alert;

/**
 * This alert is posted by some session wide event. Its main purpose is
 * trouble shooting and debugging. It's not enabled by the default alert
 * mask and is enabled by the ``alert::session_log_notification`` bit.
 *
 * @author gubatron
 * @author aldenml
 */
public final class SessionStopOverAlert extends AbstractAlert<session_stop_over_alert> {

    SessionStopOverAlert(session_stop_over_alert alert) {
        super(alert);
    }
}
