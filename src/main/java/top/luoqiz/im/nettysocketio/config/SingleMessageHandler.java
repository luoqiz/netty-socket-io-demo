package top.luoqiz.im.nettysocketio.config;

import cn.hutool.json.JSONUtil;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.luoqiz.im.nettysocketio.model.MessageTemplate;
import top.luoqiz.im.nettysocketio.service.SingleMessageService;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Component
public class SingleMessageHandler {

    @Resource
    private SingleMessageService singleMessageService;

    @Resource
    private SocketConnection socketConnection;

    /**
     * 客户端事件  SINGLE_CHAT
     *
     * @param client   　客户端信息
     * @param request  请求信息
     * @param template 　客户端发送数据
     */
    @OnEvent(value = "SINGLE_CHAT")
    public void onSingleChat(SocketIOClient client, AckRequest request, Map<String, Object> template) {
//        singleMessageService.saveSingleMessage(template);
        log.info(JSONUtil.toJsonPrettyStr(template));
//        SocketIOClient toClient = socketConnection.getSocketIOClient(template.get("toUserId").toString());
//        if (toClient != null) {
//            toClient.sendEvent("SINGLE_CHAT", JSONUtil.toJsonStr(template));
//        } else {
//            log.info("toClient not online!");
//        }
    }

    /**
     * 客户端事件  SINGLE_CHAT
     *
     * @param client   　客户端信息
     * @param request  请求信息
     * @param template 　客户端发送数据
     */
    @OnEvent(value = "GROUP_CHAT")
    public void onGroupChat(SocketIOClient client, AckRequest request, MessageTemplate template) {
        Set<String> rooms = client.getAllRooms();
        for (String room : rooms) {
            log.info("room: {}", room);
        }
//        log.info(template.getType());
        ConcurrentMap<String, SocketIOClient> connects = socketConnection.getConnections();
        if (!connects.isEmpty()) {
            connects.forEach((s, socketIOClient) -> {
                log.info("sessionId:", s);
                socketIOClient.sendEvent("GROUP_CHAT", JSONUtil.toJsonStr(template));
            });
        }
    }
}