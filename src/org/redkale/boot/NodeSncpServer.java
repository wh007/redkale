/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.redkale.boot;

import java.net.*;
import java.util.*;
import java.util.logging.*;
import org.redkale.net.*;
import org.redkale.net.sncp.*;
import org.redkale.util.*;

/**
 *
 * <p>
 * 详情见: http://www.redkale.org
 *
 * @author zhangjx
 */
@NodeProtocol({"SNCP"})
public final class NodeSncpServer extends NodeServer {

    private final SncpServer sncpServer;

    public NodeSncpServer(Application application, AnyValue serconf) {
        super(application, createServer(application, serconf));
        this.sncpServer = (SncpServer) this.server;
        this.consumer = sncpServer == null ? null : x -> sncpServer.addService(x);
    }

    private static Server createServer(Application application, AnyValue serconf) {
        return new SncpServer(application.getStartTime(), application.getWatchFactory());
    }

    @Override
    public InetSocketAddress getSocketAddress() {
        return sncpServer == null ? null : sncpServer.getSocketAddress();
    }

    public void consumerAccept(ServiceWrapper wrapper) {
        if (this.consumer != null) this.consumer.accept(wrapper);
    }

    @Override
    public void init(AnyValue config) throws Exception {
        super.init(config);
        //-------------------------------------------------------------------
        if (sncpServer == null) return; //调试时server才可能为null
        final StringBuilder sb = logger.isLoggable(Level.FINE) ? new StringBuilder() : null;
        final String threadName = "[" + Thread.currentThread().getName() + "] ";
        List<SncpServlet> servlets = sncpServer.getSncpServlets();
        Collections.sort(servlets);
        for (SncpServlet en : servlets) {
            if (sb != null) sb.append(threadName).append(" Loaded ").append(en).append(LINE_SEPARATOR);
        }
        if (sb != null && sb.length() > 0) logger.log(Level.FINE, sb.toString());
    }

    @Override
    public boolean isSNCP() {
        return true;
    }

    public SncpServer getSncpServer() {
        return sncpServer;
    }

    @Override
    protected void loadServlet(ClassFilter<? extends Servlet> servletFilter) throws Exception {
    }

    @Override
    protected ClassFilter<Servlet> createServletClassFilter() {
        return null;
    }

}
