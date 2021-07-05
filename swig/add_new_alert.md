#==如何添加新的alert？==

1. libTAU: alert_types.hpp声明新的alert，同时在源文件中完成实现；注意添加的alert有一个优先级，如 listen_succeeded_alert：
TORRENT_DEFINE_ALERT_PRIO(listen_succeeded_alert, 49, alert_priority::critical)
该优先级49是唯一的，不能与其他alert重复。

2. libTAU4j: swig/libtorrent/alert_types.i文件中配置新添加的alert, %ignore隐藏某些数据成员，%extend添加新的方法; .运行脚本run-swig.sh确保不发生错误，此时新添加的alert已经生成：src/main/java/org/libtorrent4j/swig

3. libTAU4j: src/main/java/org/libtorrent4j/alerts 创建新的alert类进行封装, 参考ListenSucceededAlert.java

4. libTAU4j:src/main/java/org/libtorrent4j/alerts 完成对新创建alert的注册

    Alerts.java: private static CastLambda[] buildTable()
    在上述方法中添加新创建的alert，一定使用正确的index。
    
    AlertType.java:  创建alert对应的enum，同时在方法中
    private static AlertType[] buildTable()  注册对应的enum。