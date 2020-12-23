package com.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.TbUserEntity;
import com.car.service.TbUserService;
import com.car.service.UserService;
import com.car.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mowuwalixilo
 * @date2020/12/16 21:37
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TbUserService tbUserService;

    /***
     * 用户管理接口-添加-具体实现
     * @param tbUserEntity
     * @return
     */
    @Override
    public RStatic addUser(TbUserEntity tbUserEntity){

        String username = tbUserEntity.getUsername();
        try {
            QueryWrapper<TbUserEntity> userCheckQw = new QueryWrapper<>();
            userCheckQw.eq("username",username);
            TbUserEntity userCheck = tbUserService.getOne(userCheckQw);
            if (userCheck == null){
                String password = tbUserEntity.getPassword();
                String md5HaxPassWord = Md5Util.MD5Hax(password);
                tbUserEntity.setPassword(md5HaxPassWord);
                boolean save = tbUserService.save(tbUserEntity);
                if (save){
                    return RStatic.ok("添加成功");
                }else {
                    return RStatic.error("添加失败");
                }
            }else {
                return RStatic.error("用户名已存在");
            }
        }catch (Exception e){
            return RStatic.error("操作有误");
        }
    }

    /***
     * 用户管理接口-删除-具体实现
     * @param map
     * @return
     */
    @Override
    public RStatic deleteUser(Map<String, Object> map) {
        ArrayList<Integer> userList = (ArrayList<Integer>)map.get("ids");
        if (userList == null){
            return RStatic.error("删除内容为空");
        }else {
            boolean removeByIds = tbUserService.removeByIds(userList);
            if (removeByIds){
                return RStatic.ok("删除成功");
            }else {
                return RStatic.error("删除失败");
            }
        }
    }

    /**
     * 用户管理接口-修改-具体实现
     * @param tbUserEntity
     * @return
     */
    @Override
    public RStatic updateUser(TbUserEntity tbUserEntity) throws Exception {

        String username = tbUserEntity.getUsername();
        QueryWrapper<TbUserEntity> userCheckQw = new QueryWrapper<>();
        userCheckQw.eq("username",username);
        TbUserEntity userCheck = tbUserService.getOne(userCheckQw);
        if (userCheck == null || userCheck.getUsername().equals(username)){
            String password = tbUserEntity.getPassword();
            String Md5Password = Md5Util.MD5Hax(password);
            tbUserEntity.setPassword(Md5Password);
            boolean saveOrUpdate = tbUserService.saveOrUpdate(tbUserEntity);
            if (saveOrUpdate){
                return RStatic.ok("修改成功");
            }else {
                return RStatic.error("修改失败");
            }
        }else {
            return RStatic.error("用户名已存在");
        }
    }

    /***
     * 用户管理接口-查询-具体实现
     * @param limit
     * @param page
     * @param items
     * @return
     */
    @Override
    public RStatic selectUser(String limit, int page, int items) {
        IPage<TbUserEntity> userPageList = null;
        try {
            Page<TbUserEntity> userPage = new Page<>(page,items,true);
            if (limit == null){
                userPageList = tbUserService.page(userPage,null);
                return RStatic.ok("查询成功").data("userPageList",userPageList);
            }else {
                QueryWrapper<TbUserEntity> userQw = new QueryWrapper<>();
                userQw
                        .like("username",limit)
                        .or()
                        .like("role",limit);
                userPageList = tbUserService.page(userPage,userQw);
                return RStatic.ok("查询成功").data("userPageList",userPageList);
            }
        }catch (Exception e){
            return RStatic.error("操作有误");
        }
    }

    @Override
    public RStatic login(String userName, String password, HttpServletRequest request) {
        String pwd = null;
        try {
            pwd = Md5Util.MD5Hax(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        QueryWrapper<TbUserEntity> userWrapper = new QueryWrapper<>();
        userWrapper.eq("username",userName).eq("password",pwd);
        TbUserEntity user = tbUserService.getOne(userWrapper);
        if (user == null) {
            return RStatic.error("账号或密码错误");
        }else {
            RuntimeDataUtil.roleMap.put(user.getUsername(),user.getRole());
            Map<String,Object> map = new HashMap<>();
            String ip = NetWorkUtil.getIpAddress(request);
            map.put("username",userName);
            map.put("role",user.getRole());
            String token = TokenUtil.encode(user.getId()+"", map, ip);
            return RStatic.ok("登录成功")
                    .data("user",user)
                    .data("token",token);
        }
    }

    @Override
    public RStatic login(TbUserEntity tbUserEntity) throws Exception {
        return null;
    }

    @Override
    public RStatic login(Map<String, Object> map) throws Exception {
        return null;
    }

    @Override
    public RStatic register(TbUserEntity tUser) throws Exception {
        TbUserEntity userEntity = new TbUserEntity();
        String pwd = Md5Util.MD5Hax(tUser.getPassword());
        userEntity.setUsername(tUser.getUsername());
        userEntity.setPassword(pwd);
        userEntity.setRemark(tUser.getRemark());
        userEntity.setAvatar(tUser.getAvatar());
        userEntity.setRole(tUser.getRole());
        try {
            QueryWrapper<TbUserEntity> userWrapper = new QueryWrapper<>();
            userWrapper.eq("username",tUser.getUsername());
            TbUserEntity one = tbUserService.getOne(userWrapper);
            if (one == null) {
                boolean save = tbUserService.save(userEntity);
                if (save) {
                    return RStatic.ok("注册成功");
                }else {
                    return RStatic.error("注册失败");
                }
            }else {
                return RStatic.error("账号已存在");
            }
        }catch (Exception e) {
            return RStatic.error(e.getMessage());
        }
    }
}
