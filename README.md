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


# 开发用户信息修改和删除服务
- 常用的验证注解
    - @NotNull 值不能为空
    - @Null 值必须为空
    - @Pattern(regex=) 字符串必须匹配正则表达式
    - @Size(min=, max=) 集合的元素数量必须在min和max之间
    - CreditCardNumber(ignoreNonDigitCharacters=)
    - @Email 字符串必须是Email地址
    - @Length(min=, max=) 检查字符串的长度
    - @NotBlank 字符串必须有字符
    - @NotEmpte 不符串不为null, 集合有元素
    - @Range(min=, max=) 数字必须大于等min, 小于等于max
    - @SafeHtml 字符串是安全的html
    - @URL 字符串是合法的URL
    - @AssertFalse 值必须是false
    - @AssertTrue 值必须是true
    - @DecimalMax(value=, inclusive=) 值必须小于等于(inclusive=true)/小于（inclusive=false）value属性指定的值 。可注解在字符串类型的属性上。
    - @DecimalMin(value=, inclusive) 值 必须大于等于（inclusive=true)/大于（inclusive=false）value属性指定的值 。可以注解在字符串类型的属性上。
    - @Digits(integer=, fraction=) 数字格式检查， integer指定整数部分的最大长度， fraction指定小数部分的最大长度
    - @Future 值必须是未来的日期。
    - @Past 值必须是过去的日期。
    - Max(value=) 值必须小于等于value指定的值 ， 不能注解在字符串类型型的属性上
    - Min(value=) 值必须大于等于value指定的值， 不能注解在字符串类型的属性上
- 自定义消息
- 自定义校验注解