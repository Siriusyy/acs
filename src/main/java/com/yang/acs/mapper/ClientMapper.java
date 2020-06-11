package com.yang.acs.mapper;

import com.yang.acs.domain.Client;
import com.yang.acs.domain.ClientLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClientMapper {
    List<Client> getAllClient();
    List<ClientLog> getClientlogByPage(int page);
    Integer getClientLogCount();
}
