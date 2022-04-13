package org.libTAU4j.alerts;

import org.libTAU4j.Address;
import org.libTAU4j.swig.address;
import org.libTAU4j.swig.listen_succeeded_alert;

/**
 * This alert is posted when the listen port succeeds to be opened on a
 * particular interface. {@link #address()} and {@link #port()} is the
 * endpoint that successfully was opened for listening.
 *
 * @author gubatron
 * @author aldenml
 */
public final class ListenSucceededAlert extends AbstractAlert<listen_succeeded_alert> {

    Address ads;
    int port;
    ListenSucceededAlert(listen_succeeded_alert alert) {
        super(alert);
        this.ads= new Address(alert.get_address());
        this.port= alert.getPort();
    }

    /**
     * The address libTAU4j ended up listening on. This address
     * refers to the local interface.
     *
     * @return the address ended up listening on
     */
    public Address address() {
        return this.ads;
    }

    /**
     * The port libTAU4j ended up listening on.
     *
     * @return the port
     */
    public int port() {
        return this.port;
    }

    /**
     * the type of listen socket this alert refers to.
     *
     * @return the socket type
     */
    public SocketType socketType() {
        return SocketType.fromSwig(alert.get_socket_type());
    }
}
