package com.hjh.community_test.Controller;

import com.hjh.community_test.dto.AccessTokenDTO;
import com.hjh.community_test.dto.GithubUser;
import com.hjh.community_test.mapper.UserMapper;
import com.hjh.community_test.model.User;
import com.hjh.community_test.provider.GithubProvider;


import com.hjh.community_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String RedirectId;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state,  HttpServletResponse httpServletResponse){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(RedirectId);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setState(state);
        String accessToken=githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubuser =githubProvider.getUser(accessToken);
        String avatar_url=githubuser.getAvatar_url();
        System.out.println(githubuser.getName());
        System.out.println(avatar_url);
        if (githubuser== null){
            //登陆失败
            return "redirect:/";
        }else {
            User user=new User();
            String token=UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubuser.getName());
            user.setAccountId(String.valueOf(githubuser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModfied(user.getGmtCreate());
            user.setAvatarUrl(avatar_url);
            userService.create(user);

            httpServletResponse.addCookie(new Cookie("token",token));
            return "redirect:/";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        System.out.println("logout");
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
