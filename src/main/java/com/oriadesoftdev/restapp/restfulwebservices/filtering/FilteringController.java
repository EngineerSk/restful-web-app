package com.oriadesoftdev.restapp.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filterValue() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter beanPropertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("field1", "field3");
        mappingJacksonValue.setFilters(new SimpleFilterProvider()
                .addFilter("SimpleBeanFilter", beanPropertyFilter));
        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filterListValue() {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(
                Arrays.asList(new SomeBean("value1", "value2", "value3"),
                        new SomeBean("value4", "value5", "value6")));
        mappingJacksonValue
                .setFilters(
                        new SimpleFilterProvider()
                                .addFilter("SimpleBeanFilter",
                                        SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3")
                                )
                );
        return mappingJacksonValue;
    }
}
