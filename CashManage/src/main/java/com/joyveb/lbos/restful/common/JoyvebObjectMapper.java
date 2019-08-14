package com.joyveb.lbos.restful.common;

import java.io.IOException;
import java.io.OutputStream;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.DeserializationConfig.Feature;

public class JoyvebObjectMapper extends ObjectMapper {

	public static class NullSerializer extends JsonSerializer<Object> {
		public void serialize(Object value, JsonGenerator jgen,
				SerializerProvider provider) throws IOException,
				JsonProcessingException {
			// any JSON value you want...
			jgen.writeString("");
		}
	}

	public JoyvebObjectMapper() {
		super();
		getSerializerProvider().setNullValueSerializer(new NullSerializer());
		configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
	}

	@Override
	public void writeValue(JsonGenerator jgen, Object value)
			throws IOException, JsonGenerationException, JsonMappingException {
		super.writeValue(jgen, value);
	}

	@Override
	public void writeValue(OutputStream out, Object value) throws IOException,
			JsonGenerationException, JsonMappingException {
		super.writeValue(out, value);
	}

	public JoyvebObjectMapper(JsonFactory jf, SerializerProvider sp,
			DeserializerProvider dp, SerializationConfig sconfig,
			DeserializationConfig dconfig) {
		super(jf, sp, dp, sconfig, dconfig);
	}

	public JoyvebObjectMapper(JsonFactory jf, SerializerProvider sp,
			DeserializerProvider dp) {
		super(jf, sp, dp);
	}

	public JoyvebObjectMapper(JsonFactory jf) {
		super(jf);
	}

}
