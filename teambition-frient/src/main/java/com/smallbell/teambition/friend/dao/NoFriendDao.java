package com.smallbell.teambition.friend.dao;

import com.smallbell.teambition.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoFriendDao extends JpaRepository<NoFriend,String> {
    /**
     * 联合双主键查询
     * @param userid
     * @param friendid
     * @return
     */
    public NoFriend findByUseridAndFriendid(String userid, String friendid);

}
