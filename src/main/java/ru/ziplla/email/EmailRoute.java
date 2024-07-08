package ru.ziplla.email;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EmailRoute extends RouteBuilder {

//    @Override
//    public void configure() throws Exception {
//        from("direct:sendEmail")
//                .setHeader("subject", simple("${header.subject}"))
//                .setHeader("to", simple("${header.to}"))
//                .setHeader("from", simple("${header.from}"))
//                .setBody(simple("${header.body}"))
//                .to("smtp://smtp.gmail.com:587?username=pzajtsev.01@gmail.com&password=hptewtnmmxqmcyzg&mail.smtp.starttls.enable=true");
//    }

    @Override
    public void configure() throws Exception {
        from("direct:sendEmail")
                .setHeader("subject", simple("${header.subject}"))
                .setHeader("to", simple("${header.to}"))
                .setHeader("from", simple("${header.from}"))
                .setBody(simple("${header.body}"))
                .to("smtp://smtp.gmail.com:587?username=pzajtsev.01@gmail.com&password=hptewtnmmxqmcyzg&mail.smtp.starttls.enable=true");
    }
}
