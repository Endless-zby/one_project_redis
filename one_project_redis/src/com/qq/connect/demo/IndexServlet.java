package com.qq.connect.demo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;

/**
 * Date: 12-12-4
 * Time: ÉÏÎç10:28
 */
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        try {
        	response.sendRedirect(new Oauth().getAuthorizeURL(request));
            
        } catch (QQConnectException e) {
            e.printStackTrace();
            System.out.print("Ê§°Ü");
            
        }
        System.out.print("³É¹¦");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request,  response);
    }
}
