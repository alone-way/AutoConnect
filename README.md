# AutoConnect



## 下载

[AutoConnect-1.1.1](https://gitee.com/iIceCube/AutoConnect/attach_files/410456/download)



## 介绍

AutoConnect是一款可以实现开机自动登录校园网的应用。避免了连接WLAN后再在浏览器登录的繁琐操作，并且还能注销当前登录的账户，实现多账号切换， 满足换号登录的需求。



![image-20200607211034894](https://gitee.com/iIceCube/Images/raw/master/img/20200607211043.png)





## 使用说明

#### 自动登录

勾选"自动登录"后，输入账号和密码，显示"登录成功"后会自动保存账户信息，在下一次启动应用时就会自动登录校园网。

#### 自动关闭

在每一次成功登录后，应用将缩小到托盘，并在15秒后自动关闭(即无需用户手动关闭)；若15秒后检测到应用窗口未被隐藏，则应用不会关闭。

#### 注销

无需输入账号和密码，点击"注销"按钮即可注销当前登录的账户。

#### 开机自启

勾选"开机自启"后，在下一次开机时会自动打开应用，配合"自动登录"，就达到开机自动登录校园网的目的。



## 注意事项

* 若要连无线网，请保持WLAN开关开启, 否则无法登录
* 不要使用管理员身份打开应用, 否则开机自启功能失效
* 安装路径不要包含中文和空格, 否则可能出现不可预知错误



## 更新日志

#### v1.1.1

* 修正i-bjxy重复连接

#### v1.1

* 修正断网时间的错误