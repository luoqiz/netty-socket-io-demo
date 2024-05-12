package top.luoqiz.im.nettysocketio.config;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Component
public class SocketConnection {
    public static ConcurrentMap<String, SocketIOClient> socketIOClientMap = new ConcurrentHashMap<>();

    /**
     * 客户端连接的时候触发
     *
     * @param client SocketIOClient
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        log.info("用户:" + client.getSessionId() + "已连接");
//        String sessionId = client.getHandshakeData().getSingleUrlParam("userId");
//        if (!StringUtils.isEmpty(sessionId)) {
//            // 存储 SocketIOClient，用于发送消息
//            socketIOClientMap.put(sessionId, client);
//            log.info("用户:" + sessionId + "已连接");
//        }
    }

    /**
     * 客户端关闭连接时触发
     *
     * @param client SocketIOClient
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
//        String sessionId = client.getHandshakeData().getSingleUrlParam("userId");
//        if (!StringUtils.isEmpty(sessionId)) {
//            socketIOClientMap.remove(sessionId);
//            log.info("用户:" + sessionId + "断开连接");
//        }
    }

    public SocketIOClient getSocketIOClient(String sessionId) {
        return socketIOClientMap.get(sessionId);
    }

    public ConcurrentMap<String, SocketIOClient> getConnections() {
        return socketIOClientMap;
    }
}