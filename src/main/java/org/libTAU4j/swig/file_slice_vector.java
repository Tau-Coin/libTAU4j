/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libTAU4j.swig;

public class file_slice_vector {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected file_slice_vector(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(file_slice_vector obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libTAU_jni.delete_file_slice_vector(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public file_slice_vector() {
    this(libTAU_jni.new_file_slice_vector__SWIG_0(), true);
  }

  public file_slice_vector(long n) {
    this(libTAU_jni.new_file_slice_vector__SWIG_1(n), true);
  }

  public long size() {
    return libTAU_jni.file_slice_vector_size(swigCPtr, this);
  }

  public long capacity() {
    return libTAU_jni.file_slice_vector_capacity(swigCPtr, this);
  }

  public void reserve(long n) {
    libTAU_jni.file_slice_vector_reserve(swigCPtr, this, n);
  }

  public boolean isEmpty() {
    return libTAU_jni.file_slice_vector_isEmpty(swigCPtr, this);
  }

  public void clear() {
    libTAU_jni.file_slice_vector_clear(swigCPtr, this);
  }

  public void add(file_slice x) {
    libTAU_jni.file_slice_vector_add(swigCPtr, this, file_slice.getCPtr(x), x);
  }

  public file_slice get(int i) {
    return new file_slice(libTAU_jni.file_slice_vector_get(swigCPtr, this, i), false);
  }

  public void set(int i, file_slice val) {
    libTAU_jni.file_slice_vector_set(swigCPtr, this, i, file_slice.getCPtr(val), val);
  }

}
