package io.github.thiago.melo.quarkussocial;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title="API Quarkus Social",
                version = "1.0.1",
                contact = @Contact(
                        name = "Thiago Henrique Melo da Silva",
                        url = "https://www.linkedin.com/in/thiago-henrique-045950b6/",
                        email = "https://www.linkedin.com/in/thiago-henrique-045950b6/"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class QuakusSocialApplication extends Application {
}
