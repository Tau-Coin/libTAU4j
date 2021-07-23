package org.libTAU4j.alerts;

import org.libTAU4j.Vectors;
import org.libTAU4j.swig.communication_last_seen_alert;

public class CommLastSeenAlert extends AbstractAlert<communication_last_seen_alert> {
    CommLastSeenAlert(communication_last_seen_alert alert) {
        super(alert);
    }

    public byte[] get_peer() {
        return Vectors.byte_vector2bytes(alert.get_peer());
    }
}
