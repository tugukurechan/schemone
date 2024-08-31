package com.example.Schemone.repository;

import com.example.Schemone.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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

    private static final String TABLE_NAME = "users";

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

    /**
     * insert&update文
     *
     * @param user 追加or更新
     * @return 操作したuser情報
     */
    public User save(User user){
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);

        if(user.getId() == null){
            // user追加
            String insertSql = "INSERT INTO "+TABLE_NAME+"(id, name) VALUES(:name);";
            template.update(insertSql,param);
        }else{
            // user更新
            String updateSql = "UPDATE "+ TABLE_NAME+" SET name = :name WHERE id = :id;";
            template.update(updateSql,param);
        }
        return user;
    }

    /**
     * delete文
     *
     * @param id user_id
     */
    public void deleteById(Integer id){
        String deleteSql = "DELETE FROM " + TABLE_NAME +" WHERE id = :id;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
        template.update(deleteSql,param);
    }
}
