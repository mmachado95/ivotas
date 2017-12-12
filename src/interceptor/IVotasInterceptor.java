package interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.util.Map;

public class IVotasInterceptor implements Interceptor {
  private static final long serialVersionUID = 189237412378L;

  @Override
  public String intercept(ActionInvocation invocation) throws Exception {
    Map<String, Object> session = invocation.getInvocationContext().getSession();

    if (session.get("username") != null && session.get("loggedin") != null && session.get("loggedin").equals(true)) {
      return invocation.invoke();
    } else {
      return Action.INPUT;
    }
  }

  @Override
  public void init() { }

  @Override
  public void destroy() { }
}