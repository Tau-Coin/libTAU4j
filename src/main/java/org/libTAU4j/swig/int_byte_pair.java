/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libTAU4j.swig;

public class int_byte_pair {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected int_byte_pair(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(int_byte_pair obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libTAU_jni.delete_int_byte_pair(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public int_byte_pair() {
    this(libTAU_jni.new_int_byte_pair__SWIG_0(), true);
  }

  public int_byte_pair(int first, byte second) {
    this(libTAU_jni.new_int_byte_pair__SWIG_1(first, second), true);
  }

  public int_byte_pair(int_byte_pair p) {
    this(libTAU_jni.new_int_byte_pair__SWIG_2(int_byte_pair.getCPtr(p), p), true);
  }

  public void setFirst(int value) {
    libTAU_jni.int_byte_pair_first_set(swigCPtr, this, value);
  }

  public int getFirst() {
    return libTAU_jni.int_byte_pair_first_get(swigCPtr, this);
  }

  public void setSecond(byte value) {
    libTAU_jni.int_byte_pair_second_set(swigCPtr, this, value);
  }

  public byte getSecond() {
    return libTAU_jni.int_byte_pair_second_get(swigCPtr, this);
  }

}
