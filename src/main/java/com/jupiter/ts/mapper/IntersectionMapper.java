package com.jupiter.ts.mapper;

import com.jupiter.ts.model.Intersection;
import com.jupiter.ts.model.IntersectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface IntersectionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    long countByExample(IntersectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    int deleteByExample(IntersectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    int insert(Intersection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    int insertSelective(Intersection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    List<Intersection> selectByExampleWithRowbounds(IntersectionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    List<Intersection> selectByExample(IntersectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    Intersection selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    int updateByExampleSelective(@Param("record") Intersection record, @Param("example") IntersectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    int updateByExample(@Param("record") Intersection record, @Param("example") IntersectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    int updateByPrimaryKeySelective(Intersection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    int updateByPrimaryKey(Intersection record);
}