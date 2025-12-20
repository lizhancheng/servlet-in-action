# servlet-in-action
For learning servlet

### HttpServletRequest

1. getMethod(): 返回请求方法，GET | POST
2. getRequestURI(): 返回请求路径，但不包括请求参数，如 "/hello"
3. getQueryString(): 返回请求参数，如 "param1=foo&param2=bar"
4. getContentType(): 获取请求Body的类型，如 "application/x-www-form-urlencoded"
