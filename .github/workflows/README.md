1. In Android Platform


NDK的配置之后需要sourec .bashrc

CXX和CC的环境变量会影响后续3个工具库的安装。

LEVELDB, 以及OpenSSL、SQLITE3的configure会读取CXX和CC的环境变量。会采用移动平台的来编译。