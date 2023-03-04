package com.maenrico.desafiojava.model;
import com.telesign.*;

public class TelesignConfig {

    String customerId = "EC94A086-CCA5-4D37-B3E5-329C81C7D5F3";
    String apiKey = "EC94A086-CCA5-4D37-B3E5-329C81C7D5F3";

    RestClient restClient = new RestClient(customerId, apiKey);

    //TelesignClient telesignClient = new TelesignClient(restClient);

    //MessagingClient messagingClient = new MessagingClient(telesignClient);


}
