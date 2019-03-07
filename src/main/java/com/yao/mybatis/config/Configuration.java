package com.yao.mybatis.config;

/**
 * @author biaoy
 * @version 1.0
 * @date 2019/3/6 20:00
 * @description
 */
public class Configuration {

    private String scanPath;

    private MapperRegistry mapperRegistry = new MapperRegistry();

    public String getScanPath() {
        return scanPath;
    }

    public void setScanPath(String scanPath) {
        this.scanPath = scanPath;
    }

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public void setMapperRegistry(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }
}
