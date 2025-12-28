# servlet-in-action
For learning servlet

### HttpServletRequest

1. getMethod(): 返回请求方法，GET | POST
2. getRequestURI(): 返回请求路径，但不包括请求参数，如 "/hello"
3. getQueryString(): 返回请求参数，如 "param1=foo&param2=bar"
4. getContentType(): 获取请求Body的类型，如 "application/x-www-form-urlencoded"
5. getContextPath(): 获取当前Webapp挂载的路径，对于ROOT来说，总是返回空字符串 ""
6. getCookies(): 返回请求携带的所有 Cookie
7. getHeader(name): 获取指定的Header, 对Header名称不区分大小写
8. getHeaderNames(): 返回所有Header名称
9. getInputStream(): 如果该请求带有 HTTP Body, 该方法将打开一个输入流用于读取 Body
10. getReader(): 和 getInputStream 类似, 但打开的是 Reader
11. getRemoteAddr(): 返回客户端的IP地址
12. getSchema(): 返回协议类型，例如 "http", "https"
13. setAttribute(), getAttribute()

### HttpServletResponse

1. setStatus(int statusCode): 设置响应代码，默认是200
2. setContentType(String type): 设置 Body 的类型，例如 "text/html"
3. setCharacterEncoding(String charset): 设置字符编码，如 "UTF-8"
4. setHeader(String name, String value): 设置一个 Header 的值
5. addCookie(String cookie): 给响应添加一个 Cookie
6. addHeader(String name, String value): 给响应添加一个 Header , 因为 Http 协议允许有多个相同的 Header

### Dockerfile

1. 构建镜像：docker build -t tomcat-alpine-hello:v1 .
2. 运行容器：docker run -d --name tomcat-hello-app -p 8080:8080 tomcat-alpine-hello:v1
3. 容器调试：docker exec -it tomcat-hello-app /bin/bash
