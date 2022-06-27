# PingPongService
This is PingPongService


配置本机文件存放路径，例如：/Users/hjzhou/Documents/myCode/txtFolder/
1.install common-core,打包到本地仓库，其他项目项目要依赖公共包
2.启动eureka服务端：http://localhost:8085
3.ping service访问：http://localhost:8081/api/basicText
4.pong service访问：http://localhost:8083/helloPong/read?name=文件名

swagger地址：
ping：http://localhost:8081/swagger-ui.html
pong：http://localhost:8083/swagger-ui.html