package com.jupiter.ts.dto;

import java.util.List;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jupiter.ts.exception.CustomizeException;
import com.jupiter.ts.exception.ICustomizeErrorCode;

/**
 * description:自定义相应结构
 * @author 谪仙
 *
 */
public class TsResultDto {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static TsResultDto build(Integer status, String msg, Object data) {
        return new TsResultDto(status, msg, data);
    }

    public static TsResultDto ok(Object data) {
        return new TsResultDto(data);
    }

    public static TsResultDto ok() {
        return new TsResultDto(null);
    }

    public TsResultDto() {

    }

    public static TsResultDto build(Integer status, String msg) {
        return new TsResultDto(status, msg, null);
    }

    public TsResultDto(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public TsResultDto(Object data) {
        this.status = 200;
        this.msg = "执行成功";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为TaotaoResult对象
     *
     * @param jsonData json数据
     * @param clazz TaotaoResult中的object类型
     * @return
     */
    public static TsResultDto formatToPojo(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz == null) {
                return MAPPER.readValue(jsonData, TsResultDto.class);
            }else {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static TsResultDto format(String json) {
        try {
            return MAPPER.readValue(json, TsResultDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static TsResultDto formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    public static TsResultDto build(CustomizeException e) {
        return TsResultDto.build(e.getStatus(),e.getMessage());
    }

    public static TsResultDto build(ICustomizeErrorCode e) {
        return TsResultDto.build(e.getStatus(),e.getMsg());
    }
}
