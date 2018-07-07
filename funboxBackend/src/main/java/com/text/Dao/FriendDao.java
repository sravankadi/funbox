package com.text.Dao;

import java.util.List;

import com.text.model.Friend;
import com.text.model.User;

public interface FriendDao {
List<User>getSuggestedUsers(String email);

void addFriend(Friend friend);
List<Friend> pendingRequests(String email);

void upadateStatus(Friend friendRequest);
List<Friend> getAllFriends(String email);

}