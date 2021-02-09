package de.pickert.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints
 * to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {

	@Override
	public void configure() {
		from("timer:hello?period={{timer.period}}").routeId("hello").transform().method("myBean", "saySomething")
				.filter(simple("${body} contains 'foo'")).to("log:foo").end()
				.to("activemq:topic:{{spring.activemq.topic.name}}");

		from("activemq:topic:{{spring.activemq.topic.name}}")
				.setHeader("CamelIOTAToAddress", constant(
						"ALKDAQWWZ9WKVJJONOANDNLYWCHYYAIGMMAOCIDZYY9MVVUUGIAJDYEOD9YYGCSGYUIVLAHBVA9XPEG9BBNY9FXUFC"))
				.setHeader("CamelIOTASeed",
						constant("ILRBPXNMLZXZVBTMYYMZDJ9PQLGRHLN9PJLMYFHHNOLBAALDQVSSWFCCQ9RZB9YSYDMCIBVIXMBISSTXZ"))
				.setHeader("CamelIOTAValue", constant(0))
				.to("iota:good?url=https://nodes.thetangle.org:443&operation=sendTransfer");
//&tag=DDXCRCZCTCFDHD&depth=3&minWeightMagnitude=14&securityLevel=1
		// from("jms:topic:{{spring.activemq.topic.name}}").filter(simple("${body}
		// contains 'HELLO'")).to("log:foo");
	}

}
