/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.redkale.net.http;

import org.redkale.net.Servlet;
import org.redkale.util.*;

/**
 *
 * <p>
 * 详情见: http://www.redkale.org
 *
 * @author zhangjx
 */
public abstract class HttpServlet extends Servlet<HttpContext, HttpRequest, HttpResponse> {

    AnyValue _conf; //当前HttpServlet的配置

    String _prefix = ""; //当前HttpServlet的path前缀

    @Override
    public final boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass();
    }

    @Override
    public final int hashCode() {
        return this.getClass().hashCode();
    }

}
