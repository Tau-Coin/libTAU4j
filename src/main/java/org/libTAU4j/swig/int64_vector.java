/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libTAU4j.swig;

public class int64_vector {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected int64_vector(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(int64_vector obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libTAU_jni.delete_int64_vector(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public int64_vector() {
    this(libTAU_jni.new_int64_vector__SWIG_0(), true);
  }

  public int64_vector(long n) {
    this(libTAU_jni.new_int64_vector__SWIG_1(n), true);
  }

  public long size() {
    return libTAU_jni.int64_vector_size(swigCPtr, this);
  }

  public long capacity() {
    return libTAU_jni.int64_vector_capacity(swigCPtr, this);
  }

  public void reserve(long n) {
    libTAU_jni.int64_vector_reserve(swigCPtr, this, n);
  }

  public boolean isEmpty() {
    return libTAU_jni.int64_vector_isEmpty(swigCPtr, this);
  }

  public void clear() {
    libTAU_jni.int64_vector_clear(swigCPtr, this);
  }

  public void add(long x) {
    libTAU_jni.int64_vector_add(swigCPtr, this, x);
  }

  public long get(int i) {
    return libTAU_jni.int64_vector_get(swigCPtr, this, i);
  }

  public void set(int i, long val) {
    libTAU_jni.int64_vector_set(swigCPtr, this, i, val);
  }

}
