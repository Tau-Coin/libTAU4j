/*
 * Copyright (c) 2018-2021, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.libTAU4j;

import org.libTAU4j.swig.libTAU;
import org.libTAU4j.swig.stats_metric;
import org.libTAU4j.swig.stats_metric_vector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gubatron
 * @author aldenml
 */
public final class LibTorrent {

    private LibTorrent() {
    }

    /**
     * The version string as reported by libTAU
     *
     * @return the version string
     */
    public static String version() {
        return libTAU.version();
    }

    /**
     * The git revision of libTAU the native library is using.
     * <p>
     * This is not the internal revision libTAU reports, since
     * that string is updated from time to time. This library can be
     * using an up to date revision, this string is manually
     * hardcoded in each version of libTAU.
     *
     * @return the git revision
     */
    public static String revision() {
        return "385602ab01900d6cd96af5c40d1dd8a0005ee827";
    }

    /**
     * Returns the version of boost used in the build.
     *
     * @return the build boost version.
     */
    public static String boostVersion() {
        return libTAU.boost_lib_version();
    }

    /**
     * Returns the version of openssl used in the build.
     *
     * @return the build openssl version.
     */
    public static String opensslVersion() {
        return libTAU.openssl_version_text();
    }

    /**
     * Version of libTAU. It should match your maven
     * artifact version.
     *
     * @return libTAU version.
     */
    public static String libTAU4jVersion() {
        return "2.0.3-19";
    }

    /**
     * This free function returns the list of available metrics exposed by
     * libTAU's statistics API. Each metric has a name and a *value index*.
     * The value index is the index into the array in session_stats_alert where
     * this metric's value can be found when the session stats is sampled (by
     * calling post_session_stats()).
     *
     * @return the list of all metrics
     */
    public static List<StatsMetric> sessionStatsMetrics() {
        stats_metric_vector v = libTAU.session_stats_metrics();

        ArrayList<StatsMetric> l = new ArrayList<StatsMetric>((int)v.size());

        for (int i = 0; i < (int)v.size(); i++) {
            l.add(new StatsMetric(v.get(i)));
        }

        return l;
    }

    /**
     * given a name of a metric, this function returns the counter index of it,
     * or -1 if it could not be found. The counter index is the index into the
     * values array returned by session_stats_alert.
     *
     * @param name the name of the metric
     * @return the index of the metric
     */
    public static int findMetricIdx(String name) {
        return libTAU.find_metric_idx_ex(name);
    }

    /**
     * If the native library is an ARM architecture variant, returns true
     * if the running platform has NEON support.
     *
     * @return true if the running platform has NEON support
     */
    public static boolean hasArmNeonSupport() {
        return libTAU.arm_neon_support();
    }
}
