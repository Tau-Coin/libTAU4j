/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libTAU4j.swig;

public class alerts_dropped_alert extends alert {
  private transient long swigCPtr;

  protected alerts_dropped_alert(long cPtr, boolean cMemoryOwn) {
    super(libTAU_jni.alerts_dropped_alert_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(alerts_dropped_alert obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libTAU_jni.delete_alerts_dropped_alert(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public int type() {
    return libTAU_jni.alerts_dropped_alert_type(swigCPtr, this);
  }

  public alert_category_t category() {
    return new alert_category_t(libTAU_jni.alerts_dropped_alert_category(swigCPtr, this), true);
  }

  public String what() {
    return libTAU_jni.alerts_dropped_alert_what(swigCPtr, this);
  }

  public String message() {
    return libTAU_jni.alerts_dropped_alert_message(swigCPtr, this);
  }

  public void setDropped_alerts(bitset_128 value) {
    libTAU_jni.alerts_dropped_alert_dropped_alerts_set(swigCPtr, this, bitset_128.getCPtr(value), value);
  }

  public bitset_128 getDropped_alerts() {
    long cPtr = libTAU_jni.alerts_dropped_alert_dropped_alerts_get(swigCPtr, this);
    return (cPtr == 0) ? null : new bitset_128(cPtr, false);
  }

  public final static alert_priority priority = alert_priority.swigToEnum(libTAU_jni.alerts_dropped_alert_priority_get());
  public final static int alert_type = libTAU_jni.alerts_dropped_alert_alert_type_get();
  public final static alert_category_t static_category = new alert_category_t(libTAU_jni.alerts_dropped_alert_static_category_get(), false);
}