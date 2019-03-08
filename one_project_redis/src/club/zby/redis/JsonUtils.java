package club.zby.redis;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils extends Exception{
	public static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	// ����->JSON�ַ���
	public static String objectToJsonStr(Object value) throws Exception {
		return objectMapper.writeValueAsString(value);
	}

	// JSON�ַ��� ->����
	public static <T> T jsonStrToObject(String content, Class<T> valueType)
			throws Exception {
		T obj = objectMapper.readValue(content, valueType);
		return obj;
	}
}
