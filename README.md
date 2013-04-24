#Android Github Oauth Example 

## Compilation Dependency

- [Android Asynchronous Http Client ](http://loopj.com/android-async-http/)


## Reference

- [Github Oauth Document](http://developer.github.com/v3/oauth/)
- [Android Asynchronous Http Client Document] (http://loopj.com/android-async-http/)


## Work Flow

- <http://developer.github.com/v3/oauth/>

1. Redirect users to request GitHub access

    GET https://github.com/login/oauth/authorize

    Parameters : client_id,  redirect_uri, scope, state


2. GitHub redirects back to your site
    
    GitHub redirects back to your site with a temporary code in a *code* parameter 
    
3. Exchange Code for an access token:

    POST https://github.com/login/oauth/access_token

    Parameters : client_id,  redirect_uri, scope, code

4. Get the acessToken
    
    Respose: access_token=e72e16c7e42f292c6912e7710c838347ae178b4a&token_type=bearer


    

