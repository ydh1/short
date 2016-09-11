package com.leederedu.educhat.db.mappers;

import java.util.List;

public interface CommendMapper<T>{
	
	public List<T> getBanners(long custId,int plateCode);
	
	public List<T> getCommends();
}
