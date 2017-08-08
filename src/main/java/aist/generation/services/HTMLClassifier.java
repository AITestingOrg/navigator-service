package aist.generation.services;

import aist.generation.models.Page;
import aist.generation.models.PageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HTMLClassifier implements Intelligence {
    @Autowired
    private ClientAdapter clientAdapter;

    public PageType classify(Page page) {
        //TODO: Put error handling for client adapter and configure IP as console arg
        clientAdapter.setIP("http://127.0.0.1:5000/");
        switch(clientAdapter.getResponse("test.html")) {
            case "Login Page":
                return PageType.LOGIN;
            case "Registration Page":
                return PageType.REGISTRATION;
            case "Payment Page":
                return PageType.PAYMENT;
            default:
                return PageType.NONE;
        }
    }
}
