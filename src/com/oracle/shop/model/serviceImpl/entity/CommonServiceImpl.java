package com.oracle.shop.model.serviceImpl.entity;

import java.io.Serializable;
import java.util.List;

import com.oracle.shop.model.entity.Goods;
import com.oracle.shop.model.entity.GoodsType;
import com.oracle.shop.model.entity.ShopUser;
import com.oracle.shop.model.service.ICommonService;

public abstract class CommonServiceImpl<T> implements ICommonService{
	private static final long serialVersionUID = 1L;

	@Override
	public ShopUser findShopUser(String name) {
		return null;
	}
	//谌敦伟插入数据
	@Override
	public int save(Object t) {
		return 0;
	}
	//谌敦伟查询数据
	@Override
	public ShopUser findShopUserT(String tel) {
		return null;
	}

	/**
	 * @author 朱彬
	 * 根据ID查找商品详情页的信息
	 * */
	@Override
	public Goods findGoods(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/**
	 * 朱彬查询订单明细表
	 * */
	@Override
	public List findOrderlist(int status,String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public List findcomm(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int uppass(Object t) {
		// TODO Auto-generated method stub
		return 0;
	}
	//模糊查询
	@Override
	public GoodsType findname(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Goods findtyid(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
