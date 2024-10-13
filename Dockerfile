#第一阶段的OpenJDK基础镜像
FROM eclipse-temurin:17 AS builder
WORKDIR /workspace
#声明了项目中应用JAR文件的位置
ARG JAR_FILE=target/*.jar
#将应用的JAR文件从本地机器复制到镜像的workspace目录中
COPY ${JAR_FILE} catalog-service.jar
#从使用分层JAR模式的归档文件中抽取各个层
RUN java -Djarmode=layertools -jar catalog-service.jar extract

#第二阶段的OpenJDK基础镜像
FROM eclipse-temurin:17
WORKDIR /workspace
RUN useradd spring
USER spring
COPY --from=builder /workspace/dependencies/ ./
COPY --from=builder /workspace/spring-boot-loader/ ./
COPY --from=builder /workspace/snapshot-dependencies/ ./
COPY --from=builder /workspace/application/ ./
#使用Spring Boot Launcher启动应用，此时应用位于层中，而不再是uber-JAR
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]
# docker rmi catalog-service && docker build -t catalog-service .
# 扫描容器漏洞
# grype catalog-sercice