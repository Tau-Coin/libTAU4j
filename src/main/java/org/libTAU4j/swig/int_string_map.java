/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libTAU4j.swig;

public class int_string_map {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected int_string_map(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(int_string_map obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libTAU_jni.delete_int_string_map(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public int_string_map() {
    this(libTAU_jni.new_int_string_map__SWIG_0(), true);
  }

  public int_string_map(int_string_map arg0) {
    this(libTAU_jni.new_int_string_map__SWIG_1(int_string_map.getCPtr(arg0), arg0), true);
  }

  public long size() {
    return libTAU_jni.int_string_map_size(swigCPtr, this);
  }

  public boolean empty() {
    return libTAU_jni.int_string_map_empty(swigCPtr, this);
  }

  public void clear() {
    libTAU_jni.int_string_map_clear(swigCPtr, this);
  }

  public String get(int key) {
    return libTAU_jni.int_string_map_get(swigCPtr, this, key);
  }

  public void set(int key, String x) {
    libTAU_jni.int_string_map_set(swigCPtr, this, key, x);
  }

  public void del(int key) {
    libTAU_jni.int_string_map_del(swigCPtr, this, key);
  }

  public boolean has_key(int key) {
    return libTAU_jni.int_string_map_has_key(swigCPtr, this, key);
  }

}
