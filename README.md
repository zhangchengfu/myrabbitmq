RabbitMQ：1、 启动命令：rabbitmq-server start &
    内存方式存储：rabbitmq-server start --ram
2、是否启动：lsof -i:5672
3、管理插件：rabbitmq-plugins enable rabbitmq_management
    插件列表：rabbitmq-plugins list
4、访问地址：http://192.168.1.10:15672/

发送方回进行重复传输（如未收到ACK）

接收方要做幂等处理