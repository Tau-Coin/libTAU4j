package org.libTAU4j;

import org.libTAU4j.alerts.SessionStatsAlert;

/**
 * @author gubatron
 * @author aldenml
 */
public final class SessionStats {

    // these are the channels we keep stats for
    private static final int UPLOAD_PAYLOAD = 0;
    private static final int UPLOAD_PROTOCOL = 1;
    private static final int UPLOAD_IP_PROTOCOL = 2;
    private static final int DOWNLOAD_PAYLOAD = 3;
    private static final int DOWNLOAD_PROTOCOL = 4;
    private static final int DOWNLOAD_IP_PROTOCOL = 5;
    private static final int DHT_TOTAL_DOWNLOAD = 6;
    private static final int DHT_TOTAL_UPLOAD = 7;
    private static final int DHT_MESSAGES_IN = 8;
    private static final int DHT_MESSAGES_OUT = 9;
    private static final int DHT_MESSAGES_IN_DROPPED = 10;
    private static final int DHT_MESSAGES_OUT_DROPPED = 11;
    private static final int NUM_AVERAGES = 12;

    private final Average[] stat;

    private long lastTickTime;
    private long dhtNodes;

	private long invokedRequests;

    SessionStats() {
        this.stat = new Average[NUM_AVERAGES];
        for (int i = 0; i < this.stat.length; i++) {
            this.stat[i] = new Average();
        }
    }

    public long totalDownload() {
        long totalDownloadIUD = stat[DOWNLOAD_PAYLOAD].total() +
                stat[DOWNLOAD_PROTOCOL].total() +
                stat[DOWNLOAD_IP_PROTOCOL].total() +
                stat[DHT_TOTAL_DOWNLOAD].total();

        long totalDownloadLL = stat[DHT_MESSAGES_IN].total() * 20; 

        return totalDownloadIUD + totalDownloadLL;
    }

    public long totalUpload() {
        long totalUploadIUD = stat[UPLOAD_PAYLOAD].total() +
                stat[UPLOAD_PROTOCOL].total() +
                stat[UPLOAD_IP_PROTOCOL].total() +
                stat[DHT_TOTAL_UPLOAD].total();

        long totalUploadLL = stat[DHT_MESSAGES_OUT].total() * 30; 

        return totalUploadIUD + totalUploadLL;
    }

    public long downloadRate() {
        return stat[DOWNLOAD_PAYLOAD].rate() +
                stat[DOWNLOAD_PROTOCOL].rate() +
                stat[DOWNLOAD_IP_PROTOCOL].rate() +
                stat[DHT_TOTAL_DOWNLOAD].rate();
    }

    public long uploadRate() {
        return stat[UPLOAD_PAYLOAD].rate() +
                stat[UPLOAD_PROTOCOL].rate() +
                stat[UPLOAD_IP_PROTOCOL].rate() +
                stat[DHT_TOTAL_UPLOAD].rate();
    }

    // dht
    public long dhtNodes() {
        return dhtNodes;
    }

	public long invokedRequests() {
		return invokedRequests;
	}

    public long dhtTotalDownload() {
        return stat[DHT_TOTAL_DOWNLOAD].total();
    }

    public long dhtTotalDownloadRate() {
        return stat[DHT_TOTAL_DOWNLOAD].rate();
    }

    public long dhtTotalUpload() {
        return stat[DHT_TOTAL_UPLOAD].total();
    }

    public long dhtTotalUploadRate() {
        return stat[DHT_TOTAL_UPLOAD].rate();
    }

    public long dhtMessagesIn() {
        return stat[DHT_MESSAGES_IN].total();
    }

    public long dhtMessagesInRate() {
        return stat[DHT_MESSAGES_IN].rate();
    }

    public long dhtMessagesOut() {
        return stat[DHT_MESSAGES_OUT].total();
    }

    public long dhtMessagesOutRate() {
        return stat[DHT_MESSAGES_OUT].rate();
    }

    public long dhtMessagesInDropped() {
        return stat[DHT_MESSAGES_IN_DROPPED].total();
    }

    public long dhtMessagesInDroppedRate() {
        return stat[DHT_MESSAGES_IN_DROPPED].rate();
    }

    public long dhtMessagesOutDropped() {
        return stat[DHT_MESSAGES_OUT_DROPPED].total();
    }

    public long dhtMessagesOutDroppedRate() {
        return stat[DHT_MESSAGES_OUT_DROPPED].rate();
    }

    void update(SessionStatsAlert alert) {
        long now = System.currentTimeMillis();
        long tickIntervalMs = now - lastTickTime;
        lastTickTime = now;

        long received = alert.value(StatsMetric.NET_RECV_BYTES_COUNTER_INDEX);
        long payload = alert.value(StatsMetric.NET_RECV_PAYLOAD_BYTES_COUNTER_INDEX);
        long protocol = received - payload;
        long ip = alert.value(StatsMetric.NET_RECV_IP_OVERHEAD_BYTES_COUNTER_INDEX);

        // add dht total download
        long dht_total_download = alert.value(StatsMetric.DHT_TOTAL_DOWNLOAD_COUNTER_INDEX);
        long dht_total_upload = alert.value(StatsMetric.DHT_TOTAL_UPLOAD_COUNTER_INDEX);

        // add dht messages num
        long dht_messages_in = alert.value(StatsMetric.DHT_MESSAGES_IN_INDEX);
        long dht_messages_out = alert.value(StatsMetric.DHT_MESSAGES_OUT_INDEX);

        long dht_messages_in_dropped = alert.value(StatsMetric.DHT_MESSAGES_IN_DROPPED_INDEX);
        long dht_messages_out_dropped = alert.value(StatsMetric.DHT_MESSAGES_OUT_DROPPED_INDEX);

        payload -= stat[DOWNLOAD_PAYLOAD].total();
        protocol -= stat[DOWNLOAD_PROTOCOL].total();
        ip -= stat[DOWNLOAD_IP_PROTOCOL].total();

        // dht
        dht_total_download -= stat[DHT_TOTAL_DOWNLOAD].total();
        dht_total_upload -= stat[DHT_TOTAL_UPLOAD].total();

        dht_messages_in -= stat[DHT_MESSAGES_IN].total();
        dht_messages_out -= stat[DHT_MESSAGES_OUT].total();

        dht_messages_in_dropped -= stat[DHT_MESSAGES_IN_DROPPED].total();
        dht_messages_out_dropped -= stat[DHT_MESSAGES_OUT_DROPPED].total();

        stat[DOWNLOAD_PAYLOAD].add(payload);
        stat[DOWNLOAD_PROTOCOL].add(protocol);
        stat[DOWNLOAD_IP_PROTOCOL].add(ip);

        stat[DHT_TOTAL_DOWNLOAD].add(dht_total_download);
        stat[DHT_TOTAL_UPLOAD].add(dht_total_upload);

        stat[DHT_MESSAGES_IN].add(dht_messages_in);
        stat[DHT_MESSAGES_OUT].add(dht_messages_out);
        stat[DHT_MESSAGES_IN_DROPPED].add(dht_messages_in_dropped);
        stat[DHT_MESSAGES_OUT_DROPPED].add(dht_messages_out_dropped);

        long sent = alert.value(StatsMetric.NET_SENT_BYTES_COUNTER_INDEX);
        payload = alert.value(StatsMetric.NET_SENT_PAYLOAD_BYTES_COUNTER_INDEX);
        protocol = sent - payload;
        ip = alert.value(StatsMetric.NET_SENT_IP_OVERHEAD_BYTES_COUNTER_INDEX);

        payload -= stat[UPLOAD_PAYLOAD].total();
        protocol -= stat[UPLOAD_PROTOCOL].total();
        ip -= stat[UPLOAD_IP_PROTOCOL].total();
        stat[UPLOAD_PAYLOAD].add(payload);
        stat[UPLOAD_PROTOCOL].add(protocol);
        stat[UPLOAD_IP_PROTOCOL].add(ip);

        tick(tickIntervalMs);
        dhtNodes = alert.value(StatsMetric.DHT_NODES_GAUGE_INDEX);
		invokedRequests = alert.value(StatsMetric.DHT_INVOKED_REQUESTS_INDEX);
    }

    void clear() {
        for (int i = 0; i < NUM_AVERAGES; ++i) {
            stat[i].clear();
        }
        dhtNodes = 0;
		invokedRequests = 0;
    }

    // should be called once every second
    private void tick(long tickIntervalMs) {
        for (int i = 0; i < NUM_AVERAGES; ++i) {
            stat[i].tick(tickIntervalMs);
        }
    }

    private static final class Average {

        // total counters
        private long totalCounter;

        // the accumulator for this second.
        private long counter;

        // sliding average
        private long averageSec5;

        public Average() {
        }

        public void add(long count) {
            counter += count;
            totalCounter += count;
        }

        // should be called once every second
        public void tick(long tickIntervalMs) {
            if (tickIntervalMs >= 1) {
                long sample = (counter * 1000) / tickIntervalMs;
                averageSec5 = (averageSec5 * 4) / 5 + sample / 5;
                counter = 0;
            }
        }

        public long rate() {
            return averageSec5;
        }

        public long total() {
            return totalCounter;
        }

        public void clear() {
            counter = 0;
            averageSec5 = 0;
            totalCounter = 0;
        }
    }
}
