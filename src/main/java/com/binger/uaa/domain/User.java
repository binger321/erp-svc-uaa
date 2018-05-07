package com.binger.uaa.domain;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户表
 */
@ApiModel(value="用户表")
public class User implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(value="ID",required = true)
    private Integer id;

    /**
     * 用户代码
     */
    @ApiModelProperty(value="用户代码",required = true)
    private String userCode;

    /**
     * 用户名称
     */
    @ApiModelProperty(value="用户名称",required = true)
    private String userName;

    /**
     * 用户密码
     */
    @ApiModelProperty(value="用户密码",required = true)
    private String userPassword;

    /**
     * 密码错误累计次数(解锁前)
     */
    @ApiModelProperty(value="密码错误累计次数(解锁前)",required = false)
    private Integer pwdTryTimes;

    /**
     * 人员表id
     */
    @ApiModelProperty(value="人员表id",required = false)
    private Integer personId;

    /**
     * 0禁用, 1启用, 2锁定
     */
    @ApiModelProperty(value="0禁用, 1启用, 2锁定",required = true)
    private Integer status;

    /**
     * 停用时间
     */
    @ApiModelProperty(value="停用时间",required = false)
    private Date blockTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人",required = false)
    private String creator;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",required = false)
    private Date createTime;

    /**
     * 修改人
     */
    @ApiModelProperty(value="修改人",required = false)
    private String modifier;

    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间",required = false)
    private Date modifyTime;

    /**
     * user
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * @return id ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 用户代码
     * @return user_code 用户代码
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 用户代码
     * @param userCode 用户代码
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * 用户名称
     * @return user_name 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名称
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 用户密码
     * @return user_password 用户密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 用户密码
     * @param userPassword 用户密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    /**
     * 密码错误累计次数(解锁前)
     * @return pwd_try_times 密码错误累计次数(解锁前)
     */
    public Integer getPwdTryTimes() {
        return pwdTryTimes;
    }

    /**
     * 密码错误累计次数(解锁前)
     * @param pwdTryTimes 密码错误累计次数(解锁前)
     */
    public void setPwdTryTimes(Integer pwdTryTimes) {
        this.pwdTryTimes = pwdTryTimes;
    }

    /**
     * 人员表id
     * @return person_id 人员表id
     */
    public Integer getPersonId() {
        return personId;
    }

    /**
     * 人员表id
     * @param personId 人员表id
     */
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    /**
     * 0禁用, 1启用, 2锁定
     * @return status 0禁用, 1启用, 2锁定
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 0禁用, 1启用, 2锁定
     * @param status 0禁用, 1启用, 2锁定
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 停用时间
     * @return block_time 停用时间
     */
    public Date getBlockTime() {
        return blockTime;
    }

    /**
     * 停用时间
     * @param blockTime 停用时间
     */
    public void setBlockTime(Date blockTime) {
        this.blockTime = blockTime;
    }

    /**
     * 创建人
     * @return creator 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人
     * @return modifier 修改人
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人
     * @param modifier 修改人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 修改时间
     * @return modify_time 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserPassword() == null ? other.getUserPassword() == null : this.getUserPassword().equals(other.getUserPassword()))
            && (this.getPwdTryTimes() == null ? other.getPwdTryTimes() == null : this.getPwdTryTimes().equals(other.getPwdTryTimes()))
            && (this.getPersonId() == null ? other.getPersonId() == null : this.getPersonId().equals(other.getPersonId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getBlockTime() == null ? other.getBlockTime() == null : this.getBlockTime().equals(other.getBlockTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserPassword() == null) ? 0 : getUserPassword().hashCode());
        result = prime * result + ((getPwdTryTimes() == null) ? 0 : getPwdTryTimes().hashCode());
        result = prime * result + ((getPersonId() == null) ? 0 : getPersonId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getBlockTime() == null) ? 0 : getBlockTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userCode=").append(userCode);
        sb.append(", userName=").append(userName);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", pwdTryTimes=").append(pwdTryTimes);
        sb.append(", personId=").append(personId);
        sb.append(", status=").append(status);
        sb.append(", blockTime=").append(blockTime);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}