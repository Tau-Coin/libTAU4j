/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class performance_alert extends torrent_alert {
  private transient long swigCPtr;

  protected performance_alert(long cPtr, boolean cMemoryOwn) {
    super(libtorrent_jni.performance_alert_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(performance_alert obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtorrent_jni.delete_performance_alert(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public int type() {
    return libtorrent_jni.performance_alert_type(swigCPtr, this);
  }

  public alert_category_t category() {
    return new alert_category_t(libtorrent_jni.performance_alert_category(swigCPtr, this), true);
  }

  public String what() {
    return libtorrent_jni.performance_alert_what(swigCPtr, this);
  }

  public String message() {
    return libtorrent_jni.performance_alert_message(swigCPtr, this);
  }

  public performance_alert.performance_warning_t getWarning_code() {
    return performance_alert.performance_warning_t.swigToEnum(libtorrent_jni.performance_alert_warning_code_get(swigCPtr, this));
  }

  public final static class performance_warning_t {
    public final static performance_alert.performance_warning_t outstanding_disk_buffer_limit_reached = new performance_alert.performance_warning_t("outstanding_disk_buffer_limit_reached");
    public final static performance_alert.performance_warning_t outstanding_request_limit_reached = new performance_alert.performance_warning_t("outstanding_request_limit_reached");
    public final static performance_alert.performance_warning_t upload_limit_too_low = new performance_alert.performance_warning_t("upload_limit_too_low");
    public final static performance_alert.performance_warning_t download_limit_too_low = new performance_alert.performance_warning_t("download_limit_too_low");
    public final static performance_alert.performance_warning_t send_buffer_watermark_too_low = new performance_alert.performance_warning_t("send_buffer_watermark_too_low");
    public final static performance_alert.performance_warning_t too_many_optimistic_unchoke_slots = new performance_alert.performance_warning_t("too_many_optimistic_unchoke_slots");
    public final static performance_alert.performance_warning_t too_high_disk_queue_limit = new performance_alert.performance_warning_t("too_high_disk_queue_limit");
    public final static performance_alert.performance_warning_t aio_limit_reached = new performance_alert.performance_warning_t("aio_limit_reached");
    public final static performance_alert.performance_warning_t too_few_outgoing_ports = new performance_alert.performance_warning_t("too_few_outgoing_ports", libtorrent_jni.performance_alert_too_few_outgoing_ports_get());
    public final static performance_alert.performance_warning_t too_few_file_descriptors = new performance_alert.performance_warning_t("too_few_file_descriptors");
    public final static performance_alert.performance_warning_t num_warnings = new performance_alert.performance_warning_t("num_warnings");

    public final int swigValue() {
      return swigValue;
    }

    public String toString() {
      return swigName;
    }

    public static performance_warning_t swigToEnum(int swigValue) {
      if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
        return swigValues[swigValue];
      for (int i = 0; i < swigValues.length; i++)
        if (swigValues[i].swigValue == swigValue)
          return swigValues[i];
      throw new IllegalArgumentException("No enum " + performance_warning_t.class + " with value " + swigValue);
    }

    private performance_warning_t(String swigName) {
      this.swigName = swigName;
      this.swigValue = swigNext++;
    }

    private performance_warning_t(String swigName, int swigValue) {
      this.swigName = swigName;
      this.swigValue = swigValue;
      swigNext = swigValue+1;
    }

    private performance_warning_t(String swigName, performance_warning_t swigEnum) {
      this.swigName = swigName;
      this.swigValue = swigEnum.swigValue;
      swigNext = this.swigValue+1;
    }

    private static performance_warning_t[] swigValues = { outstanding_disk_buffer_limit_reached, outstanding_request_limit_reached, upload_limit_too_low, download_limit_too_low, send_buffer_watermark_too_low, too_many_optimistic_unchoke_slots, too_high_disk_queue_limit, aio_limit_reached, too_few_outgoing_ports, too_few_file_descriptors, num_warnings };
    private static int swigNext = 0;
    private final int swigValue;
    private final String swigName;
  }

  public final static alert_priority priority = alert_priority.swigToEnum(libtorrent_jni.performance_alert_priority_get());
  public final static int alert_type = libtorrent_jni.performance_alert_alert_type_get();
  public final static alert_category_t static_category = new alert_category_t(libtorrent_jni.performance_alert_static_category_get(), false);
}
