package org.libTAU4j.alerts;

import org.libTAU4j.Address;
import org.libTAU4j.ErrorCode;
import org.libTAU4j.Operation;
import org.libTAU4j.swig.listen_failed_alert;

/**
 * This alert is generated when none of the ports, given in the port range, to
 * session can be opened for listening. The {@link #listenInterface()} member is the
 * interface and port that failed, {@link #error()} is the error code describing
 * the failure.
 * <p>
 * In the case an endpoint was created before generating the alert, it is
 * represented by ``address`` and ``port``. The combinations of socket type
 * and operation in which such address and port are not valid are:
 * accept  - i2p
 * accept  - socks5
 * enum_if - tcp
 * <p>
 * libTAU4j may sometimes try to listen on port 0, if all other ports failed.
 * Port 0 asks the operating system to pick a port that's free). If that fails
 * you may see a {@link ListenFailedAlert} with port 0 even if you didn't ask to
 * listen on it.
 *
 * @author gubatron
 * @author aldenml
 */
public final class ListenFailedAlert extends AbstractAlert<listen_failed_alert> {

    Address ads;
    int port;
    String listen_interface;
    ErrorCode ec;

    ListenFailedAlert(listen_failed_alert alert) {
        super(alert);
        this.ads = new Address(alert.get_address());
        this.port= alert.getPort();
        this.listen_interface = alert.listen_interface();
        this.ec = new ErrorCode(alert.getError());
    }

    /**
     * The interface libTAU4j attempted to listen on that failed.
     *
     * @return the listen interface (as string).
     */
    public String listenInterface() {
        return this.listen_interface;
    }

    /**
     * The error the system returned.
     *
     * @return the error.
     */
    public ErrorCode error() {
        return this.ec;
    }

    /**
     * The specific low level operation that failed.
     *
     * @return the operation.
     */
    public Operation operation() {
        return Operation.fromSwig(alert.getOp());
    }

    /**
     * The type of listen socket this alert refers to.
     *
     * @return the socket type.
     */
    public SocketType socketType() {
        return SocketType.fromSwig(alert.get_socket_type());
    }

    /**
     * The address libTAU4j attempted to listen on.
     * See alert's documentation for validity of this value.
     *
     * @return the address attempted to listen on.
     */
    public Address address() {
        return this.ads;
    }

    /**
     * The port libTAU4j attempted to listen on
     * see alert's documentation for validity of this value.
     *
     * @return the port.
     */
    public int port() {
        return this.port;
    }
}
