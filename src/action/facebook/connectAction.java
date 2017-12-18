package action.facebook;

import Data.User;
import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.interceptor.SessionAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class connectAction extends ActionSupport implements SessionAware {
  private static final long serialVersionUID = 4231L;
  private Map<String, Object> session;
  public String code = null;
  private final String NETWORK_NAME = "Facebook";
  private final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/me";
  private final String clientId = "1486604264709457";
  private final String clientSecret = "d51d5ed0af4f56607780576b0b8eda3c";

  @Override
  public String execute() {
    System.out.println(code);

    // Build service to send request to Facebook
    final OAuth20Service service = new ServiceBuilder(clientId)
            .apiSecret(clientSecret)
            .callback("http://127.0.0.1:8080/connectFacebook")
            .scope("publish_actions")
            .build(FacebookApi.instance());

    final OAuth2AccessToken accessToken;
    try {
      accessToken = service.getAccessToken(code);

      // Get user id and name
      final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
      service.signRequest(accessToken, request);

      // Get response from API
      final Response response = service.execute(request);
      System.out.println(response);
      System.out.println(response.getCode());
      System.out.println(response.getMessage());
      System.out.println(response.getBody());

      // Parse response
      JSONObject body = (JSONObject) JSONValue.parse(response.getBody());

      // Update user attributes with facebook association
      User user = this.getIVotasBean().getUserByName((String)session.get("username"));
      user.setFacebookID((String)body.get("id"));
      user.setFacebookName((String)body.get("name"));
      user.setFacebookAccessToken(accessToken.getAccessToken());
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }


  public IVotasBean getIVotasBean() {
    if(!session.containsKey("iVotasBean")) {
      this.setHeyBean(new IVotasBean());
    }
    return (IVotasBean) session.get("iVotasBean");
  }

  public void setHeyBean(IVotasBean iVotasBean) {
    this.session.put("iVotasBean", iVotasBean);
  }

  @Override
  public void setSession(Map<String, Object> session) {
    this.session = session;
  }
}
