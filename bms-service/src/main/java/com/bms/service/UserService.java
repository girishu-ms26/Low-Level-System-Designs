package com.bms.service;

import com.bms.model.User;
import com.bms.provider.DataProvider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class UserService {

    public Set<User> userSet;
    private final DataProvider provider;

    public UserService(DataProvider provider) {
        this.provider = provider;
        this.userSet = this.provider.userSet;
    }

    public User getUserWithId(int id) throws Exception {
        for(Iterator<User> it = userSet.iterator(); it.hasNext();) {
            User user = it.next();
            if(user.getId() == id) {
                return user;
            }
        }
        throw new Exception("User not found");
    }
}
