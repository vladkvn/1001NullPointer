package com.springapp.mvc;

import org.springframework.stereotype.Component;

/**
 * Created by vladkvn on 12.11.2016.
 */
@Component
public class SQLConnect implements Connect
{
    @Override
    public boolean add(User user) {
        return false;
    }
}
