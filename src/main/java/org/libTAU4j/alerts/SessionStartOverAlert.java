package org.libTAU4j.alerts;

import org.libTAU4j.swig.session_start_over_alert;

/**
 * This alert is posted by some session wide event. Its main purpose is
 * trouble shooting and debugging. It's not enabled by the default alert
 * mask and is enabled by the ``alert::session_log_notification`` bit.
 *
 * @author gubatron
 * @author aldenml
 */
public final class SessionStartOverAlert extends AbstractAlert<session_start_over_alert> {

    SessionStartOverAlert(session_start_over_alert alert) {
        super(alert);
    }
}
