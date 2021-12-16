package parenkov.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/browserstack.properties"})
public interface BrowserStackConfig extends Config {
    @Key("user")
    String getUser();

    @Key("key")
    String getKey();

    @Key("url")
    String getUrl();

    @Key("appUrl")
    String getAppUrl();

    @Key("device")
    String getDevice();

    @Key("osVersion")
    String getOSVersion();
}
