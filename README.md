# MultiProcess
此工程主要用来熟悉Android中的多进程，包含了进程间的通信、自定义类型的使用以及进程的回调

1、为什么要使用多进程？

2、不同进程间如何通信？

3、AIDL是什么？定向tag是AIDL的一部分，in、out、inout，所有非基本类型都需要一个定向tag来指出数据流通的方式，那定向tag的in、out、inout表示的意义是什么？

4、如何使用自定义类型以及进程间如何回调？

5、真的有必要使用多进程吗？

工程配置相关

1、在AIDL中使用了自定义类型时，需要配置gradle文件，将aidl目录包含进srcDir里面，否则会报错找不到自定义类型

2、在自定义类型时，先定义AIDL文件，再建对应的Java文件


