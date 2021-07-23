package org.libTAU4j.alerts;

import org.libTAU4j.Vectors;
import org.libTAU4j.swig.communication_syncing_message_alert;

/**
 * This alert is posted by some session wide event. Its main purpose is
 * trouble shooting and debugging. It's not enabled by the default alert
 * mask and is enabled by the ``alert::session_log_notification`` bit.
 *
 * @author gubatron
 * @author aldenml
 */
public final class CommSyncMsgAlert extends AbstractAlert<communication_syncing_message_alert> {

    CommSyncMsgAlert(communication_syncing_message_alert alert) {
        super(alert);
    }

    public byte[] get_peer() {
        return Vectors.byte_vector2bytes(alert.get_peer());
    }

    public byte[] getSyncing_msg_hash() {
        return Vectors.byte_vector2bytes(alert.getSyncing_msg_hash().to_bytes());
    }
}
