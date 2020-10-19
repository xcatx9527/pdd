/*
package com.cc.task.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.cc.task.model.SocketMessageBean;
import com.cc.common.commonutils.RxBus;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;
import okio.BufferedSource;
import rx.functions.Action1;

*/
/**
 * @author: ChenYang
 * @date: 2017-10-24 18:06
 * @Email 1016224774@qq.com
 * @Description
 *//*

public class SocketService extends Service {
    private OkHttpClient okHttpClient;
    private Request request;
    private WebSocketCall webSocketCall;
    private final ExecutorService sendExecutor = Executors.newSingleThreadExecutor();
    public WebSocket mWebSocket;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initSocket();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public void initSocket() {
         okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(3000, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(3000, TimeUnit.SECONDS)//设置连接超时时间
                .build();
        request = new Request.Builder().url("ws://10.0.0.28:8080/echo?id=10&type=1").build();
        webSocketCall = WebSocketCall.create(okHttpClient, request);
        webSocketCall.enqueue(new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                RxBus.getInstance().post("socketState", "连接成功.....");
                mWebSocket = webSocket;
            }

            */
/**
             * 连接失败
             *
             * @param e
             * @param response Present when the failure is a direct result of the response (e.g., failed
             *                 upgrade, non-101 response code, etc.). {@code null} otherwise.
             *//*

            @Override
            public void onFailure(IOException e, Response response) {
                RxBus.getInstance().post("socketState", "连接失败.....");

            }

            */
/**
             * 接收到消息
             *
             * @param message
             * @throws IOException
             *//*

            @Override
            public void onMessage(ResponseBody message) throws IOException {
                final RequestBody response;
                RxBus.getInstance().post("socketState", "消息:" + message.string());
//                    Logger.e("WebSocketCall", "onMessage:" + message.source().readByteString().utf8());
//                    message.source().close();
                    if (message.contentType() == WebSocket.TEXT) {//
                        response = RequestBody.create(WebSocket.TEXT, "你好");//文本格式发送消息
                    } else {
                        BufferedSource source = message.source();
                        Logger.e("WebSocketCall", "onMessage:" + source.readByteString());
                        response = RequestBody.create(WebSocket.BINARY, source.readByteString());
                    }
                    message.source().close();
                    sendExecutor.execute(() -> {
                        try {
                            Thread.sleep(1000 * 60);
                            mWebSocket.sendMessage(response);//发送消息
                        } catch (IOException e) {
                            e.printStackTrace(System.out);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
            }

            @Override
            public void onPong(Buffer payload) {
                Logger.e("WebSocketCall", "onPong:");
            }


            */
/**
             * 关闭
             *
             * @param code   The <a href="http://tools.ietf.org/html/rfc6455#section-7.4.1">RFC-compliant</a>
             *               status code.
             * @param reason Reason for close or an empty string.
             *//*

            @Override
            public void onClose(int code, String reason) {
//                    sendExecutor.shutdown();
                RxBus.getInstance().post("socketState", "连接关闭.....");
            }
        });
        //发送消息
        RxBus.getInstance().on("sentMessage", (Action1<SocketMessageBean>) s -> {
            Gson gson = new Gson();
            String message = gson.toJson(s);
            Logger.e("发送消息--->" + message);
            RequestBody response = RequestBody.create(WebSocket.TEXT, message);
            try {
                mWebSocket.sendMessage(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
*/
