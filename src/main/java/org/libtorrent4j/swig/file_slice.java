/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class file_slice {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected file_slice(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(file_slice obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtorrent_jni.delete_file_slice(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setFile_index(int value) {
    libtorrent_jni.file_slice_file_index_set(swigCPtr, this, value);
  }

  public int getFile_index() {
    return libtorrent_jni.file_slice_file_index_get(swigCPtr, this);
  }

  public void setOffset(long value) {
    libtorrent_jni.file_slice_offset_set(swigCPtr, this, value);
  }

  public long getOffset() {
    return libtorrent_jni.file_slice_offset_get(swigCPtr, this);
  }

  public void setSize(long value) {
    libtorrent_jni.file_slice_size_set(swigCPtr, this, value);
  }

  public long getSize() {
    return libtorrent_jni.file_slice_size_get(swigCPtr, this);
  }

  public file_slice() {
    this(libtorrent_jni.new_file_slice(), true);
  }

}
