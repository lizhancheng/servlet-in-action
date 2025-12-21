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
