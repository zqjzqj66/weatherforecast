package cn.edu.zzuli.weatherforecast.mapper;

import cn.edu.zzuli.weatherforecast.bean.LoginRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LoginRecordMapper {

    LoginRecord selectLoginRecordById(Integer loginId);

    //LoginRecord selectLoginRecordByIdCascade(Integer loginId);

    List<LoginRecord> selectLoginRecordByInfo(Map<String,Object> info);

    //List<LoginRecord> selectLoginRecordByInfoCascade(Map<String,Object> info);

    boolean insertLoginRecord(LoginRecord loginRecord);

    boolean deleteLoginRecordByinfo(Map<String,Object> info);

    boolean deleteLoginRecordById(Integer loginId);

    boolean updateLoginRecord(LoginRecord loginRecord);
}