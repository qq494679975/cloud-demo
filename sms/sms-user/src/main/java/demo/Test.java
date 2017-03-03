package demo;

import com.yihu.cwd.user.model.User;

import java.util.*;

/**
 * Created by Administrator on 2017/2/27.
 */
public class Test {

    public static List<User> createUser() {
        return Arrays.asList(
                new User(1, "张三", new Date()),
                new User(2, "赵毅", new Date()),
                new User(3, "李四", new Date()),
                new User(4, "王五", new Date()));
    }

    public static void main(String[] args) {
        List<User> users = createUser();
        System.out.println(users.stream().count());
        users.stream().map(u -> {
            Map<String, User> params = new HashMap<>();
            params.put(u.getId() + "", u);
            return params;
        }).forEach(map -> {
            System.out.println(map.toString());
        });
    }
}
