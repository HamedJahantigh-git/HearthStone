package network.protocol;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class NetworkProtocol implements Serializable {

    private String authToken;
    private ProtocolType protocolType;
    private Map<ParameterTyp, String> parameter;
    private ShopProtocol shopProtocol;

    public NetworkProtocol(String authToken, ProtocolType protocolType) {
        this.protocolType = protocolType;
        this.parameter = new HashMap<>();
        this.authToken = authToken;
        this.shopProtocol = null;
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public void error(ErrorNetworkEnum errorNetworkEnum) {
        parameter.put(ParameterTyp.ERROR, errorNetworkEnum.name());
    }

    public void signInUp(String username, String password) {
        parameter.put(ParameterTyp.USERNAME, username);
        parameter.put(ParameterTyp.PASSWORD, password);
    }

    public void shop(ShopParameter shopParameter){
        shopProtocol = new ShopProtocol(shopParameter);
    }

    public String getAuthToken() {
        return authToken;
    }

    public Map<ParameterTyp, String> getParameter() {
        return parameter;
    }

    public void deleteAccount(String password) {
        parameter.put(ParameterTyp.PASSWORD, password);
    }

    public ShopProtocol getShopProtocol() {
        return shopProtocol;
    }

    public void setShopProtocol(ShopProtocol shopProtocol) {
        this.shopProtocol = shopProtocol;
    }
}
