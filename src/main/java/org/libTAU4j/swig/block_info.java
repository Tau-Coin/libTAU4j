/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libTAU4j.swig;

public class block_info {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected block_info(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(block_info obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libTAU_jni.delete_block_info(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void set_peer(tcp_endpoint ep) {
    libTAU_jni.block_info_set_peer(swigCPtr, this, tcp_endpoint.getCPtr(ep), ep);
  }

  public tcp_endpoint peer() {
    return new tcp_endpoint(libTAU_jni.block_info_peer(swigCPtr, this), true);
  }

  public void setBytes_progress(long value) {
    libTAU_jni.block_info_bytes_progress_set(swigCPtr, this, value);
  }

  public long getBytes_progress() {
    return libTAU_jni.block_info_bytes_progress_get(swigCPtr, this);
  }

  public void setBlock_size(long value) {
    libTAU_jni.block_info_block_size_set(swigCPtr, this, value);
  }

  public long getBlock_size() {
    return libTAU_jni.block_info_block_size_get(swigCPtr, this);
  }

  public void setState(long value) {
    libTAU_jni.block_info_state_set(swigCPtr, this, value);
  }

  public long getState() {
    return libTAU_jni.block_info_state_get(swigCPtr, this);
  }

  public void setNum_peers(long value) {
    libTAU_jni.block_info_num_peers_set(swigCPtr, this, value);
  }

  public long getNum_peers() {
    return libTAU_jni.block_info_num_peers_get(swigCPtr, this);
  }

  public block_info() {
    this(libTAU_jni.new_block_info(), true);
  }

  public final static class block_state_t {
    public final static block_info.block_state_t none = new block_info.block_state_t("none");
    public final static block_info.block_state_t requested = new block_info.block_state_t("requested");
    public final static block_info.block_state_t writing = new block_info.block_state_t("writing");
    public final static block_info.block_state_t finished = new block_info.block_state_t("finished");

    public final int swigValue() {
      return swigValue;
    }

    public String toString() {
      return swigName;
    }

    public static block_state_t swigToEnum(int swigValue) {
      if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
        return swigValues[swigValue];
      for (int i = 0; i < swigValues.length; i++)
        if (swigValues[i].swigValue == swigValue)
          return swigValues[i];
      throw new IllegalArgumentException("No enum " + block_state_t.class + " with value " + swigValue);
    }

    private block_state_t(String swigName) {
      this.swigName = swigName;
      this.swigValue = swigNext++;
    }

    private block_state_t(String swigName, int swigValue) {
      this.swigName = swigName;
      this.swigValue = swigValue;
      swigNext = swigValue+1;
    }

    private block_state_t(String swigName, block_state_t swigEnum) {
      this.swigName = swigName;
      this.swigValue = swigEnum.swigValue;
      swigNext = this.swigValue+1;
    }

    private static block_state_t[] swigValues = { none, requested, writing, finished };
    private static int swigNext = 0;
    private final int swigValue;
    private final String swigName;
  }

}
