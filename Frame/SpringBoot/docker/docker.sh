#!/bin/zsh

echo "------>   拷贝jar包到远程服务器"
scp target/"$1"-"$2".jar root@linux.juststudy.site:/mnt/study/docker-jar/"$1".jar
scp Dockerfile root@linux.juststudy.site:/mnt/study/docker-jar/"$1"-Dockerfile

echo "------>   连接远程服务器并执行命令"
ssh -tt root@linux.juststudy.site <<eeooff
    echo "------>   进入工作目录"
    cd /mnt/study/docker-jar/
    echo "------>   停止旧容器"
    docker stop $1
    echo "------>   删除旧容器"
    docker rm $1
    echo "------>   删除旧镜像"
    docker rmi $1
    echo "------>   构建新镜像"
    docker build -t $1 --build-arg app=$1 -f /mnt/study/docker-jar/$1-Dockerfile .&&
    echo "------>   运行新容器"
    docker run -di --name=$1 -p 60000:60000 $1
    echo "------>   执行结束，退出远程服务器"
    exit

eeooff
