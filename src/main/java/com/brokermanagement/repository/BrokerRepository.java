package com.brokermanagement.repository;

import com.brokermanagement.model.Broker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import static java.lang.String.format;

@Repository
public class BrokerRepository {

    private JdbcTemplate template;

    @Autowired
    public BrokerRepository(JdbcTemplate template) {
        this.template = template;
    }

    public Broker findById(Integer id) {
        return template.query("Select id,manager,name FROM BROKER WHERE id=" + id, getBrokerByResultSet());
    }

    private ResultSetExtractor<Broker> getBrokerByResultSet() {
        return rs -> {
            rs.next();
            return Broker.builder()
                    .id(rs.getInt(1))
                    .manager(rs.getString(2))
                    .name(rs.getString(3))
                    .build();
        };
    }

    public Broker save(Broker broker) {
        template.execute(format("INSERT INTO BROKER (ID,NAME,MANAGER) VALUES(nextval('broker_id_seq'),'%s','%s')",
                broker.getName(),
                broker.getManager()));
        return template.query("SELECT ID,NAME,MANAGER FROM BROKER ORDER BY ID DESC", getBrokerByResultSet());
    }
}
