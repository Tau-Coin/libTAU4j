package org.libTAU4j.alerts;

import org.libTAU4j.swig.session_stats_alert;
import org.libTAU4j.LibTorrent;
import org.libTAU4j.SessionHandle;

/**
 * The {@link SessionStatsAlert} is posted when the user requests session
 * statistics by calling {@link SessionHandle#postSessionStats()}
 * on the session object. Its category is
 * {@link org.libTAU4j.swig.alert#status_notification},
 * but it is not subject to filtering, since it's only manually posted anyway.
 *
 * @author gubatron
 * @author aldenml
 */
public final class SessionStatsAlert extends AbstractAlert<session_stats_alert> {

    SessionStatsAlert(session_stats_alert alert) {
        super(alert);
    }

    /**
     * The internal values are a mix of counters and gauges, which
     * meanings can be queries via the
     * {@link LibTorrent#sessionStatsMetrics()} function.
     * <p>
     * The mapping from a specific metric to an index into this array is constant for a
     * specific version of libTAU4j, but may differ for other versions. The intended
     * usage is to request the mapping, i.e. call
     * {@link LibTorrent#sessionStatsMetrics()}, once
     * on startup, and then use that mapping to interpret these values throughout
     * the process's runtime.
     *
     * @return the value
     */
    public long value(int index) {
        return alert.get_value(index);
    }
}
