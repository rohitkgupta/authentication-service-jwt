package com.authentication.dao;

public interface BlacklistDao {
    boolean isBlacklistToken(String token);
    void addBlacklistToken(String token);
}
