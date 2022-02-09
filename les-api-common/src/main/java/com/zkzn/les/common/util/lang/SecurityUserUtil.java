package com.zkzn.les.common.util.lang;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkzn.les.common.pojo.user.SecurityUser;
import com.zkzn.les.common.util.redis.RedisUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class SecurityUserUtil {

    /**
     * pc端获取当前用户所属仓库编码  返回格式：仓库编码;仓库编码;
     * @param redisTemplate
     * @param currUid
     * @return
     */
    public static String getWarehouseCodeByUserId(RedisTemplate<String, String> redisTemplate, String currUid) {
        String warehouseCode = "";
        String warehouse = (String) RedisUtil.getCache(redisTemplate, currUid + "_warehouse");
        if (warehouse != null && warehouse.length() > 0) {
            JSONObject jsonObject = JSON.parseObject(warehouse);
            warehouseCode = (String) jsonObject.get("orgCode");
        }
        return warehouseCode;
    }

    /**
     * APP获取当前用户选中仓库编码
     * @param redisTemplate
     * @param currUid
     * @return
     */
    public static String getAppWarehouseCodeByUserId(RedisTemplate<String, String> redisTemplate, String currUid) {
        String warehouseCode = "";
        String warehouse = (String) RedisUtil.getCache(redisTemplate, currUid + "_app_storage");
        JSONObject jsonObject = JSON.parseObject(warehouse);
        warehouseCode = (String) jsonObject.get("orgCode");
        return warehouseCode;
    }

    /**
     * APP获取当前用户选中仓库名称
     * @param redisTemplate
     * @param currUid
     * @return
     */
    public static String getAppWarehouseNameByUserId(RedisTemplate<String, String> redisTemplate, String currUid) {
        String warehouseName = "";
        String warehouse = (String) RedisUtil.getCache(redisTemplate, currUid + "_app_storage");
        JSONObject jsonObject = JSON.parseObject(warehouse);
        warehouseName = (String) jsonObject.get("orgName");
        return warehouseName;
    }

    /**
     * 获取当前用户id
     * @param request
     * @return
     */
    public static String getCurrentUserId(HttpServletRequest request) {
        Map<String, Object> obj = (Map<String, Object>) getCurrentPrinciPal(request);
        if (obj == null) {
            return null;
        }
        return (String) obj.get("id");
    }

    /**
     * 获取当前用户姓名
     * @param request
     * @return
     */
    public static String getCurrentUserName(HttpServletRequest request) {
        Map<String, Object> obj = (Map<String, Object>) getCurrentPrinciPal(request);
        if (obj == null) {
            return null;
        }
        return (String) obj.get("userName");
    }



































    /**
     * 获取当前用户
     *
     * @return
     */
    public static OAuth2Authentication getOAuth2Authentication(HttpServletRequest request) {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
                .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        OAuth2Authentication u = (OAuth2Authentication) securityContextImpl.getAuthentication();
        return u;
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public static Authentication getAuthentication(HttpServletRequest request) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication == null) {
            OAuth2Authentication u = getOAuth2Authentication(request);
            authentication = u.getUserAuthentication();
        }

        return authentication;
    }

    public static Object getCurrentPrinciPal(HttpServletRequest request) {

        Authentication authentication = getAuthentication(request);

        SecurityUser user = (SecurityUser) authentication.getPrincipal();

        Map<String, Object> objMap = null;

        if (user != null) {
            objMap = BeanUtil.objectToMap(user);
        } else {
            objMap = (Map<String, Object>) authentication.getDetails();
        }
        //Map<String,Object> objMap = (Map<String, Object>) authentication.getDetails();

        return objMap;
    }





    /**
     * .
     * <p>
     * 功能描述：获取当前仓库对应的仓库地点编码
     * 作者：wangzhou
     * 时间：2018年10月17日
     *
     * @param currUid
     * @return
     */
    public static List<String> getCurrStorageCode(String currUid, RedisTemplate<String, String> redisTemplate, String type) {
        List<String> resultList = new ArrayList<String>();
        String currentStorage = getCacheStorage(type, redisTemplate, currUid);
        JSONObject jsonObject = JSON.parseObject(currentStorage);
        currentStorage = jsonObject.getString("storageLocation");
        if (currentStorage != null && currentStorage.length() > 0) {
            if (currentStorage.indexOf(";") > 0) {
                resultList.addAll(Arrays.asList(currentStorage.split(";")));
            } else {
                resultList.add(currentStorage);
            }
        }
        return resultList;
    }

    /**
     * .
     * <p>
     * 功能描述：获取当前仓库的仓库名称
     * 作者：wangzhou
     * 时间：2018年10月18日
     *
     * @param currUid
     * @param redisTemplate
     * @param type
     * @return
     */
    public static String getCurrStorageName(String currUid, RedisTemplate<String, String> redisTemplate, String type) {
        String currentStorage = getCacheStorage(type, redisTemplate, currUid);
        if (currentStorage != null && currentStorage.length() > 0) {
            JSONObject jsonObject = JSON.parseObject(currentStorage);
            currentStorage = jsonObject.getString("orgName");
        } else {
            currentStorage = "";
        }
        return currentStorage;
    }

    /**
     * .
     * <p>
     * 功能描述：获取当前仓库的编码（组织表的编码）
     * 作者：wangzhou
     * 时间：2018年10月18日
     *
     * @param currUid
     * @param redisTemplate
     * @param type
     * @return
     */
    public static String getCurrOrgCode(String currUid, RedisTemplate<String, String> redisTemplate, String type) {
        String currentStorage = getCacheStorage(type, redisTemplate, currUid);
        if (currentStorage != null && currentStorage.length() > 0) {
            JSONObject jsonObject = JSON.parseObject(currentStorage);
            currentStorage = jsonObject.getString("orgCode");
        } else {
            currentStorage = "";
        }
        return currentStorage;
    }

    /**
     * 功能描述：获取缓存中的库存地点
     * 作者：wangzhou
     * 时间：2018年10月18日
     *
     * @param type
     * @param redisTemplate
     * @param currUid
     * @return
     */
    public static String getCacheStorage(String type, RedisTemplate<String, String> redisTemplate, String currUid) {
        String storageStr = "";
        if ("app".equals(type)) {
            storageStr = (String) RedisUtil.getCache(redisTemplate, currUid + "_app_storage");
        } else if ("pc".equals(type)) {
            storageStr = (String) RedisUtil.getCache(redisTemplate, currUid + "_pc_storage");
        }
        return storageStr;
    }

    /**
     * .
     *
     * @param redisTemplate
     * @param currUid
     * @return
     * @Author:wangzhou
     * @date:2020年8月12日
     * @Description:获取用户有权限的仓库号
     */
    public static List<String> getUserOrgCode(RedisTemplate<String, String> redisTemplate, String currUid) {

        List<String> resultList = new ArrayList<String>();
        String warehouseStr = (String) RedisUtil.getCache(redisTemplate, currUid + "_warehouse");
        if (warehouseStr != null && warehouseStr.length() > 0) {
            JSONObject jsonObject = JSON.parseObject(warehouseStr);
            String orgCodes = (String) jsonObject.get("orgCode");

            if (orgCodes != null && orgCodes.length() > 0) {
                if (orgCodes.indexOf(";") > 0) {
                    resultList.addAll(Arrays.asList(orgCodes.split(";")));
                } else {
                    resultList.add(orgCodes);
                }
            }
        }

        return resultList;
    }

    /**
     * .
     *
     * @param redisTemplate
     * @param currUid
     * @return
     * @Author:wangzhou
     * @date:2020年8月12日
     * @Description:获取用户有权限的仓库名称
     */
    public static List<String> getUserOrgName(RedisTemplate<String, String> redisTemplate, String currUid) {
        List<String> resultList = new ArrayList<String>();
        String warehouseStr = (String) RedisUtil.getCache(redisTemplate, currUid + "_warehouse");
        if (warehouseStr != null && warehouseStr.length() > 0) {
            JSONObject jsonObject = JSON.parseObject(warehouseStr);
            String orgNames = (String) jsonObject.get("orgName");

            if (orgNames != null && orgNames.length() > 0) {
                if (orgNames.indexOf(";") > 0) {
                    resultList.addAll(Arrays.asList(orgNames.split(";")));
                } else {
                    resultList.add(orgNames);
                }
            }
        }

        return resultList;
    }

    /**
     * .
     *
     * @param redisTemplate
     * @param currUid
     * @return
     * @Author:wangzhou
     * @date:2020年8月12日
     * @Description:获取用户有权限的库存地点
     */
    public static List<String> getUserStorageLocation(RedisTemplate<String, String> redisTemplate, String currUid) {
        List<String> resultList = new ArrayList<String>();
        String warehouseStr = (String) RedisUtil.getCache(redisTemplate, currUid + "_warehouse");
        if (warehouseStr != null && warehouseStr.length() > 0) {
            JSONObject jsonObject = JSON.parseObject(warehouseStr);
            String storageLocations = (String) jsonObject.get("storageLocation");

            if (storageLocations != null && storageLocations.length() > 0) {
                if (storageLocations.indexOf(";") > 0) {
                    resultList.addAll(Arrays.asList(storageLocations.split(";")));
                } else {
                    resultList.add(storageLocations);
                }
            }
        }

        return resultList;
    }
}
