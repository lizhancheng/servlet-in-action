# 基于 tomcat:9-alpine 基础镜像
FROM docker.1ms.run/library/tomcat:9-alpine
MAINTAINER admin admin@example.com
# 可选：安装Alpine缺失的常用工具（如bash、curl，按需添加）
# Alpine默认无bash，若脚本依赖bash需安装；若仅用ash可跳过此步
RUN apk add --no-cache bash curl
# 核心：将本地WAR包复制到Tomcat的webapps目录
# 注意：WAR包需与Dockerfile同目录，或指定绝对路径
COPY target/hello.war /usr/local/tomcat/webapps/
# 关键：修复Alpine版Tomcat的webapps空目录问题（复制webapps.dist到webapps）
# Alpine的ash shell兼容此命令，无需修改
# 可选：删除默认示例应用（减少镜像体积，按需执行）
# RUN mkdir -p /usr/local/tomcat/webapps.dist && cp -r /usr/local/tomcat/webapps.dist/* /usr/local/tomcat/webapps/ && rm -rf /usr/local/tomcat/webapps/examples /usr/local/tomcat/webapps/docs
RUN mkdir -p /usr/local/tomcat/webapps

# 暴露Tomcat默认端口
EXPOSE 8080

# 启动Tomcat（与普通镜像一致，catalina.sh兼容Alpine）
# 注意：Alpine无bash，若用catalina.sh start会导致容器退出，必须用run前台运行
CMD ["catalina.sh", "run"]

# 构建镜像
# docker build -t tomcat-alpine-hello:v1 .
# 启动镜像
# docker run -d --name tomcat-hello-app -p 8080:8080 tomcat-alpine-hello:v1