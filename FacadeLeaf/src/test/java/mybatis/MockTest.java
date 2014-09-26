package mybatis;

import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.config.WebIniSecurityManagerFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.elfleaf.subjects.userCenter.ctrl.LoginCtrl;
import com.elfleaf.utils.UtilsSpringTest;

public class MockTest extends UtilsSpringTest{
    
    @Autowired
    private LoginCtrl loginCtrl;
    
    @Test
    public void test() {
        
        //生成一个shiro实例
        ThreadContext.put(ThreadContext.SECURITY_MANAGER_KEY, new WebIniSecurityManagerFactory().getInstance());
        
        
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/userCenter/loginPage");
        MockHttpServletResponse response = new MockHttpServletResponse();
        Model model = new RedirectAttributesModelMap();
        try {
            loginCtrl.loginPage(request, response, model);
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
}
