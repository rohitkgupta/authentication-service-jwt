package com.authentication.dao.impl;

import com.authentication.dao.BlacklistDao;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.TreeSet;

@Component
public class BlacklistDaoImpl implements BlacklistDao {
    private Set<String> list = new TreeSet<>();

    @Override
    public boolean isBlacklistToken(String token) {
        return list.contains(token);
    }

    @Override
    public void addBlacklistToken(String token) {
        list.add(token);
    }
}
