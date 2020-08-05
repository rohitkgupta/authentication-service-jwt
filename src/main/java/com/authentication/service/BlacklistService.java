package com.authentication.service;

import com.authentication.dao.BlacklistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlacklistService {

    @Autowired
    private BlacklistDao blacklistDao;

    public boolean isBlacklistToken(String token) {
        return blacklistDao.isBlacklistToken(token);
    }

    public void addBlacklistToken(String token) {
        blacklistDao.addBlacklistToken(token);
    }

}
