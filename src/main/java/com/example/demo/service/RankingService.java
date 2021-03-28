package com.example.demo.service;

import com.example.demo.entity.Ranking;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {

    private final JdbcTemplate jdbcTemplate;

    public RankingService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Ranking> findTotalRankings() {
        var query = "select row_number() over () ranking, u.username, sum(m.mark) as total\n" +
                "from lms.mark m\n" +
                "         join lms.user u on m.student_id = u.id\n" +
                "group by student_id\n" +
                "order by total desc ";
        RowMapper<Ranking> rankingRowMapper = (rs, i) -> new Ranking(
                rs.getLong("ranking"),
                rs.getString("username"),
                rs.getInt("total")
        );
        return this.jdbcTemplate.query(query, rankingRowMapper);
    }
}
