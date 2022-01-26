/*package com.zkzn.les.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

	@Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        // @formatter:off
        return builder.routes ()
                .route (r -> r.path ("/wms/**")
                        .uri ("lb://les-wms-provider")
                        .order (0)
                        .id ("les-wms-provider1")
                )
                .build ();
    }
}
*/