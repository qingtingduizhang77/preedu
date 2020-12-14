package com.tware.common.entity;

import swallow.framework.jpaentity.IOnlyIdEntity;
import swallow.framework.jpaquery.repository.annotations.CnName;


import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;

/**
 * 只有一个ID的实体对象
 * @author aohanhe
 *
 */
@MappedSuperclass
public  class OnlyIdEntity implements IOnlyIdEntity{

    @ApiModelProperty(value="主键ID",name="id",example="")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CnName("主键ID")
    private Long id;
    
    @ApiModelProperty(value="记录版本号",name="version",example="")
    @Version
    @CnName("记录版本号")
    private Long version;


	public Long getId() {
		
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	

	
}
