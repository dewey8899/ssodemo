package spring.security.sso.service;

import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by deweydu
 * Date on 2019/3/26
 */
public class SsoSpelView implements View {
    private final String template;

    private final StandardEvaluationContext context = new StandardEvaluationContext();

    private PropertyPlaceholderHelper helper;

    private PropertyPlaceholderHelper.PlaceholderResolver resolver;

    public SsoSpelView(String template){
        this.template = template;
        this.context.addPropertyAccessor(new MapAccessor());//初始化
        this.helper = new PropertyPlaceholderHelper("${", "}");
        this.resolver = new PropertyPlaceholderHelper.PlaceholderResolver() {
            @Override
            public String resolvePlaceholder(String s) {
                return null;
            }
        };
    }
    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        if (httpServletResponse.getContentType()!=null){
            httpServletResponse.setContentType(getContentType());
        }
        Map<String, Object> map = new HashMap<>(model);
        map.put("path", httpServletRequest.getContextPath());
        this.context.setRootObject(map);
        String result = this.helper.replacePlaceholders(this.template, this.resolver);
        httpServletResponse.getWriter().append(result);
    }
}
