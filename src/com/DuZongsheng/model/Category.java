package com.DuZongsheng.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private int categoryId;
    private String categoryName;
    private String description;
    private Boolean active;

    public Category() {}

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(String categoryName, String description, Boolean active) {
        this.categoryName = categoryName;
        this.description = description;
        this.active = active;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }

    public static List<Category> findAllCategory(Connection con) {
        List<Category> List = new ArrayList<Category>();
        String queryString = "select * from Category";
        try {
            PreparedStatement statement = con.prepareStatement(queryString);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category c = new Category();
                c.setCategoryId(resultSet.getInt("CategoryID"));
                c.setCategoryName(resultSet.getString("categoryName"));
                c.setDescription(resultSet.getString("Description"));
                List.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return List;
    }

    public static String findByCategoryId(int categoryId,Connection con) throws SQLException {
        String categoryName=null;
        String sql = "select CategoryName from Category where CategoryID=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, categoryId);
        ResultSet rs = ps.executeQuery();
        Category c = new Category();
        if (rs.next()) {
            categoryName=rs.getString("categoryName");
        }
        return categoryName;
    }
}
