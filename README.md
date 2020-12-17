- shinley-security: 主模块
- shinley-security-core: 核心业务逻辑
- imooc-security-browser: 浏览器安全特定代码
- imooc-security-app: app相关特定代码
- imooc-security-demo: 样例程序


# jsonPath 的github地址
https://github.com/json-path/JsonPath

# JsonView 使用步骤

- 使用接口来声明多个视图
- 在值对象的get方法上指定视图
- 在Controller方法上指定视图
 
# 处理创建请求
- @RequestBody 映射请求到java方法的参数
- 日期类型参数的处理
    1. 不要传带格式的时间格式， 只传时间戳
    
- @Valid注解和BindingResult验证请求参数的合法性并处理校验结果