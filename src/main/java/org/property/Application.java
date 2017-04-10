package org.property;
/**
 *
 *server.port: 8443
 server.ssl.key-store: keystore.p12
 server.ssl.key-store-password: password
 server.ssl.keyStoreType: PKCS12
 server.ssl.keyAlias: tomcat
 google client id: 1024116579945-tbtb0a0pdh6vnnrbr5rhkqcpfiuqqe0q.apps.googleusercontent.com
 client secret: fhkRx0J3Ab0mIFJau6c6pr6E


 * Created by sowmyaparameshwara on 3/19/17.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}