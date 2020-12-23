package com.car.service;

import com.car.entity.TbUserEntity;
import com.car.util.RStatic;
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
    public RStatic updateUser(TbUserEntity tbUserEntity) throws Exception;

    /***
     * 用户管理接口-查询
     * @param limit
     * @param page
     * @param items
     * @return
     */
    public RStatic selectUser(String limit,int page,int items);
}
