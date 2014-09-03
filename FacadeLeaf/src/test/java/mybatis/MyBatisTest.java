package mybatis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.elfleaf.models.user.bean.User;
import com.elfleaf.models.user.dao.UserDAO;
import com.elfleaf.utils.UtilsSpringTest;

public class MyBatisTest extends UtilsSpringTest{
    
    @Autowired
    private UserDAO userDAO;
    
    @SuppressWarnings("unused")
    @Test
    public void test() {
        User user = userDAO.findLoginUserInfo("adsfa", "email");
        System.out.println(user.getPassword());
    }
}

