package com.car.mapper;

import com.car.entity.TbCarEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.car.entity.dto.SpeedDTO;
import com.car.entity.vo.SpeedVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface TbCarMapper extends BaseMapper<TbCarEntity> {

    /**
     * 多天测速数据
     * @param speedDTO
     * @return
     */
    public List<SpeedVO> getMoreDayCars(@Param("speedDTO") SpeedDTO speedDTO);

    /**
     * 单天测速数据
     * @param speedDTO
     * @return
     */
    public List<SpeedVO> getOneDayCars(@Param("speedDTO") SpeedDTO speedDTO);

    /**
     *
     * @param gunId
     * @param carId
     * @return
     */
    public SpeedVO getSpeedVo(@Param("gunId")Integer gunId,@Param("carId") Integer carId);

    /**
     * 导出多天
     * @param speedDTO
     * @return
     */
    public List<SpeedVO> exportMoreDays(@Param("speedDTO") SpeedDTO speedDTO);

    /**
     * 导出单天
     * @param speedDTO
     * @return
     */
    public List<SpeedVO> exportOneDay(@Param("speedDTO") SpeedDTO speedDTO);




    /**
     * 多天总超速比
     * @param speedDTO
     * @return
     */
    public Map<String, Integer> moreDayRatio(@Param("speedDTO") SpeedDTO speedDTO);

    /**
     * 单天总超速比
     * @param speedDTO
     * @return
     */
    public Map<String, Integer> oneDayRatio(@Param("speedDTO") SpeedDTO speedDTO);


    /**
     * 每小时超速比
     * @param speedDTO
     * @param hour
     * @return
     */
    public Map<String, Integer> hourRatio(@Param("speedDTO") SpeedDTO speedDTO, @Param("hour")Integer hour);


}