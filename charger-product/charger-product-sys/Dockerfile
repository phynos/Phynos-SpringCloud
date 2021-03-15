# 基于的镜像
FROM java:8
# 设置环境变量
ENV TZ=Asia/Shanghai
# 执行命令，为了防止多层镜像，这里只执行一次
RUN mkdir -p /cloud
# 指定工作目录
WORKDIR /cloud
# 声明暴露端口
EXPOSE ${APP_PORT}
#复制到容器指定目录
COPY ./target/charger_product_sys.jar /cloud/charger_product_sys.jar
# 启动后执行命令
CMD java -jar -Duser.timezone=GMT+08 -Dfile.encoding=UTF-8 /cloud/charger_product_sys.jar