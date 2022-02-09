package com.zkzn.les.gateway.config;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

public class GateWayMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

	GateWayMappingJackson2HttpMessageConverter(){
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"));
        setSupportedMediaTypes(mediaTypes);
    }
}
