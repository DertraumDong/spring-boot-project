# 找到docker mysql的ip地址
docker inspect mysql

# 启动xxl_job
docker run -d -e PARAMS="--server.port=8090 --spring.datasource.url=jdbc:mysql://172.17.0.2:3306/xxl_job?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=UTC --spring.datasource.username=traum --spring.datasource.password=621248 --spring.datasource.driver-class-name=com.mysql.jdbc.Driver --xxl.job.accessToken=" -p  8090:8090 -v /data/xxl-job:/data/applogs --name dong-xxl-job-admin -d xuxueli/xxl-job-admin:2.4.0
