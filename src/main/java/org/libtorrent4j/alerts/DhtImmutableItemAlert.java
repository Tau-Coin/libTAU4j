package org.libtorrent4j.alerts;

import org.libtorrent4j.Entry;
import org.libtorrent4j.SessionHandle;
import org.libtorrent4j.Sha256Hash;
import org.libtorrent4j.swig.dht_immutable_item_alert;

/**
 * This alert is posted as a response to a call to {@link SessionHandle#dhtGetItem(Sha256Hash)},
 * looking up immutable items in the DHT.
 *
 * @author gubatron
 * @author aldenml
 */
public final class DhtImmutableItemAlert extends AbstractAlert<dht_immutable_item_alert> {

    DhtImmutableItemAlert(dht_immutable_item_alert alert) {
        super(alert);
    }

    /**
     * The target hash of the immutable item. This must
     * match the sha-2 hash of the bencoded form of the item.
     *
     * @return the target of the original query
     */
    public Sha256Hash target() {
        return new Sha256Hash(alert.getTarget());
    }

    /**
     * the data for this item
     *
     * @return the entry returned by the DHT
     */
    public Entry item() {
        return new Entry(alert.getItem());
    }
}
