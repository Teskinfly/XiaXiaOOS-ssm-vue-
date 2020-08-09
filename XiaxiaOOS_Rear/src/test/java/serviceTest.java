import com.teskinfly.domain.Category;
import com.teskinfly.domain.Food;
import com.teskinfly.domain.User;
import com.teskinfly.service.impl.CategoryService;
import com.teskinfly.service.impl.FoodService;
import com.teskinfly.service.impl.UserService;
import org.apache.ibatis.io.Resources;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

public class serviceTest {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userService", UserService.class);
//        List<User> listUser = userService.getListUser(3, 1);
//        System.out.println(listUser);
//        List<User> list = userService.searchUser("林");
//        System.out.println(list);
//        userService.delUser(9);
        CategoryService categoryService = context.getBean("categoryService", CategoryService.class);
        List<Category> all = categoryService.findAll();
        for (Category category : all) {
            List<Food> foods = category.getFoods();
            System.out.println(category);
            System.out.println(foods);
            System.out.println("-----");
        }
    }
}
