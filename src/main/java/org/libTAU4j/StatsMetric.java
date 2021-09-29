package org.libTAU4j;

import org.libTAU4j.swig.metric_type_t;
import org.libTAU4j.swig.stats_metric;

/**
 * Describes one statistics metric from the session.
 *
 * @author gubatron
 * @author aldenml
 */
public final class StatsMetric {

    public static final String NET_SENT_PAYLOAD_BYTES_COUNTER_NAME = "net.sent_payload_bytes";
    public static final String NET_SENT_BYTES_COUNTER_NAME = "net.sent_bytes";
    public static final String NET_SENT_IP_OVERHEAD_BYTES_COUNTER_NAME = "net.sent_ip_overhead_bytes";
    public static final String NET_RECV_PAYLOAD_BYTES_COUNTER_NAME = "net.recv_payload_bytes";
    public static final String NET_RECV_BYTES_COUNTER_NAME = "net.recv_bytes";
    public static final String NET_RECV_IP_OVERHEAD_BYTES_COUNTER_NAME = "net.recv_ip_overhead_bytes";

    // add dht download
    public static final String DHT_NODES_GAUGE_NAME = "dht.dht_nodes";

    public static final String DHT_TOTAL_DOWNLOAD_COUNTER_NAME = "dht.dht_bytes_in";
    public static final String DHT_TOTAL_UPLOAD_COUNTER_NAME = "dht.dht_bytes_out";
    public static final String DHT_MESSAGES_IN_NAME = "dht.dht_messages_in";
    public static final String DHT_MESSAGES_OUT_NAME = "dht.dht_messages_out";
    public static final String DHT_MESSAGES_IN_DROPPED_NAME = "dht.dht_messages_in_dropped";
    public static final String DHT_MESSAGES_OUT_DROPPED_NAME = "dht.dht_messages_out_dropped";
	public static final String DHT_ALLOCATED_OBSERVERS_NAME = "dht.dht_allocated_observers";
    
    public static final int NET_SENT_PAYLOAD_BYTES_COUNTER_INDEX = LibTorrent.findMetricIdx(NET_SENT_PAYLOAD_BYTES_COUNTER_NAME);
    public static final int NET_SENT_BYTES_COUNTER_INDEX = LibTorrent.findMetricIdx(NET_SENT_BYTES_COUNTER_NAME);
    public static final int NET_SENT_IP_OVERHEAD_BYTES_COUNTER_INDEX = LibTorrent.findMetricIdx(NET_SENT_IP_OVERHEAD_BYTES_COUNTER_NAME);
    public static final int NET_RECV_PAYLOAD_BYTES_COUNTER_INDEX = LibTorrent.findMetricIdx(NET_RECV_PAYLOAD_BYTES_COUNTER_NAME);
    public static final int NET_RECV_BYTES_COUNTER_INDEX = LibTorrent.findMetricIdx(NET_RECV_BYTES_COUNTER_NAME);
    public static final int NET_RECV_IP_OVERHEAD_BYTES_COUNTER_INDEX = LibTorrent.findMetricIdx(NET_RECV_IP_OVERHEAD_BYTES_COUNTER_NAME);

    // add dht
    public static final int DHT_NODES_GAUGE_INDEX = LibTorrent.findMetricIdx(DHT_NODES_GAUGE_NAME);

    public static final int DHT_TOTAL_DOWNLOAD_COUNTER_INDEX = LibTorrent.findMetricIdx(DHT_TOTAL_DOWNLOAD_COUNTER_NAME);
    public static final int DHT_TOTAL_UPLOAD_COUNTER_INDEX = LibTorrent.findMetricIdx(DHT_TOTAL_UPLOAD_COUNTER_NAME);
    public static final int DHT_MESSAGES_IN_INDEX = LibTorrent.findMetricIdx(DHT_MESSAGES_IN_NAME);
    public static final int DHT_MESSAGES_OUT_INDEX = LibTorrent.findMetricIdx(DHT_MESSAGES_OUT_NAME);
    public static final int DHT_MESSAGES_IN_DROPPED_INDEX = LibTorrent.findMetricIdx(DHT_MESSAGES_IN_DROPPED_NAME);
    public static final int DHT_MESSAGES_OUT_DROPPED_INDEX = LibTorrent.findMetricIdx(DHT_MESSAGES_OUT_DROPPED_NAME);
	public static final int DHT_ALLOCATED_OBSERVERS_INDEX = LibTorrent.findMetricIdx(DHT_ALLOCATED_OBSERVERS_NAME);

    public static final int TYPE_COUNTER = metric_type_t.counter.swigValue();
    public static final int TYPE_GAUGE = metric_type_t.gauge.swigValue();

    StatsMetric(stats_metric sm) {
        this.name = sm.get_name();
        this.valueIndex = sm.getValue_index();
        this.type = sm.getType().swigValue();
    }

    public final String name;

    public final int valueIndex;

    public final int type;

    @Override
    public String toString() {
        return name + ":" + valueIndex + ":" + typeStr();
    }

    private String typeStr() {
        String str = "unknown";

        if (type == TYPE_COUNTER) {
            str = "counter";
        } else if (type == TYPE_GAUGE) {
            str = "gauge";
        }

        return str;
    }
}
