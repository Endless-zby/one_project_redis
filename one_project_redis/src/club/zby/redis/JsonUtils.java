package club.zby.redis;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils extends Exception{
	public static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	// 对象->JSON字符串
	public static String objectToJsonStr(Object value) throws Exception {
		return objectMapper.writeValueAsString(value);
	}

	// JSON字符串 ->对象
	public static <T> T jsonStrToObject(String content, Class<T> valueType)
			throws Exception {
		T obj = objectMapper.readValue(content, valueType);
		return obj;
	}
}
