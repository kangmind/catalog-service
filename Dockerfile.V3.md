# 使用springboot build image

### 数据库

docker run -d \
--name polar-postgres \
--net catalog-network \
-e POSTGRES_USER=user \
-e POSTGRES_PASSWORD=password \
-e POSTGRES_DB=polardb_catalog \
-p 5432:5432 postgres:14.4

### 应用

docker run -d \
--name catalog-service \
--net catalog-network \
-p 9001:9001 \
-e SPRING_DATASOURCE_URL=jdbc:postgresql://polar-postgres:5432/polardb_catalog \
-e SPRING_PROFILES_ACTIVE=test-data \
--platform linux/amd64 \
catalog-service:0.0.1-SNAPSHOT

### 删除

docker rm -f catalog-service polar-postgres

### 删除网络

docker network rm catalog-network

### 删除未使用和悬空的镜像

docker image prune -a

### 强力清除 未使用的资源 包括未运行的容器

docker system prune -f

### 打包镜像并推送

./mvnw spring-boot:build-image \
-Ddocker.registry.username=kangmind \
-Ddocker.registry.password=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 \
-Ddocker.registry.protocol=https \
-Ddocker.registry.url=ghcr.io \
-Dimage.name=catalog-service \
-Dimage.tag= \
-DskipTests \
-Dspring-boot.build-image.publish=true

不指定tag 则自动使用项目版本 tag为空字符串 则默认为 :latest

./mvnw spring-boot:build-image \
-Ddocker.registry.username=kangmind \
-Ddocker.registry.password=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 \
-Ddocker.registry.protocol=https \
-Ddocker.registry.url=ghcr.io \
-Dimage.name=catalog-service \
-DskipTests \
-Dspring-boot.build-image.publish=true

注意：~~不要显示指定 -Dimage.tag=:latest 否则镜像仓库不覆盖该镜像，但是第一次可以推送~~ 最好删除已推送的

./mvnw spring-boot:build-image \
-Ddocker.registry.username=kangmind \
-Ddocker.registry.password=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 \
-Ddocker.registry.protocol=https \
-Ddocker.registry.url=ghcr.io \
-Dimage.name=catalog-service \
-Dimage.tag= \
-DskipTests \
-Dspring-boot.build-image.publish=true


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




docker run -d \
--name catalog-service \
-p 9001:9001 \
-e SPRING_DATASOURCE_URL=jdbc:postgresql://polar-postgres:5432/polardb_catalog \
-e SPRING_PROFILES_ACTIVE=test-data \
--platform linux/amd64 \
catalog-service:latest


jar -tf target/catalog-service-0.0.1-SNAPSHOT.jar | grep JarLauncher
