# SpringSecurity开发基于表单的认证
### SpringSecurity基本原理

- UsernamePasswordAuthenticationFilter 表单认证过滤器
- BasicAuthentictionFilter  基本认证过滤器
- 。。。
- ExceptionTranslationFilter
- FilterSecurityIntercepter

### 处定义用户认证逻辑
- 处理用户信息获取逻辑 UserDetailService
- 处理用户校验逻辑  UserDetails
- 处理密码加密解密  PasswordEncoder

### 个性化用户认证流程
- 自定义登录页面      http.formLogin().loginPage("/imooc-signIn.html")
- 自定义登录成功处理   AuthenticationSuccessHandler
- 自定义登录失败处理   AuthenticationFailureHandler

#### 处理不同类型 的请法庭

接到html 请求或数据请求

是否需要身份认证

跳转到一个自定义Controller方法上

在方法内判断

是否是html请求引发的跳转

是=》返回登录页面

否=》返回401状态码和错误信息

### 认证流程源码级详解
- 认证处理流程说明
- 认证结果如何在多个请求之间共享
- 获取认证用户信息

### 实现国资委形验证码功能
- 开发生成图形验证码接口
    - 根据随机数生成图片
    - 将随机数存到Session中
    - 将生成的图片写到接口的响应中
- 在认证流程中加和图形验证码校验
- 重构代码