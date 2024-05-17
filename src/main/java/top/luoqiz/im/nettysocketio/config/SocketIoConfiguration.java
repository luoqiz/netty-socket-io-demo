package top.luoqiz.im.nettysocketio.config;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Slf4j
@Configuration
public class SocketIoConfiguration {

    /**
     * netty-socketio 服务器
     */
    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config =
                new com.corundumstudio.socketio.Configuration();
        // 设置访问 host 地址
        config.setHostname("::0");

        // 设置端口
        config.setPort(9001);

        // 设置是否可以跨域访问
        config.setOrigin("*");

        // 鉴权管理 --> SpringBoot OAuth2.0 封装登录、刷新令牌接口
//        config.setAuthorizationListener(new AuthorizationListener() {
//            @Override
//            public boolean isAuthorized(HandshakeData data) {
//                String accessToken = data.getSingleUrlParam("userId");
//                log.info("登录用户为：" + accessToken);
//                if (StringUtils.isEmpty(accessToken)) {
//                    return false;
//                }
//                return true;
//            }
//        });
        return new SocketIOServer(config);
    }

    /**
     * 用于扫描 netty-socketio 注解 比如 @OnConnect、@OnEvent
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner() {
        return new SpringAnnotationScanner(socketIOServer());
    }
}