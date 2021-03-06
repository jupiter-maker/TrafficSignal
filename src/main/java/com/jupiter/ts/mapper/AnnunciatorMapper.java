package com.jupiter.ts.mapper;

import com.jupiter.ts.model.Annunciator;
import com.jupiter.ts.model.AnnunciatorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

@Component
public interface AnnunciatorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_xh
     *
     * @mbg.generated Sat Nov 09 16:43:33 GMT+08:00 2019
     */
    long countByExample(AnnunciatorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_xh
     *
     * @mbg.generated Sat Nov 09 16:43:33 GMT+08:00 2019
     */
    int deleteByExample(AnnunciatorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_xh
     *
     * @mbg.generated Sat Nov 09 16:43:33 GMT+08:00 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_xh
     *
     * @mbg.generated Sat Nov 09 16:43:33 GMT+08:00 2019
     */
    int insert(Annunciator record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_xh
     *
     * @mbg.generated Sat Nov 09 16:43:33 GMT+08:00 2019
     */
    int insertSelective(Annunciator record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_xh
     *
     * @mbg.generated Sat Nov 09 16:43:33 GMT+08:00 2019
     */
    List<Annunciator> selectByExampleWithRowbounds(AnnunciatorExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_xh
     *
     * @mbg.generated Sat Nov 09 16:43:33 GMT+08:00 2019
     */
    List<Annunciator> selectByExample(AnnunciatorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_xh
     *
     * @mbg.generated Sat Nov 09 16:43:33 GMT+08:00 2019
     */
    Annunciator selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_xh
     *
     * @mbg.generated Sat Nov 09 16:43:33 GMT+08:00 2019
     */
    int updateByExampleSelective(@Param("record") Annunciator record, @Param("example") AnnunciatorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_xh
     *
     * @mbg.generated Sat Nov 09 16:43:33 GMT+08:00 2019
     */
    int updateByExample(@Param("record") Annunciator record, @Param("example") AnnunciatorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_xh
     *
     * @mbg.generated Sat Nov 09 16:43:33 GMT+08:00 2019
     */
    int updateByPrimaryKeySelective(Annunciator record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_xh
     *
     * @mbg.generated Sat Nov 09 16:43:33 GMT+08:00 2019
     */
    int updateByPrimaryKey(Annunciator record);
}