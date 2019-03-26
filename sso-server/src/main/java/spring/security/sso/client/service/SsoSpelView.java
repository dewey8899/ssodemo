package spring.security.sso.client.service;

import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by deweydu
 * Date on 2019/3/26
 */
public class SsoSpelView implements View {
    private String string;
    public SsoSpelView(String string){
        this.string = string;
    }
    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

    }
}
