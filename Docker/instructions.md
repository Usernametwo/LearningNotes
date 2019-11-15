# 常用指令

## 生命周期

1. docker rm \[OPTIONS\] CONTAINER : 删除容器
    
    -f : 强制删除正在运行的容器

2. docker run \[OPTIONS\] IMAGE : 创建一个容器并运行一个命令
    
    -it : 交互式命令行

    --name : 指定名称

    -p mappedport:originport : 端口映射

    -P : 随机端口映射

    -d : 以守护进程的方式运行

    -v hostdir:containerdir : 绑定主机和容器里面的两个卷进行数据同步

    -- volumes-from CONTAINER : 容器间的卷的绑定

3. docker start/stop/restart CONTAINER : 启动/停止/重启容器

4. docker kill CONTAINER : 强制停止运行中的容器

5. docker exec CONTAINER COMMAND: 在运行的容器中执行命令

    sudo docker exec -it mysql /bin/bash

## 容器操作

1. docker images : 列出镜像

2. docker ps \[OPTIONS\] : 查看Docker中运行的容器
    
    -a : 未运行的容器也显示

3. docker inspect CONTAINER : 获取容器的元数据

4. docker top CONTAINER : 查看容器中运行的进程信息

5. docker attach CONTAINER : 连接到正在运行中的容器

6. docker logs \[OPTIONS\] CONTAINER : 查看容器日志

    -f : 实时跟踪日志
    -t : 显示时间戳
    -tail NUMBER : 仅列出最新的NUMBER条日志

## 容器rootfs命令

1. docker commit \[OPTIONS\] CONTAINER \[REPOSITORY\[:TAG\]\] : 从容器创建一个新的镜像

    -a :提交的镜像作者

    -m :提交时的说明文字

2. docker cp CONTAINER:SRC_PATH DEST_PATH : 用于容器与主机之间的数据拷贝

## 镜像仓库

1. docker pull IMAGE:TAG : 从镜像仓库中拉取或者更新指定镜像

2. docker search IMAGE

## 本地镜像管理

1. docker build \[OPTIONS\] : 使用 Dockerfile 创建镜像

    -f : 指定要使用的Dockerfile路径

    --tag, -t : 镜像的名字及标签
