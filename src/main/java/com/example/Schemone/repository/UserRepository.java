package com.example.Schemone.repository;

import com.example.Schemone.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * userを操作するリポジトリクラス.
 *
 * @author tuguk
 */
@Repository
public class UserRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<User> USER_ROW_MAPPER
            = new BeanPropertyRowMapper<>(User.class);

    private static final String TABLE_NAME = "user";

    /**
     * ユーザ情報（1件取得）
     *
     * @param id user_id
     * @return ユーザ情報
     */
    public User load(Integer id) {
        String sql = "SELECT id, name FROM " + TABLE_NAME + " WHERE id = :id;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, param, USER_ROW_MAPPER);
    }

    /**
     * ユーザ情報全件取得
     *
     * @return 全ユーザ情報
     */
    public List<User> findAll() {
        String sql = "SELECT id, name FROM" + TABLE_NAME + ";";
        return template.query(sql, USER_ROW_MAPPER);
    }
}
