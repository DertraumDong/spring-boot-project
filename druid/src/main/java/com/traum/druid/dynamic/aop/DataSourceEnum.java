package com.traum.druid.dynamic.aop;

/**
 * 名称：
 * 阐述：
 *
 * @author Administrator
 * @date 2022/7/11 20:31
 */
public enum DataSourceEnum {

    /**
     * 数据库
     */
    TRAUM("1","traum"),
    /**
     * 数据库2
     */
    TRAUM_2("2","traum_2");

    /**
     * 租户id
     * */
    private String tendId;

    /**
     * 数据库
     * */
    private String dataName;

    DataSourceEnum(String tendId,String dataName){
        this.tendId = tendId;
        this.dataName = dataName;
    }

    private String getDataName(){
        return this.dataName;
    }
    private String getTendId(){
        return this.tendId;
    }

    public static String getDataNameByTendId(String tendId){
        for (DataSourceEnum value : DataSourceEnum.values()) {
            if(value.getTendId().equals(tendId)){
                return value.getDataName();
            }
        }
        return null;
    }
}
