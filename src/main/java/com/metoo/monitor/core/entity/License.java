package com.metoo.monitor.core.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;


@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class License {

    @ApiModelProperty("申请码,系统唯一序列号")
    private String systemSN;

    @ApiModelProperty("过期时间")
    private String expireTime;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("结束时间")
    private String endTime;

    @ApiModelProperty("License类型 0：试用版 1，授权版 2：终身版")
    private Integer type;

    @ApiModelProperty("License版本号")
    private Integer licenseVersion;
    // 授权信息
    @ApiModelProperty("授权防火墙")
    private int licenseFwNum;
    @ApiModelProperty("以导入防火墙")
    private int currentFwNum;
    private boolean checkFwResult = false;
    @ApiModelProperty("授权路由/交换")
    private int licenseRouterNum;
    @ApiModelProperty("以导入路由交换")
    private int currentRouterNum;
    private boolean checkRouterResult = false;
    @ApiModelProperty("授权主机数")
    private int licenseHostNum;
    @ApiModelProperty("以导入主机数")
    private int currentHostNum;
    private boolean checkHostResult = false;
    @ApiModelProperty("授权模拟网关")
    private int licenseGatewayNum;
    @ApiModelProperty("以导入模拟网关")
    private int currentGatewayNum;
    private boolean checkGatewayResult = false;
    @ApiModelProperty("已使用")
    private int useDay;
    @ApiModelProperty("未使用")
    private int surplusDay;

    @ApiModelProperty("授权码")
    private String liecnes;

}
