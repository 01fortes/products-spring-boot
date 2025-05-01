package com.jsamkt.learn.productcatalogspringboot.filter;

//import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Service
public class AuthFilter implements WebFilter {
//    private final ReactiveRedisTemplate<String, Object> redis;

//    public AuthFilter(ReactiveRedisTemplate<String, Object> redis) {
//        this.redis = redis;
//    }
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        if (path.startsWith("/actuator")) {
            return chain.filter(exchange);
        }

        var authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return Mono.empty();
        }
        
        var token = authorization.substring(7);
        return chain.filter(exchange);
//        return redis.opsForValue().get("banned:" + token)
//                .flatMap(banned -> {
//                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                    return Mono.empty();
//                })
//                .switchIfEmpty(chain.filter(exchange))
//                .then();
    }
}
