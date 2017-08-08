package aist.generation.services;

import org.springframework.stereotype.Service;

@Service
public interface ClientAdapter {
    void post();
    void get();
    void openConnection();
    void closeConnection();
    void sendParams(String html);
    void setIP(String ip);
    String getResponse(String html);
}
