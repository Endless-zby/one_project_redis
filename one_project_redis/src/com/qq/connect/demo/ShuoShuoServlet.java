package com.qq.connect.demo;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.qzone.Topic;
import com.qq.connect.javabeans.GeneralResultBean;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Date: 12-12-5
 * Time: ����3:19
 */
public class ShuoShuoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=utf-8");

        request.setCharacterEncoding("utf-8");
        String con = request.getParameter("con");
        HttpSession session = request.getSession();
        String accessToken = (String)session.getAttribute("demo_access_token");
        String openID = (String)session.getAttribute("demo_openid");
        System.out.println(accessToken);
        System.out.println(openID);
        //�뿪��������У���ȡ��conֵ�Ƿ���Ч
        if (con != "") {
            Topic topic = new Topic(accessToken, openID);
            try {
                GeneralResultBean grb = topic.addTopic(con);
                if (grb.getRet() == 0) {
                    response.getWriter().println("<a href=\"http://www.qzone.com\" target=\"_blank\">����˵˵�ѷ���ɹ������¼Qzone�鿴</a>");
                } else {
                    response.getWriter().println("���ź���֪ͨ��������˵˵ʧ�ܣ�ԭ�� " + grb.getMsg());
                }
            } catch (QQConnectException e) {
                System.out.println("���쳣�ˣ�");
            }
        } else {
            System.out.println("��ȡ����ֵΪ�գ�");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
