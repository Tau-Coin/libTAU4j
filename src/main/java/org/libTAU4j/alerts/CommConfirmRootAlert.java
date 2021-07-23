package org.libTAU4j.alerts;

import org.libTAU4j.Vectors;
import org.libTAU4j.swig.communication_confirmation_root_alert;
import org.libTAU4j.swig.sha256_hash_vector;

import java.util.ArrayList;

/**
 * This alert is posted by some session wide event. Its main purpose is
 * trouble shooting and debugging. It's not enabled by the default alert
 * mask and is enabled by the ``alert::session_log_notification`` bit.
 *
 * @author gubatron
 * @author aldenml
 */
public final class CommConfirmRootAlert extends AbstractAlert<communication_confirmation_root_alert> {

    CommConfirmRootAlert(communication_confirmation_root_alert alert) {
        super(alert);
    }

    public byte[] get_peer() {
        return Vectors.byte_vector2bytes(alert.get_peer());
    }

    public ArrayList<byte[]> getConfirmation_roots() {
        ArrayList<byte[]> hashList = new ArrayList<>();
        sha256_hash_vector hash_vector = alert.getConfirmation_roots();
        if (!hash_vector.isEmpty()) {
            long size = hash_vector.size();
            for (int i = 0; i < size; ++i) {
                hashList.add(Vectors.byte_vector2bytes(hash_vector.get(i).to_bytes()));
            }
        }

        return hashList;
    }

}
