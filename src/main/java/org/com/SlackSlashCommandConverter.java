package org.com;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Map;

public class SlackSlashCommandConverter  extends AbstractHttpMessageConverter<Pepe>{

    private static final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
    private static final ObjectMapper mapper = new ObjectMapper();


    @Override
    protected boolean supports(Class<?> aClass) {
        return (Pepe.class == aClass);
    }

    @Override
    protected Pepe readInternal(Class<? extends Pepe> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Map<String, String> vals = formHttpMessageConverter.read(null, httpInputMessage).toSingleValueMap();
        return mapper.convertValue(vals, Pepe.class);
    }

    @Override
    protected void writeInternal(Pepe pepe, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
