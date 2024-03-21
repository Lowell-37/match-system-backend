package com.lowell.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lowell.usercenter.mapper.UserTeamMapper;
import com.lowell.usercenter.model.domain.UserTeam;
import com.lowell.usercenter.service.UserTeamService;
import org.springframework.stereotype.Service;

/**
* @author 86131
* @description 针对表【user_team(用户队伍关系表)】的数据库操作Service实现
* @createDate 2024-03-14 23:12:08
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService {

}




