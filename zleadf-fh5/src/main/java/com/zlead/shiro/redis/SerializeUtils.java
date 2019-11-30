package com.zlead.shiro.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hejie on 2016/12/29.
 */
public class SerializeUtils {

	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 */
	public static Object deserialize(byte[] bytes) {
        if (isEmpty(bytes)) {
            return null;
        }

        return SerializationUtils.deserialize(bytes);
	}

	public static boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}

	/**
	 * 序列化
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Serializable object) {
        if (object == null) {
            return new byte[0];
        }
        return SerializationUtils.serialize(object);
	}
}
