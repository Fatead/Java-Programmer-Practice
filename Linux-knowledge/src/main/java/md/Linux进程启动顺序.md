

_**Linux下有三个特殊的进程，idle进程（PID = 0），init进程（PID = 1），和kthreadd(PID = 2)**_
![图片.png](https://cdn.nlark.com/yuque/0/2021/png/1196177/1617070880763-e962b0a3-632e-4f61-b1a0-a939585ba32b.png#align=left&display=inline&height=320&margin=%5Bobject%20Object%5D&name=%E5%9B%BE%E7%89%87.png&originHeight=407&originWidth=721&size=92182&status=done&style=none&width=567)


- 0号进程是linux启动的第一个进程，也称为idle进程或者swapper进程，是静态创建的
- 在0号进程启动后会接连创建两个进程，分别是一号进程和二号进程
- 1号进程最终回去调用init可执行文件，init进程最终回去创建所有的应用进程
- 2号进程（kthreadd）会在内核中负责创建所有的内核进程
- 0号进程是1号和2号进程的父进程，1号进程是所有用户态进程的父进程，2号进程是所有内核进程的父进程
- idle进程由系统自动创建，运行在内核态
- init进程由idle通过kernel_thread创建，在内核空间完成初始化后，加载init程序
- 在系统启动完成后，init将变为守护进程监视系统其他进程
- kthreadd进程由idle通过kernel_thread创建，并始终运行在内核空间，负责所有内核线程的调度和管理，它的任务就是管理和调度其他内核线程kernel_thread，会循环执行一个kthread函数，该函数的作用就是运行kthread_create_list全局链表中维护的kthread，当我们调用kernnel_thread创建的内核线程会被加入到此链表中。



