/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-05-31 12:27:41 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.commons;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!--shortcut start-->\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "shortcut.jsp", out, false);
      out.write("\r\n");
      out.write("<!--shortcut end-->\r\n");
      out.write("<div id=\"header\">\r\n");
      out.write("  <div class=\"header_inner\">\r\n");
      out.write("    <div class=\"logo\">\r\n");
      out.write("\t\t\t<a name=\"sfbest_hp_hp_head_logo\" href=\"http://www.ecourses.cn\" class=\"trackref logoleft\">\r\n");
      out.write("\t\t</a>\r\n");
      out.write("\t\t\t<div class=\"logo-text\">\r\n");
      out.write("\t\t\t\t<img src=\"/images/html/logo_word.jpg\">\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("    <div class=\"index_promo\"></div>\r\n");
      out.write("    <div class=\"search\">\r\n");
      out.write("      <form action=\"http://localhost:8085/search.html\" id=\"searchForm\" name=\"query\" method=\"GET\">\r\n");
      out.write("        <input type=\"text\" class=\"text keyword ac_input\" name=\"keyword\" id=\"keyword\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${query }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" style=\"color: rgb(153, 153, 153);\" onkeydown=\"javascript:if(event.keyCode==13) search_keys('searchForm');\" autocomplete=\"off\">\r\n");
      out.write("        <input type=\"button\" value=\"\" class=\"submit\" onclick=\"search_keys('searchForm')\">\r\n");
      out.write("      </form>\r\n");
      out.write("      \r\n");
      out.write("      <div class=\"search_hot\"><a target=\"_blank\" href=\"http://search.ecourses.cn/search.html?keyword=java\">Java</a>\r\n");
      out.write("      \t\t\t\t\t\t  <a target=\"_blank\" href=\"http://search.ecourses.cn/search.html?keyword=php\">PHP</a>\r\n");
      out.write("      \t\t\t\t\t\t  <a target=\"_blank\" href=\"http://search.ecourses.cn/search.html?keyword=c\">C</a>\r\n");
      out.write("      \t\t\t\t\t\t  <a target=\"_blank\" href=\"http://search.ecourses.cn/search.html?keyword=python\">Python</a>\r\n");
      out.write("      \t\t\t\t\t\t  <a target=\"_blank\" href=\"http://search.ecourses.cn/search.html?keyword=javascript\">JavaScript</a>\r\n");
      out.write("      \t\t\t\t\t\t  <a target=\"_blank\" href=\"http://search.ecourses.cn/search.html?keyword=android\">Android</a>\r\n");
      out.write("      \t\t\t\t\t\t  <a target=\"_blank\" href=\"http://search.ecourses.cn/search.html?keyword=ios\">iOS</a>\r\n");
      out.write("      \t\t\t\t\t\t  <a target=\"_blank\" href=\"http://search.ecourses.cn/search.html?keyword=大数据\">大数据</a>\r\n");
      out.write("      \t\t\t\t\t\t  <a target=\"_blank\" href=\"http://search.ecourses.cn/search.html?keyword=人工智能\">人工智能</a>\r\n");
      out.write("      </div>\r\n");
      out.write("      \r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"shopingcar\" id=\"topCart\">\r\n");
      out.write("      <s class=\"setCart\"></s><a href=\"http://cart.e3mall.cn\" class=\"t\" rel=\"nofollow\">我的购物车</a><b id=\"cartNum\">0</b>\r\n");
      out.write("      <span class=\"outline\"></span>\r\n");
      out.write("      <span class=\"blank\"></span>\r\n");
      out.write("      <div id=\"cart_lists\">\r\n");
      out.write("        <!--cartContent-->   \r\n");
      out.write("        <div class=\"clear\"></div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("  <script type=\"text/javascript\">\r\n");
      out.write("  \tfunction search_keys(formName){\r\n");
      out.write("\t   $('#'+formName).submit();\r\n");
      out.write("\t}\r\n");
      out.write("  </script>\r\n");
      out.write("</div>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
