/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wentch.redkale.net.icep;

import com.wentch.redkale.convert.bson.*;
import com.wentch.redkale.net.*;
import com.wentch.redkale.util.*;
import com.wentch.redkale.watch.*;
import java.net.*;
import java.nio.*;
import java.nio.charset.*;
import java.util.concurrent.*;
import java.util.logging.*;

/**
 *
 * @author zhangjx
 */
public final class IcepContext extends Context {

    protected final BsonFactory bsonFactory;

    public IcepContext(long serverStartTime, Logger logger, ExecutorService executor, ObjectPool<ByteBuffer> bufferPool,
            ObjectPool<Response> responsePool, int maxbody, Charset charset, InetSocketAddress address, PrepareServlet prepare,
            WatchFactory watch, int readTimeoutSecond, int writeTimeoutSecond) {
        super(serverStartTime, logger, executor, bufferPool, responsePool, maxbody, charset,
                address, prepare, watch, readTimeoutSecond, writeTimeoutSecond);
        this.bsonFactory = BsonFactory.root();
    }
}