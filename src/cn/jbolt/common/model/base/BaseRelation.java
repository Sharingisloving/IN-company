package cn.jbolt.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseRelation<M extends BaseRelation<M>> extends Model<M> implements IBean {
	public M setid1(java.lang.String id1) {
		set("id1", id1);
		return (M)this;
	}
	public java.lang.String get﻿id1() {
		return getStr("﻿id1");
	}

	public M setId2(java.lang.String id2) {
		set("id2", id2);
		return (M)this;
	}
	
	public java.lang.String getId2() {
		return getStr("id2");
	}

	public M setWeight(java.lang.String weight) {
		set("weight", weight);
		return (M)this;
	}
	
	public java.lang.String getWeight() {
		return getStr("weight");
	}

}
