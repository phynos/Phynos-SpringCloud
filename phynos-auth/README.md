
# OAuth2.0

client_id：客户端id
client_secret：一般存储在后台服务器
response_type：返回类型
scope：数据范围
redirect_uri：回调uri
code：授权码
grant_type：授权方式

## 授权方式

- 授权码（authorization-code）
- 隐藏式（implicit）
- 密码式（password）：
- 客户端凭证（client credentials）

### 授权码

第一步：用户点击下面的链接，去B网站请求授权
https://b.com/oauth/authorize?
  response_type=code&
  client_id=CLIENT_ID&
  redirect_uri=CALLBACK_URL&
  scope=read
  
第二步：用户在B网站登录并决定是否授权，授权后会回调并附带授权码
https://a.com/callback?code=AUTHORIZATION_CODE

第三步：拿到授权码后，在“后台”服务器用授权码和客户端秘钥请求令牌
https://b.com/oauth/token?
 client_id=CLIENT_ID&
 client_secret=CLIENT_SECRET&
 grant_type=authorization_code&
 code=AUTHORIZATION_CODE&
 redirect_uri=CALLBACK_URL
 
第四步：B网站授权后，会向回调地址返回令牌数据

### 隐藏式

前端直接向B网站请求令牌
https://b.com/oauth/authorize?
  response_type=token&
  client_id=CLIENT_ID&
  redirect_uri=CALLBACK_URL&
  scope=read

### 密码模式

A网站直接根据用户提供的用户名和密码去B网站请求令牌，令牌直接在JSON中返回，不需要回调地址
https://oauth.b.com/token?
  grant_type=password&
  username=USERNAME&
  password=PASSWORD&
  client_id=CLIENT_ID
  
### 凭证式

https://oauth.b.com/token?
  grant_type=client_credentials&
  client_id=CLIENT_ID&
  client_secret=CLIENT_SECRET

## 令牌使用

curl -H "Authorization: Bearer ACCESS_TOKEN" \
"https://api.b.com"

## 令牌更新

https://b.com/oauth/token?
  grant_type=refresh_token&
  client_id=CLIENT_ID&
  client_secret=CLIENT_SECRET&
  refresh_token=REFRESH_TOKEN