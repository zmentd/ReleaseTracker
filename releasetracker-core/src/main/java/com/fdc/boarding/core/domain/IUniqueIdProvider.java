package com.fdc.boarding.core.domain;

public interface IUniqueIdProvider<T extends Object> {
	public T getUniqueId();
}

