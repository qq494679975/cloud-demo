package com.yihu.cwd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

public class BaseController {

    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected HttpServletRequest request;

    /**
     * 獲取髮送請求用戶的uid
     *
     * @return
     */
    public String getUID() {
        try {
            String userAgent = request.getHeader("userAgent");
            if (StringUtils.isEmpty(userAgent)) {
                userAgent = request.getHeader("User-Agent");
            }
            JSONObject json = new JSONObject(userAgent);
            return json.getString("uid");

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 獲取髮送請求用戶的uid
     *
     * @return
     */
    public String getLastUid() {
        try {
            String userAgent = request.getHeader("userAgent");
            if (StringUtils.isEmpty(userAgent)) {
                userAgent = request.getHeader("User-Agent");
            }
            JSONObject json = new JSONObject(userAgent);
            return json.getString("lastUid");

        } catch (Exception e) {
            return null;
        }
    }

    public String getOpenid() {
        try {
            String userAgent = request.getHeader("userAgent");
            if (StringUtils.isEmpty(userAgent)) {
                userAgent = request.getHeader("User-Agent");
            }
            JSONObject json = new JSONObject(userAgent);
            return json.getString("openid");

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取用户ID
     *
     * @return
     */
    public long getId() {
        try {
            String userAgent = request.getHeader("userAgent");
            if (StringUtils.isEmpty(userAgent)) {
                userAgent = request.getHeader("User-Agent");
            }
            JSONObject json = new JSONObject(userAgent);
            return json.getLong("id");
        } catch (Exception e) {
            return 0;
        }
    }

    public String getIMEI() {
        try {
            String userAgent = request.getHeader("userAgent");
            if (StringUtils.isEmpty(userAgent)) {
                userAgent = request.getHeader("User-Agent");
            }
            JSONObject json = new JSONObject(userAgent);
            return json.getString("imei");
        } catch (Exception e) {
            return null;
        }
    }

    public String getToken() {
        try {
            String userAgent = request.getHeader("userAgent");
            if (StringUtils.isEmpty(userAgent)) {
                userAgent = request.getHeader("User-Agent");
            }
            JSONObject json = new JSONObject(userAgent);
            return json.getString("token");
        } catch (Exception e) {
            return null;
        }
    }

    public void error(Exception e) {
        logger.error(getClass().getName() + ":", e.getMessage());
        e.printStackTrace();
    }

    public void warn(Exception e) {
        logger.warn(getClass().getName() + ":", e.getMessage());
        e.printStackTrace();
    }

    public void infoMessage(String message) {
        logger.info(message);
    }

    /**
     * 返回接口处理结果
     *
     * @param code  结果码，成功为200
     * @param msg   结果提示信息
     * @return
     */
    public String error(int code, String msg) {
        try {
            Map<Object, Object> map = new HashMap<Object, Object>();
            ObjectMapper mapper = new ObjectMapper();
            map.put("status", code);
            map.put("msg", msg);
            return mapper.writeValueAsString(map);
        } catch (Exception e) {
            error(e);
            return null;
        }
    }

    /**
     * 接口处理成功
     *
     * @param msg
     * @return
     */
    public String success(String msg) {
        try {
            Map<Object, Object> map = new HashMap<Object, Object>();
            ObjectMapper mapper = new ObjectMapper();
            map.put("status", 200);
            map.put("msg", msg);
            return mapper.writeValueAsString(map);
        } catch (Exception e) {
            error(e);
            return null;
        }
    }

    public String write(int code, String msg) {
        try {
            Map<Object, Object> map = new HashMap<Object, Object>();
            ObjectMapper mapper = new ObjectMapper();
            map.put("status", code);
            map.put("msg", msg);
            return mapper.writeValueAsString(map);
        } catch (Exception e) {
            error(e);
            return null;
        }
    }

    /**
     * 返回接口处理结果
     *
     * @param code  结果码，成功为200
     * @param msg   结果提示信息
     * @return
     */
    public String write(int code, String msg, String key, List<?> list) {
        try {
            Map<Object, Object> map = new HashMap<Object, Object>();
            ObjectMapper mapper = new ObjectMapper();
            map.put("status", code);
            map.put("msg", msg);
            map.put(key, list);
            return mapper.writeValueAsString(map);
        } catch (Exception e) {
            error(e);
            return error(-1, "服务器异常，请稍候再试！");
        }
    }

    /**
     * 返回接口处理结果
     *
     * @param code  结果码，成功为200
     * @param msg   结果提示信息
     * @param value 结果数据
     * @return
     */
    public String write(int code, String msg, String key, JSONObject value) {
        try {
            JSONObject json = new JSONObject();
            json.put("status", code);
            json.put("msg", msg);
            json.put(key, value);
            return json.toString();
        } catch (Exception e) {
            error(e);
            return error(-1, "服务器异常，请稍候再试！");
        }
    }

    /**
     * 返回接口处理结果
     *
     * @param code  结果码，成功为200
     * @param msg   结果提示信息
     * @param value 结果数据
     * @return
     */
    public String write(int code, String msg, String key, JSONArray value) {
        try {
            JSONObject json = new JSONObject();
            json.put("status", code);
            json.put("msg", msg);
            json.put(key, value);
            return json.toString();
        } catch (Exception e) {
            error(e);
            return error(-1, "服务器异常，请稍候再试！");
        }
    }

    /**
     * 返回接口处理结果
     *
     * @param code  结果码，成功为200
     * @param msg   结果提示信息
     * @param total 总数
     * @param value 结果数据
     * @return
     */
    public String write(int code, String msg, int total, String key, JSONArray value) {
        try {
            JSONObject json = new JSONObject();
            json.put("status", code);
            json.put("msg", msg);
            json.put("total", total);
            json.put(key, value);
            return json.toString();
        } catch (Exception e) {
            error(e);
            return error(-1, "服务器异常，请稍候再试！");
        }
    }

    /**
     * 返回接口处理结果
     *
     * @param code  结果码，成功为200
     * @param msg   结果提示信息
     * @param value 结果数据
     * @return
     */
    public String write(int code, String msg, String key, Object value) {
        try {
            Map<Object, Object> map = new HashMap<Object, Object>();
            ObjectMapper mapper = new ObjectMapper();
            map.put("status", code);
            map.put("msg", msg);
            map.put(key, value);
            return mapper.writeValueAsString(map);
        } catch (Exception e) {
            error(e);
            return error(-1, "服务器异常，请稍候再试！");
        }
    }

    /**
     * 返回接口处理结果
     *
     * @param code  结果码，成功为200
     * @param msg   结果提示信息
     * @return
     */
    public String write(int code, String msg, String key, Page<?> list) {
        try {
            Map<Object, Object> map = new HashMap<Object, Object>();
            ObjectMapper mapper = new ObjectMapper();
            map.put("status", code);
            map.put("msg", msg);
            // 是否为第一页
            map.put("isFirst", list.isFirst());
            // 是否为最后一页
            map.put("isLast", list.isLast());
            // 总条数
            map.put("total", list.getTotalElements());
            // 总页数
            map.put("totalPages", list.getTotalPages());
            map.put(key, list.getContent());
            return mapper.writeValueAsString(map);
        } catch (Exception e) {
            error(e);
            return error(-1, "服务器异常，请稍候再试！");
        }
    }

    /**
     * 返回接口处理结果
     *
     * @param code  结果码，成功为200
     * @param msg   结果提示信息
     * @return
     */
    public String write(int code, String msg, String key, Page<?> page, JSONArray array) {
        try {
            JSONObject json = new JSONObject();
            json.put("status", code);
            json.put("msg", msg);
            // 是否为第一页
            json.put("isFirst", page == null ? false : page.isFirst());
            // 是否为最后一页
            json.put("isLast", page == null ? true : page.isLast());
            // 总条数
            json.put("total", page == null ? 0 : page.getTotalElements());
            // 总页数
            json.put("totalPages", page == null ? 0 : page.getTotalPages());
            json.put(key, array);
            return json.toString();
        } catch (Exception e) {
            error(e);
            return error(-1, "服务器异常，请稍候再试！");
        }
    }

    /**
     * 返回接口处理结果
     *
     * @param code  结果码，成功为200
     * @param msg   结果提示信息
     * @param value 结果数据
     * @return
     */
    public String write(int code, String msg, String key, Map<?, ?> value) {
        try {
            Map<Object, Object> map = new HashMap<Object, Object>();
            ObjectMapper mapper = new ObjectMapper();
            map.put("status", code);
            map.put("msg", msg);
            map.put(key, value);
            return mapper.writeValueAsString(map);
        } catch (Exception e) {
            error(e);
            return error(-1, "服务器异常，请稍候再试！");
        }
    }

    /**
     * 返回接口处理结果
     *
     * @param code  结果码，成功为200
     * @param msg   结果提示信息
     * @param value 结果数据
     * @return
     */
    public String write(int code, String msg, String key, String value) {
        try {
            Map<Object, Object> map = new HashMap<Object, Object>();
            ObjectMapper mapper = new ObjectMapper();
            map.put("status", code);
            map.put("msg", msg);
            map.put(key, value);
            return mapper.writeValueAsString(map);
        } catch (Exception e) {
            error(e);
            return error(-1, "服务器异常，请稍候再试！");
        }
    }


    /**
     * 返回接口处理结果
     *
     * @param code  结果码，成功为200
     * @param msg   结果提示信息
     * @return
     */
    public String write(int code, String msg, boolean isFirst, boolean isLast, long total, int totalPages, String key, Object values) {
        try {
            JSONObject json = new JSONObject();
            json.put("status", code);
            json.put("msg", msg);
            // 是否为第一页
            json.put("isFirst", isFirst);
            // 是否为最后一页
            json.put("isLast", isLast);
            // 总条数
            json.put("total", total);
            // 总页数
            json.put("totalPages", totalPages);
            json.put(key, values);
            return json.toString();
        } catch (Exception e) {
            logger.error("BaseController:", e.getMessage());
            return error(-1, "服务器异常，请稍候再试！");
        }
    }

    public String trimEnd(String param, String trimChars) {
        if (param.endsWith(trimChars)) {
            param = param.substring(0, param.length() - trimChars.length());
        }
        return param;
    }

    /**
     * 无效用户消息返回
     *
     * @param e
     * @param defaultCode
     * @param defaultMsg
     * @return
     */
    public String invalidUserException(Exception e, int defaultCode, String defaultMsg) {
        try {
            // if (e instanceof UndeclaredThrowableException) {
            // UndeclaredThrowableException ute = (UndeclaredThrowableException) e;
            // InvalidUserException iue = (InvalidUserException) ute.getUndeclaredThrowable();
            // if (iue != null) {
            // return error(iue.getCode(), iue.getMsg());
            // }
            // }
            return error(defaultCode, defaultMsg);
        } catch (Exception e2) {
            return null;
        }
    }

    public List<Map<String, Object>> copyBeans(Collection<? extends Object> beans, String... propertyNames) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object bean : beans) {
            result.add(copyBeanProperties(bean, propertyNames));
        }

        return result;
    }

    /**
     * 复制特定属性。
     *
     * @param bean
     * @param propertyNames
     * @return
     */
    public Map<String, Object> copyBeanProperties(Object bean, String... propertyNames) {
        Map<String, Object> simplifiedBean = new HashMap<>();
        for (String propertyName : propertyNames) {
            Field field = ReflectionUtils.findField(bean.getClass(), propertyName);
            if (field != null) {
                field.setAccessible(true);
                Object value = ReflectionUtils.getField(field, bean);
                simplifiedBean.put(propertyName, value == null ? "" : value);
            } else {
                simplifiedBean.put(propertyName, "");
            }
        }

        return simplifiedBean;
    }
}
