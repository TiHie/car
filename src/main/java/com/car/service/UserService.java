package com.car.service;

import com.car.entity.TbUserEntity;
import com.car.util.RStatic;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author mowuwalixilo
 * @date2020/12/16 21:36
 */
public interface UserService {

    /***
     * 用户管理接口-添加
     * @param tbUserEntity
     * @return
     */
    public RStatic addUser(TbUserEntity tbUserEntity);

    /***
     * 用户管理接口-删除
     * @param map
     * @return
     */
    public RStatic deleteUser(Map<String, Object> map);

    /***
     * 用户管理接口-修改
     * @param tbUserEntity
     * @return
     */
    public RStatic updateUser(TbUserEntity tbUserEntity);

    /***
     * 用户管理接口-查询
     * @param limit
     * @param page
     * @param items
     * @return
     */
    public RStatic selectUser(String limit,int page,int items);


    /**
     * 登录
     * @param userName
     * @param password
     * @param request
     * 用户登录
     * @param map
     * @return
     * @throws  Exception
     */
    public RStatic login(String userName, String password, HttpServletRequest request);
    public RStatic login(@RequestBody TbUserEntity tbUserEntity) throws Exception;
    public RStatic login(@RequestBody Map<String, Object> map) throws Exception;

    /**
     * 管理员生成账号
     * @param tbUserEntity
     * @return
     * @throws Exception
     */
    public RStatic register(@RequestBody TbUserEntity tbUserEntity) throws Exception;
}
