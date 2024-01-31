package com.minyi.load;

import com.minyi.load.model.Order;
import com.minyi.load.utils.ObjectUtils;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;
import java.util.function.Supplier;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class WebClientSimulation extends Simulation {

    Iterator<Map<String, Object>> orderIdFeeder = Stream.generate((Supplier<Map<String, Object>>) () -> {
        String orderId = UUID.randomUUID().toString();
        return Collections.singletonMap("orderId", orderId);
    }).iterator();

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:10010")
            .acceptHeader("application/json;q=0.9,*/*;q=0.8")
            .doNotTrackHeader("1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0");

    ScenarioBuilder scn = scenario("CreateSimulation")
            .feed(orderIdFeeder)
            .exec(http("Create") // 8
                    .post("/create")
                    .header("content-type", "application/json")
                    .body(StringBody(createOrder("#{orderId}")))
                    .check(status().is(200))
            )
            .pause(5);

    {
        setUp( // 11
                scn.injectOpen(constantUsersPerSec(1000).during(60))
        ).protocols(httpProtocol);
    }

    public String createOrder(String orderId) {
        Order order = Order.builder()
                .orderId(orderId)
                .ccyPair("USDJPY")
                .traderRate(new BigDecimal("147.8642"))
                .dealtAmount(new BigDecimal("10000"))
                .dealtSide("BUY")
                .dealtCcy("USD")
                .build();
        return ObjectUtils.toJson(order);
    }
}
