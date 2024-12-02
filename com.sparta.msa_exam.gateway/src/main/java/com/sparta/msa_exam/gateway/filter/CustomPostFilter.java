package com.sparta.msa_exam.gateway.filter;

import java.net.URI;
import java.util.logging.Logger;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomPostFilter implements GlobalFilter, Ordered {

    private static final Logger logger = Logger.getLogger(CustomPostFilter.class.getName());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            ServerHttpResponse response = exchange.getResponse();
            logger.info("Post Filter: Response status code is " + response.getStatusCode());
            // 백엔드 서비스 정보 추출
            URI routeUri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);

            if (routeUri != null) {
                int backendPort = routeUri.getPort(); // 라우팅된 서비스의 포트 추출
                logger.info("Post Filter: Adding service-port header with value " + backendPort);

                // 응답 헤더에 Server-Port 추가
                response.getHeaders().add("Server-Port", String.valueOf(backendPort));
            } else {
                logger.warning("Post Filter: Unable to retrieve route URI.");
            }
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
