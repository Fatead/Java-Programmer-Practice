## 配置文件的分类
### 1.守护进程
守护进程是运行在非交互模式下的程序，一般来说，守护进程任务是和联网区域有关的，它们等待连接，通过连接提供服务。

- /etc/syslogd.conf    syslogd守护进程的配置文件，syslogd是一种守护进程，它负责将其它程序发送到系统的消息写入到磁盘。
- /etc/httpd.conf   Web服务器Apache的配置文件，这个文件可能在、usr/local/httpd/conf或etx/httpd/conf中。
- /etc/conf.modules   和  etc/modules.conf   kerneld的配置文件

守护进程是永远运行在后台的程序，场见的守护进程有in.ftpd (ftp服务器守护进程)、in.telnetd（telnet服务器守护进程）和syslogd（系统日志守护进程）。有些守护进程会严格监视配置文件，在配置文件发生改变的时候会自动重新加载，但是大多数需要手动完成加载，可以使用命令
service network restart
这些服务最常见的是 /etc/rc.d/init.d/*  目录中存在的脚本，在系统引导时由init启动，可以执行下列指令重启服务。
 /etc/rc.d/init.d/<script-for-the-service> start |  stop | status


### 2.访问文件

- /etc/host.conf 告诉网络域名服务器如何查找主机名
- /etc/hosts 包含本地网络中已知主机的一个列表，对于简单的主机名解析，在请求DNS或NIS服务器之前，/etc/hosts.conf通常会告诉解析程序先查看这里



### 3. 引导和登录/注销

- /etc/rc.d/rc.sysinit 通常时所有运行级别的第一个脚本
- /etc/rc.d/rc 通常在所有的运行级别运行，级别作为参数传递



### 4.系统管理

- /etc/group包含有效的组名称和指定组中包含的用户
- /etc/password  包含用户的账户信息，包含密码
- /etc/rpmrc rpm命令配置，所有的rpm命令行选项都可以在这个文件中一起设置
- /etc/usertty       /etc/shadow 包含加密后的用户账号密码信息
- /etc/shells 包含系统课用的shell列表
- /etc/motd 每日消息



### 5. 联网

- /etc/gated.conf gated的配置，只能被gated守护进程所使用
- /etc/rpc 包含rpc指令和规则
- /etc/services 将网络服务名转换为 端口号/协议
