/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.libtorrent4j.swig;

public class file_storage {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected file_storage(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(file_storage obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtorrent_jni.delete_file_storage(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public file_storage() {
    this(libtorrent_jni.new_file_storage__SWIG_0(), true);
  }

  public file_storage(file_storage arg0) {
    this(libtorrent_jni.new_file_storage__SWIG_1(file_storage.getCPtr(arg0), arg0), true);
  }

  public boolean is_valid() {
    return libtorrent_jni.file_storage_is_valid(swigCPtr, this);
  }

  public void reserve(int num_files) {
    libtorrent_jni.file_storage_reserve(swigCPtr, this, num_files);
  }

  public void rename_file(int index, String new_filename) {
    libtorrent_jni.file_storage_rename_file(swigCPtr, this, index, new_filename);
  }

  public file_slice_vector map_block(int piece, long offset, long size) {
    return new file_slice_vector(libtorrent_jni.file_storage_map_block(swigCPtr, this, piece, offset, size), true);
  }

  public peer_request map_file(int file, long offset, int size) {
    return new peer_request(libtorrent_jni.file_storage_map_file(swigCPtr, this, file, offset, size), true);
  }

  public int num_files() {
    return libtorrent_jni.file_storage_num_files(swigCPtr, this);
  }

  public int end_file() {
    return libtorrent_jni.file_storage_end_file(swigCPtr, this);
  }

  public long total_size() {
    return libtorrent_jni.file_storage_total_size(swigCPtr, this);
  }

  public long size_on_disk() {
    return libtorrent_jni.file_storage_size_on_disk(swigCPtr, this);
  }

  public void set_num_pieces(int n) {
    libtorrent_jni.file_storage_set_num_pieces(swigCPtr, this, n);
  }

  public int num_pieces() {
    return libtorrent_jni.file_storage_num_pieces(swigCPtr, this);
  }

  public int end_piece() {
    return libtorrent_jni.file_storage_end_piece(swigCPtr, this);
  }

  public int last_piece() {
    return libtorrent_jni.file_storage_last_piece(swigCPtr, this);
  }

  public void set_piece_length(int l) {
    libtorrent_jni.file_storage_set_piece_length(swigCPtr, this, l);
  }

  public int piece_length() {
    return libtorrent_jni.file_storage_piece_length(swigCPtr, this);
  }

  public int piece_size(int index) {
    return libtorrent_jni.file_storage_piece_size(swigCPtr, this, index);
  }

  public int piece_size2(int index) {
    return libtorrent_jni.file_storage_piece_size2(swigCPtr, this, index);
  }

  public int blocks_in_piece2(int index) {
    return libtorrent_jni.file_storage_blocks_in_piece2(swigCPtr, this, index);
  }

  public void set_name(String n) {
    libtorrent_jni.file_storage_set_name(swigCPtr, this, n);
  }

  public String name() {
    return libtorrent_jni.file_storage_name(swigCPtr, this);
  }

  public void swap(file_storage ti) {
    libtorrent_jni.file_storage_swap(swigCPtr, this, file_storage.getCPtr(ti), ti);
  }

  public void canonicalize() {
    libtorrent_jni.file_storage_canonicalize(swigCPtr, this);
  }

  public sha256_hash root(int index) {
    return new sha256_hash(libtorrent_jni.file_storage_root(swigCPtr, this, index), true);
  }

  public String root_ptr(int index) {
    return libtorrent_jni.file_storage_root_ptr(swigCPtr, this, index);
  }

  public String symlink(int index) {
    return libtorrent_jni.file_storage_symlink(swigCPtr, this, index);
  }

  public long mtime(int index) {
    return libtorrent_jni.file_storage_mtime(swigCPtr, this, index);
  }

  public String file_path(int index, String save_path) {
    return libtorrent_jni.file_storage_file_path__SWIG_0(swigCPtr, this, index, save_path);
  }

  public String file_path(int index) {
    return libtorrent_jni.file_storage_file_path__SWIG_1(swigCPtr, this, index);
  }

  public long file_size(int index) {
    return libtorrent_jni.file_storage_file_size(swigCPtr, this, index);
  }

  public boolean pad_file_at(int index) {
    return libtorrent_jni.file_storage_pad_file_at(swigCPtr, this, index);
  }

  public long file_offset(int index) {
    return libtorrent_jni.file_storage_file_offset(swigCPtr, this, index);
  }

  public int file_num_pieces(int index) {
    return libtorrent_jni.file_storage_file_num_pieces(swigCPtr, this, index);
  }

  public int file_num_blocks(int index) {
    return libtorrent_jni.file_storage_file_num_blocks(swigCPtr, this, index);
  }

  public int file_first_piece_node(int index) {
    return libtorrent_jni.file_storage_file_first_piece_node(swigCPtr, this, index);
  }

  public int file_first_block_node(int index) {
    return libtorrent_jni.file_storage_file_first_block_node(swigCPtr, this, index);
  }

  public file_flags_t file_flags(int index) {
    return new file_flags_t(libtorrent_jni.file_storage_file_flags(swigCPtr, this, index), true);
  }

  public boolean file_absolute_path(int index) {
    return libtorrent_jni.file_storage_file_absolute_path(swigCPtr, this, index);
  }

  public int file_index_at_offset(long offset) {
    return libtorrent_jni.file_storage_file_index_at_offset(swigCPtr, this, offset);
  }

  public int file_index_at_piece(int piece) {
    return libtorrent_jni.file_storage_file_index_at_piece(swigCPtr, this, piece);
  }

  public int last_file_index_at_piece(int piece) {
    return libtorrent_jni.file_storage_last_file_index_at_piece(swigCPtr, this, piece);
  }

  public int file_index_for_root(sha256_hash root_hash) {
    return libtorrent_jni.file_storage_file_index_for_root(swigCPtr, this, sha256_hash.getCPtr(root_hash), root_hash);
  }

  public int piece_index_at_file(int f) {
    return libtorrent_jni.file_storage_piece_index_at_file(swigCPtr, this, f);
  }

  public int last_piece_index_at_file(int f) {
    return libtorrent_jni.file_storage_last_piece_index_at_file(swigCPtr, this, f);
  }

  public void sanitize_symlinks() {
    libtorrent_jni.file_storage_sanitize_symlinks(swigCPtr, this);
  }

  public boolean v2() {
    return libtorrent_jni.file_storage_v2(swigCPtr, this);
  }

  public void add_file_ex(error_code ec, String path, long file_size, file_flags_t file_flags, long mtime, String symlink_path) {
    libtorrent_jni.file_storage_add_file_ex__SWIG_0(swigCPtr, this, error_code.getCPtr(ec), ec, path, file_size, file_flags_t.getCPtr(file_flags), file_flags, mtime, symlink_path);
  }

  public void add_file_ex(error_code ec, String path, long file_size, file_flags_t file_flags, long mtime) {
    libtorrent_jni.file_storage_add_file_ex__SWIG_1(swigCPtr, this, error_code.getCPtr(ec), ec, path, file_size, file_flags_t.getCPtr(file_flags), file_flags, mtime);
  }

  public void add_file_ex(error_code ec, String path, long file_size, file_flags_t file_flags) {
    libtorrent_jni.file_storage_add_file_ex__SWIG_2(swigCPtr, this, error_code.getCPtr(ec), ec, path, file_size, file_flags_t.getCPtr(file_flags), file_flags);
  }

  public void add_file_ex(error_code ec, String path, long file_size) {
    libtorrent_jni.file_storage_add_file_ex__SWIG_3(swigCPtr, this, error_code.getCPtr(ec), ec, path, file_size);
  }

  public String file_name_ex(int index) {
    return libtorrent_jni.file_storage_file_name_ex(swigCPtr, this, index);
  }

  public final static long max_file_size = libtorrent_jni.file_storage_max_file_size_get();
  public final static long max_file_offset = libtorrent_jni.file_storage_max_file_offset_get();
  public final static file_flags_t flag_pad_file = new file_flags_t(libtorrent_jni.file_storage_flag_pad_file_get(), false);
  public final static file_flags_t flag_hidden = new file_flags_t(libtorrent_jni.file_storage_flag_hidden_get(), false);
  public final static file_flags_t flag_executable = new file_flags_t(libtorrent_jni.file_storage_flag_executable_get(), false);
  public final static file_flags_t flag_symlink = new file_flags_t(libtorrent_jni.file_storage_flag_symlink_get(), false);
}
