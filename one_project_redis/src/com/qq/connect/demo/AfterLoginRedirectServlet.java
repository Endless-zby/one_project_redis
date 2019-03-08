package com.qq.connect.demo;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.PageFans;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.PageFansBean;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.javabeans.weibo.Company;
import com.qq.connect.oauth.Oauth;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Date: 12-12-4
 * Time: ����4:36
 */
public class AfterLoginRedirectServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
    	doPost(request, response);
        
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	 response.setContentType("text/html; charset=utf-8");

         PrintWriter out = response.getWriter();

         try {
           AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);

             String accessToken = null,
             		    openID = null;
             long tokenExpireIn = 0L;
             System.out.println("accessToken:" + accessToken);
             System.out.println("openID:" + openID);
             System.out.println("accessTokenObj:" + accessTokenObj);




             if (accessTokenObj.getAccessToken().equals("")) {
             	System.out.print(accessTokenObj.getAccessToken());
             	
//                 ���ǵ���վ��CSRF�����˻����û�ȡ������Ȩ
//                 ��һЩ����ͳ�ƹ���
                 System.out.print("û�л�ȡ����Ӧ����");
             } else {
                 accessToken = accessTokenObj.getAccessToken();
                 tokenExpireIn = accessTokenObj.getExpireIn();

                 request.getSession().setAttribute("demo_access_token", accessToken);
                 request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

                 // ���û�ȡ����accessToken ȥ��ȡ��ǰ�õ�openid -------- start
                 OpenID openIDObj =  new OpenID(accessToken);
                 openID = openIDObj.getUserOpenID();

                 out.println("��ӭ�㣬����Ϊ " + openID + " ���û�!");
                 request.getSession().setAttribute("demo_openid", openID);
                 out.println("<a href=" + "/shuoshuoDemo.html" +  " target=\"_blank\">ȥ��������˵˵��demo��</a>");
                 // ���û�ȡ����accessToken ȥ��ȡ��ǰ�û���openid --------- end


                 out.println("<p> start -----------------------------------���û�ȡ����accessToken,openid ȥ��ȡ�û���Qzone���ǳƵ���Ϣ ---------------------------- start </p>");
                 UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                 UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                 out.println("<br/>");
                 if (userInfoBean.getRet() == 0) {
                     out.println(userInfoBean.getNickname() + "<br/>");
                     out.println(userInfoBean.getGender() + "<br/>");
                     out.println("����ȼ��� " + userInfoBean.getLevel() + "<br/>");
                     out.println("��Ա : " + userInfoBean.isVip() + "<br/>");
                     out.println("�����Ա�� " + userInfoBean.isYellowYearVip() + "<br/>");
                     out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL30() + "/><br/>");
                     out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL50() + "/><br/>");
                     out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL100() + "/><br/>");
                 } else {
                     out.println("�ܱ�Ǹ������û����ȷ��ȡ��������Ϣ��ԭ���ǣ� " + userInfoBean.getMsg());
                 }
                 out.println("<p> end -----------------------------------���û�ȡ����accessToken,openid ȥ��ȡ�û���Qzone���ǳƵ���Ϣ ---------------------------- end </p>");



                 out.println("<p> start ----------------------------------- ��֤��ǰ�û��Ƿ�Ϊ��֤�ռ�ķ�˿------------------------------------------------ start <p>");
                 PageFans pageFansObj = new PageFans(accessToken, openID);
                 PageFansBean pageFansBean = pageFansObj.checkPageFans("97700000");
                 if (pageFansBean.getRet() == 0) {
                     out.println("<p>��֤��" + (pageFansBean.isFans() ? "��" : "����")  + "QQ�ռ�97700000�ٷ���֤�ռ�ķ�˿</p>");
                 } else {
                     out.println("�ܱ�Ǹ������û����ȷ��ȡ��������Ϣ��ԭ���ǣ� " + pageFansBean.getMsg());
                 }
                 out.println("<p> end ----------------------------------- ��֤��ǰ�û��Ƿ�Ϊ��֤�ռ�ķ�˿------------------------------------------------ end <p>");



                 out.println("<p> start -----------------------------------���û�ȡ����accessToken,openid ȥ��ȡ�û���΢�����ǳƵ���Ϣ ---------------------------- start </p>");
                 com.qq.connect.api.weibo.UserInfo weiboUserInfo = new com.qq.connect.api.weibo.UserInfo(accessToken, openID);
                 com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean = weiboUserInfo.getUserInfo();
                 if (weiboUserInfoBean.getRet() == 0) {
                     //��ȡ�û���΢��ͷ��----------------------start
                     out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL30() + "/><br/>");
                     out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL50() + "/><br/>");
                     out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL100() + "/><br/>");
                     //��ȡ�û���΢��ͷ�� ---------------------end

                     //��ȡ�û���������Ϣ --------------------start
                     out.println("<p>�𾴵��û�����������ǣ� " + weiboUserInfoBean.getBirthday().getYear()
                                 +  "��" + weiboUserInfoBean.getBirthday().getMonth() + "��" +
                                 weiboUserInfoBean.getBirthday().getDay() + "��");
                     //��ȡ�û���������Ϣ --------------------end

                     StringBuffer sb = new StringBuffer();
                     sb.append("<p>���ڵ�:" + weiboUserInfoBean.getCountryCode() + "-" + weiboUserInfoBean.getProvinceCode() + "-" + weiboUserInfoBean.getCityCode()
                              + weiboUserInfoBean.getLocation());

                     //��ȡ�û��Ĺ�˾��Ϣ---------------------------start
                     ArrayList<Company> companies = weiboUserInfoBean.getCompanies();
                     if (companies.size() > 0) {
                         //�й�˾��Ϣ
                         for (int i=0, j=companies.size(); i<j; i++) {
                             sb.append("<p>�����۹��Ĺ�˾����˾ID-" + companies.get(i).getID() + " ����-" +
                             companies.get(i).getCompanyName() + " ��������-" + companies.get(i).getDepartmentName() + " ��ʼ������-" +
                             companies.get(i).getBeginYear() + " ����������-" + companies.get(i).getEndYear());
                         }
                     } else {
                         //û�й�˾��Ϣ
                     }
                     //��ȡ�û��Ĺ�˾��Ϣ---------------------------end

                     out.println(sb.toString());

                 } else {
                     out.println("�ܱ�Ǹ������û����ȷ��ȡ��������Ϣ��ԭ���ǣ� " + weiboUserInfoBean.getMsg());
                 }

                 out.println("<p> end -----------------------------------���û�ȡ����accessToken,openid ȥ��ȡ�û���΢�����ǳƵ���Ϣ ---------------------------- end </p>");



             }
         } catch (QQConnectException e) {
         }
    }
}
