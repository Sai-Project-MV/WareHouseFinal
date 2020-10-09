package com.mangesh.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ListToMapConfig {

	public static Map<Integer, String> generateListToMap(List<Object[]> list) {
		// JDK 1.8
		Map<Integer, String> map = list.stream()
				.collect(Collectors.toMap(ob -> Integer.valueOf(ob[0].toString()), ob -> ob[1].toString()));
		return map;
	}
}
