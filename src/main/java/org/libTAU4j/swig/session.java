/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libTAU4j.swig;

public class session extends session_handle {
  private transient long swigCPtr;

  protected session(long cPtr, boolean cMemoryOwn) {
    super(libTAU_jni.session_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(session obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libTAU_jni.delete_session(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public session(session_params params) {
    this(libTAU_jni.new_session__SWIG_0(session_params.getCPtr(params), params), true);
  }

  public session() {
    this(libTAU_jni.new_session__SWIG_1(), true);
  }

  public session(session arg0) {
    this(libTAU_jni.new_session__SWIG_2(session.getCPtr(arg0), arg0), true);
  }

  public session_proxy abort() {
    return new session_proxy(libTAU_jni.session_abort(swigCPtr, this), true);
  }

}
