/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libTAU4j.swig;

public class peer_class_type_filter {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected peer_class_type_filter(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(peer_class_type_filter obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libTAU_jni.delete_peer_class_type_filter(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public peer_class_type_filter() {
    this(libTAU_jni.new_peer_class_type_filter(), true);
  }

  public long apply(peer_class_type_filter.socket_type_t st, long peer_class_mask) {
    return libTAU_jni.peer_class_type_filter_apply(swigCPtr, this, st.swigValue(), peer_class_mask);
  }

  public final static class socket_type_t {
    public final static peer_class_type_filter.socket_type_t tcp_socket = new peer_class_type_filter.socket_type_t("tcp_socket", libTAU_jni.peer_class_type_filter_tcp_socket_get());
    public final static peer_class_type_filter.socket_type_t utp_socket = new peer_class_type_filter.socket_type_t("utp_socket");
    public final static peer_class_type_filter.socket_type_t ssl_tcp_socket = new peer_class_type_filter.socket_type_t("ssl_tcp_socket");
    public final static peer_class_type_filter.socket_type_t ssl_utp_socket = new peer_class_type_filter.socket_type_t("ssl_utp_socket");
    public final static peer_class_type_filter.socket_type_t i2p_socket = new peer_class_type_filter.socket_type_t("i2p_socket");
    public final static peer_class_type_filter.socket_type_t num_socket_types = new peer_class_type_filter.socket_type_t("num_socket_types");

    public final int swigValue() {
      return swigValue;
    }

    public String toString() {
      return swigName;
    }

    public static socket_type_t swigToEnum(int swigValue) {
      if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
        return swigValues[swigValue];
      for (int i = 0; i < swigValues.length; i++)
        if (swigValues[i].swigValue == swigValue)
          return swigValues[i];
      throw new IllegalArgumentException("No enum " + socket_type_t.class + " with value " + swigValue);
    }

    private socket_type_t(String swigName) {
      this.swigName = swigName;
      this.swigValue = swigNext++;
    }

    private socket_type_t(String swigName, int swigValue) {
      this.swigName = swigName;
      this.swigValue = swigValue;
      swigNext = swigValue+1;
    }

    private socket_type_t(String swigName, socket_type_t swigEnum) {
      this.swigName = swigName;
      this.swigValue = swigEnum.swigValue;
      swigNext = this.swigValue+1;
    }

    private static socket_type_t[] swigValues = { tcp_socket, utp_socket, ssl_tcp_socket, ssl_utp_socket, i2p_socket, num_socket_types };
    private static int swigNext = 0;
    private final int swigValue;
    private final String swigName;
  }

}