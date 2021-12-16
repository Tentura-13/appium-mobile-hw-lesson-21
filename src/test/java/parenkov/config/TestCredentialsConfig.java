package parenkov.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/test.properties"})
public interface TestCredentialsConfig extends Config {
    @Key("username")
    String getUsername();

    @Key("password")
    String getPassword();
}
