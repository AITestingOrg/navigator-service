package aist.generation.services;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class ClientService implements ClientAdapter {
    private HttpURLConnection connection;
    private URL url;

    public ClientService() {}

    public void setIP(String ip){
        try {
            url = new URL(ip);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void openConnection(){
        try {
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput( true );
        } catch(Exception e) {
            System.out.println("Couldn't open connection");
            e.printStackTrace();
        }
    }

    @Override
    public void sendParams(String html) {
        String urlParameters  = "html=" + html;
        byte[] postData= urlParameters.getBytes(StandardCharsets.UTF_8);

        openConnection();
        post();

        try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            wr.write(postData);
            wr.close();
        } catch (Exception e) {
            System.out.println("Could not send parameters");
            e.printStackTrace();
        }
    }
    @Override
    public void closeConnection() {
        connection.disconnect();
    }

    @Override
    public String getResponse(String html) {
        try {
            sendParams(html);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            closeConnection();
            return response.toString();
        } catch (Exception e) {
            System.out.println("Could not get response");
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void post() {
        try {
            connection.setRequestMethod("POST");
        } catch (Exception e) {
            System.out.println("Could not send post request");
            e.printStackTrace();
        }
    }

    public void get() {
        try {
            connection.setRequestMethod("GET");
        } catch (Exception e) {
            System.out.println("Could not send get request");
            e.printStackTrace();
        }
    }
}
