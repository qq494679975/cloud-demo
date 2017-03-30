package com.yihu.cwd.service;

import com.yihu.cwd.model.system.Menu;
import com.yihu.cwd.model.system.Role;
import com.yihu.cwd.model.system.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/3/29.
 */
@Service
public class MenuJavaService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Menu> findByUserId(String userId) {
        String sql = "select r.* from system_user_role ur,system_role r where ur.role_id=r.id and ur.user_id='" + userId + "'";
        List<Role> returnList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Role.class));
        List<String> roleIds = new ArrayList<String>();
        returnList.stream().forEach(userRole -> {
            roleIds.add(userRole.getId());
        });
        if (roleIds.size() > 0) {
            sql = "select distinct m.id,m.fid,m.url,m.name from system_role_menu rm,system_menu m where rm.menu_id=m.id and rm.role_id in(" + listToString(roleIds, ',') + ")";
            List<Menu> returnMenu = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Menu.class));
            return returnMenu;
        }
        return new ArrayList<>();
    }

    public String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i));
                sb.append(separator);
            }
        }
        return sb.toString();
    }
}
