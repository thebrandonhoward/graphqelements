package com.thebrandonhoward.graphqelements.domain.models.user;

import lombok.Data;

import java.net.URL;

@Data
public class UserProfile {
    public String profileId;
    public URL imageUrl;
    public User user;
}
