package org.libTAU4j.alerts;

import org.libTAU4j.ErrorCode;
import org.libTAU4j.swig.session_error_alert;

/**
 * This alert is posted when the session encounters a serious error,
 * potentially fatal.
 *
 * @author gubatron
 * @author aldenml
 */
public final class SessionErrorAlert extends AbstractAlert<session_error_alert> {

    SessionErrorAlert(session_error_alert alert) {
        super(alert);
    }

    /**
     * The error code, if one is associated with this error.
     *
     * @return the error
     */
    public ErrorCode error() {
        return new ErrorCode(alert.getError());
    }
}
