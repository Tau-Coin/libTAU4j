package org.libtorrent4j.alerts;

import org.libtorrent4j.PeerRequest;
import org.libtorrent4j.swig.invalid_request_alert;

/**
 * This is a debug alert that is generated by an incoming invalid piece request.
 * ``ip`` is the address of the peer and the ``request`` is the actual incoming
 * request from the peer. See peer_request for more info.
 *
 * @author gubatron
 * @author aldenml
 */
public final class InvalidRequestAlert extends PeerAlert<invalid_request_alert> {

    public InvalidRequestAlert(invalid_request_alert alert) {
        super(alert);
    }

    public PeerRequest getRequest() {
        return new PeerRequest(alert.getRequest());
    }
}
