package com.leederedu.educhat.db.mappers;

import java.util.List;

public interface PlateMapper<T> {
	
	public List<T> getList(long custId,String dcode);
}
