package com.suwadi.web.flash;

import java.io.Serializable;
import java.util.Map;

public interface Flash extends Serializable {
	void info(String messageKey, String... params);

	void error(String messageKey, String... params);

	void success(String messageKey, String... params);

	Map<String, Object> getMessages();

	void reset();
}
