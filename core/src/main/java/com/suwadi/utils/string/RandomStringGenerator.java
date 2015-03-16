package com.suwadi.utils.string;

import org.apache.commons.lang.RandomStringUtils;

public class RandomStringGenerator {
	private final static char[] RANDOM_CHARS = { 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9' };

	public String getRandomSalt() {
		String s = RandomStringUtils.randomAlphanumeric(20);

		return s;
	}
}
