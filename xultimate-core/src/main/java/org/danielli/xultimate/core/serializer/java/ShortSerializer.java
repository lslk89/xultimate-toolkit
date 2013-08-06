package org.danielli.xultimate.core.serializer.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.danielli.xultimate.core.serializer.AbstractClassTypeSupportSerializer;
import org.danielli.xultimate.core.serializer.DeserializerException;
import org.danielli.xultimate.core.serializer.SerializerException;
import org.danielli.xultimate.core.serializer.java.util.SerializerUtils;
import org.danielli.xultimate.util.reflect.ClassUtils;

public class ShortSerializer extends AbstractClassTypeSupportSerializer {

	private Boolean packZeros = true;
	
	public void setPackZeros(Boolean packZeros) {
		this.packZeros = packZeros;
	}

	@Override
	public boolean support(Class<?> classType) {
		return ClassUtils.isAssignable(Short.class, classType);
	}
	
	@Override
	public <T> byte[] serialize(T source) throws SerializerException {
		return SerializerUtils.encodeShort((Short) source, packZeros);
	}

	@Override
	public <T> void serialize(T source, OutputStream outputStream) throws SerializerException {
		try {
			outputStream.write(SerializerUtils.encodeShort((Short) source, false));
		} catch (IOException e) {
			throw new SerializerException(e.getMessage(), e);
		}
	}

	@Override
	public <T> T deserialize(byte[] bytes, Class<T> clazz) throws DeserializerException {
		return clazz.cast(SerializerUtils.decodeShort(bytes));
	}

	@Override
	public <T> T deserialize(InputStream inputStream, Class<T> clazz) throws DeserializerException {
		try {
			byte[] result = new byte[SerializerUtils.SHORT_BYTE_SIZE];
			inputStream.read(result);
			return clazz.cast(SerializerUtils.decodeShort(result));
		} catch (IOException e) {
			throw new DeserializerException(e.getMessage(), e);
		}
	}

}
