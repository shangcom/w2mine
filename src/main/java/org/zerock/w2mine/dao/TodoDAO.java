package org.zerock.w2mine.dao;


import lombok.Cleanup;
import org.zerock.w2mine.domain.TodoVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    public String getTime() {
        String now = null;

        /*
        try-with-resources 구문을 사용하면, 괄호 안에 선언된 모든 자원(AutoCloseable 인터페이스를 구현하는 객체)은 try 블록을 벗어날 때 자동으로 닫힘.
        이는 Connection, PreparedStatement, ResultSet과 같은 JDBC 자원을 포함하며, 이들은 모두 AutoCloseable 인터페이스를 구현하고 있어 해당 방식으로 자동 관리됨.*/

        try (Connection connection = ConnectionUtil.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select now()");
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            resultSet.next();
            now = resultSet.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    public String getTime2() throws Exception {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select now()");
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        String now = resultSet.getString(1);

        return now;
    }

    public void insert(TodoVO todoVO) throws Exception {
        String sql = "insert into tbl_todo (title, dueDate, finished) values(?, ?, ?)";

        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();

        @Cleanup
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, todoVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
        preparedStatement.setBoolean(3, todoVO.isFinished());

        preparedStatement.executeUpdate();
    }

    public List<TodoVO> selectAll() throws Exception {

        String sql = "Select * from tbl_todo";
        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();

        @Cleanup
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        @Cleanup
        ResultSet resultSet = preparedStatement.executeQuery();

        List<TodoVO> list = new ArrayList<>();

        while (resultSet.next()) {
            TodoVO vo = TodoVO.builder().tno(resultSet.getLong("tno")).title(resultSet.getString("title")).dueDate(resultSet.getDate("dueDate").toLocalDate()).finished(resultSet.getBoolean("finished")).build();
            list.add(vo);
        }
        return list;
    }


    public TodoVO selectOne(Long tno) throws Exception {

        String sql = "select * from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        TodoVO vo = TodoVO.builder()
                .tno(resultSet.getLong("tno"))
                .title(resultSet.getString("title"))
                .dueDate(resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();

        return vo;
    }

    public void deleteOne(Long tno) throws Exception {
        String sql = "Delete from tbl_todo where tno=?";

        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();

        @Cleanup
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);

        preparedStatement.executeUpdate();

    }

    public void updateOne(TodoVO todoVO) throws Exception {

        String sql = "update tbl_todo set title=?, dueDate = ?, finished=? where tno=?";
        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();

        @Cleanup
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, todoVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
        preparedStatement.setBoolean(3, todoVO.isFinished());
        preparedStatement.setLong(4, todoVO.getTno());
        preparedStatement.executeUpdate();
    }
}
