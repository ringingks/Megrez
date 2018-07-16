package com.Y3.AnalyticsTeam.CT.provider.system.BO;

import com.Y3.AnalyticsTeam.CT.provider.system.DTO.UserDTO;

import java.util.List;

public interface ICaUserBO {

    List<UserDTO> findUserOnPage(int page, int limit);

    int count(int status);
}
