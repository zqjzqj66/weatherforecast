package cn.edu.zzuli.weatherforecast.mapper;

import cn.edu.zzuli.weatherforecast.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    User selectUserById(Integer userId);

    //User selectUserByIdCascade(Integer userId);

    List<User> selectUserByInfo(Map<String,Object> info);

    //List<User> selectUserByInfoCascade(Map<String,Object> info);

    boolean insertUser(User user);

    boolean deleteUserByinfo(Map<String,Object> info);

    boolean deleteUserById(Integer userId);

    boolean updateUser(Map<String ,Object> info);

}