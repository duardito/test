package org.com;

import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BaseController {

    @RequestMapping( value = "/test",method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getName(@RequestBody Pepe map) {

        System.out.println(map);

        //System.out.println(map);
        return "ok";

    }

    public  <K, V> Map<K, Collection<V>> toMap(MultiValueMap<K, V> map) {

        Assert.notNull(map, "Given map must not be null!");
        Map<K, Collection<V>> result = new LinkedHashMap<K, Collection<V>>(map.size());

        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

}
