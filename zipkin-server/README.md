# 分布式跟踪


## Sleuth
Span：基本的工作单元。Span包括一个64位的唯一ID，一个64位trace码，描述信息，时间戳事件，key-value 注解(tags)，span处理者的ID（通常为IP）。

Trace：一组Span形成的树形结构。

Annotation：用于及时记录存在的事件。常用的Annotation如下：

cs：客户端发送(client send) 客户端发起一个请求，表示span开始
sr：服务器接收(server received) 服务器接收到客户端的请求并开始处理，sr - cs 的时间为网络延迟
ss：服务器发送(server send) 服务器处理完请求准备返回数据给客户端。ss - sr 的时间表示服务器端处理请求花费的时间
cr：客户端接收(client received) 客户端接收到处理结果，表示span结束。 cr - cs 的时间表示客户端接收服务端数据的时间


## Zipkin
Zipkin Server用于收集和展示这些调用链路的信息
