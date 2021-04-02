## 1.显示系统执行的进程
ps -a：显示当前终端的所有进程信息
ps -u：以用户的格式显示进程信息
ps -x：显示后台进程运行的参数
ps -aux 
ps -ef  以全格式显示当前所有的进程（可以查看进程的父进程）


## 2.终止进程
### kill和killall
kill [选项] 进程号  （通过进程号杀死进程）
killall 进程名称  通过进程名称杀死进程
-9 表示强迫进程立即停止




## 3.查看进程树 pstree


-p 显示进程的PID
-u 显示进程的所属用户


## 4.服务管理（守护进程）
service 服务名 start | stop | restart | reload | status
在CentOS 7.0后，不再使用service，而是systemctl


Linux开机的启动流程

- 开机
- BIOS
- /boot
- init进程1
- 运行级别
- 运行级别对应的服务



### chkconfig命令
可以给每个服务的各个运行级别设置 自启动/关闭

- 查看服务 chkconfig --list | grep xxx
- chkconfig  服务名 --list
- chkconfig --level 5 服务名   on/off



## 5. 动态监控进程
### top 
top命令和ps命令很相似，但是区别在于top命令在执行的一段时间内可以更新正在运行的进程
top [选项]
top -d 秒数  ：指定top命令每隔几秒更新，默认是3秒
-i 使top不显示任何限制或者僵死进程
-p 通过指定监控进程ID来仅仅监控某个进程的状态

