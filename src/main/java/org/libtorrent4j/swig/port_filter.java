/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class port_filter {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected port_filter(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(port_filter obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtorrent_jni.delete_port_filter(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public port_filter() {
    this(libtorrent_jni.new_port_filter__SWIG_0(), true);
  }

  public port_filter(port_filter arg0) {
    this(libtorrent_jni.new_port_filter__SWIG_1(port_filter.getCPtr(arg0), arg0), true);
  }

  public void add_rule(int first, int last, long flags) {
    libtorrent_jni.port_filter_add_rule(swigCPtr, this, first, last, flags);
  }

  public long access(int port) {
    return libtorrent_jni.port_filter_access(swigCPtr, this, port);
  }

  public final static class access_flags {
    public final static port_filter.access_flags blocked = new port_filter.access_flags("blocked", libtorrent_jni.port_filter_blocked_get());

    public final int swigValue() {
      return swigValue;
    }

    public String toString() {
      return swigName;
    }

    public static access_flags swigToEnum(int swigValue) {
      if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
        return swigValues[swigValue];
      for (int i = 0; i < swigValues.length; i++)
        if (swigValues[i].swigValue == swigValue)
          return swigValues[i];
      throw new IllegalArgumentException("No enum " + access_flags.class + " with value " + swigValue);
    }

    private access_flags(String swigName) {
      this.swigName = swigName;
      this.swigValue = swigNext++;
    }

    private access_flags(String swigName, int swigValue) {
      this.swigName = swigName;
      this.swigValue = swigValue;
      swigNext = swigValue+1;
    }

    private access_flags(String swigName, access_flags swigEnum) {
      this.swigName = swigName;
      this.swigValue = swigEnum.swigValue;
      swigNext = this.swigValue+1;
    }

    private static access_flags[] swigValues = { blocked };
    private static int swigNext = 0;
    private final int swigValue;
    private final String swigName;
  }

}
