该项目是网关项目，一般作为微服务集群对外使用资源使用
里面已经实现了2个例子

1.通过访问网关调用userApp的微服务实现调用微服务的负载均衡（ ribbon demo）
代码定位
UserEndPointRibbon.class

2.通过访问网关调用userApp的微服务实现调用微服务的断路由的效果 (hystrix demo 包含2种实现方式)
代码定位 
UserEndPointHystrix.class