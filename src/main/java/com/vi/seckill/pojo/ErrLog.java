package com.vi.seckill.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-03-06
 */
@Getter
@Setter
@TableName("sys_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "ErrLog对象", description = "")
public class ErrLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("日志ID")
    @TableField("uuid")
    private String uuid;

    @ApiModelProperty("操作用户ID")
    @TableField("usr_id")
    private String usrId;

    @ApiModelProperty("请求URI")
    @TableField("uri")
    private String uri;

    @ApiModelProperty("ip地址")
    @TableField("ip")
    private String ip;

    @ApiModelProperty("返回码")
    @TableField("code")
    private String code;

    @ApiModelProperty("处理时间")
    @TableField("exc_time")
    private Long excTime;

    @ApiModelProperty("创建时间")
    @TableField("op_time")
    private Date opTime;

    @ApiModelProperty("异常")
    @TableField("exp")
    private String exp;

    @ApiModelProperty("类名.方法名")
    @TableField("class_method")
    private String classMethod;


}
