package my;


import com.demo.spring.boot2.Application;
import com.demo.spring.boot2.mapper.manual.UserExtMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITest {

    @Resource
    private UserExtMapper userExtMapper;
    @Test
    public void DaoTest(){

    }

}
