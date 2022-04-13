package org.libTAU4j.alerts;

import org.libTAU4j.PortmapProtocol;
import org.libTAU4j.PortmapTransport;
import org.libTAU4j.swig.portmap_alert;

/**
 * This alert is generated when a NAT router was successfully found and
 * a port was successfully mapped on it. On a NAT:ed network with a NAT-PMP
 * capable router, this is typically generated once when mapping the TCP
 * port and, if DHT is enabled, when the UDP port is mapped.
 *
 * @author gubatron
 * @author aldenml
 */
public final class PortmapAlert extends AbstractAlert<portmap_alert> {

    int mpg;
    int ep;

    PortmapAlert(portmap_alert alert) {
        super(alert);
        this.mpg = alert.get_mapping();
        this.ep = alert.getExternal_port();
    }

    /**
     * refers to the mapping index of the port map that failed, i.e.
     * the index returned from add_mapping().
     *
     * @return the mapping index
     */
    public int mapping() {
        return this.mpg;
    }

    /**
     * the external port allocated for the mapping.
     *
     * @return the external port
     */
    public int externalPort() {
        return this.ep;
    }

    public PortmapTransport mapTransport() {
        return PortmapTransport.fromSwig(alert.getMap_transport().swigValue());
    }

    /**
     * The protocol this mapping was for.
     *
     * @return the mapping protocol
     */
    public PortmapProtocol mapProtocol() {
        return PortmapProtocol.fromSwig(alert.getMap_protocol().swigValue());
    }
}
