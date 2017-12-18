package action.facebook;

import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Post {
  private final String NETWORK_NAME = "Facebook";
  private String postUrl = "https://graph.facebook.com/";
  private final String clientId = "1486604264709457";
  private final String clientSecret = "d51d5ed0af4f56607780576b0b8eda3c";

  public void votePost(String facebookID, String accessToken, String message) {
    postUrl += facebookID +  "/";
    postUrl += "feed?message=" + message + "&";
    postUrl += "access_token=" + accessToken;

    final OAuth20Service service = new ServiceBuilder(clientId)
            .apiSecret(clientSecret)
            .callback("http://127.0.0.1:8080/")
            .scope("publish_actions")
            .build(FacebookApi.instance());

    try {
      // Get user id and name
      final OAuthRequest request = new OAuthRequest(Verb.POST, postUrl);
      service.signRequest(accessToken, request);

      // Get response from API
      final Response response = service.execute(request);
      System.out.println(response);
      System.out.println(response.getCode());
      System.out.println(response.getMessage());
      System.out.println(response.getBody());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
