package ru.ziplla.email;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EmailRoute extends RouteBuilder {

    /**
     * Configures the Camel route for sending email messages.
     * This route listens on the "direct:sendEmail" endpoint, sets the necessary email headers,
     * and sends the email using an SMTP server.
     *
     * @throws Exception if an error occurs during route configuration
     */
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
