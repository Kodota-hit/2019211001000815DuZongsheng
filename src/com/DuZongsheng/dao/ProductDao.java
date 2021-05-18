package com.DuZongsheng.dao;

import com.DuZongsheng.model.Product;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements  IProductDao {
    @Override
    public int save(Product product, Connection con) throws SQLException {
        int n = 0;
        String sql = "insert into product(ProductName,ProductDescription,Picture,price,CategoryID) values(?,?,?,?,?)";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, product.getProductName());
        pt.setString(2, product.getProductDescription());
        if (product.getPicture() != null) {
            //for sql server
            //pt.setBinaryStream(3, product.getPicture());
            //for mysql
            pt.setBlob(3, product.getPicture());
        }
        pt.setDouble(4, product.getPrice());
        pt.setInt(5, product.getCategoryID());
        n = pt.executeUpdate();
        if (n > 0) {
            return n;
        }
        return 0;
    }//end save

    @Override
    public int delete(Integer productId, Connection con) throws SQLException {
        String sql = "DELETE FROM Product WHERE ProductID=?;";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, productId);
        int rs = pt.executeUpdate();
        return rs;
    }

    @Override
    public int update(Product instance, Connection con) throws SQLException {
        int n = 0;
        String sql = "UPDATE Product SET ProductName=?,ProductDescription=?,Picture=?,price=?,CategoryID=? WHERE ProductID=?;";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, instance.getProductName());
        pt.setString(2, instance.getProductDescription());
        if (instance.getPicture() != null) {
            pt.setBlob(3, instance.getPicture());
        }
        pt.setDouble(4, instance.getPrice());
        pt.setInt(5, instance.getCategoryID());
        pt.setInt(6, instance.getProductID());
        n = pt.executeUpdate();
        if (n > 0) {
            return n;
        }
        return 0;
    }

    @Override
    public Product findById(Integer productId, Connection con) throws SQLException {
        String sql = "select * from Product where ProductID=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();
        Product product = new Product();
        if (rs.next()) {
            product.setProductID(rs.getInt("ProductID"));
            product.setProductName(rs.getString("ProductName"));
            product.setProductDescription(rs.getString("ProductDescription"));
            //product.setPicture((InputStream) rs.getBlob("Picture"));
            product.setPrice(rs.getDouble("Price"));
            product.setCategoryID(rs.getInt("CategoryID"));
        }
        return product;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, Connection con) throws SQLException {
        List<Product> productList = new ArrayList<Product>();
        String sql = "select * from Product where CategoryID=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, categoryId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("ProductID"));
            product.setProductName(rs.getString("ProductName"));
            product.setProductDescription(rs.getString("ProductDescription"));
            //product.setPicture((InputStream) rs.getBlob("Picture"));
            product.setPrice(rs.getDouble("Price"));
            product.setCategoryID(rs.getInt("CategoryID"));
            productList.add(product);
        }
        return productList;
    }

    @Override
    public List<Product> findByPrice(double minPrice, double maxPrice, Connection con) throws SQLException {
        List<Product> productList = new ArrayList<Product>();
        String sql = "select * from Product where Price between ? and ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, minPrice);
        ps.setDouble(2, maxPrice);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("ProductID"));
            product.setProductName(rs.getString("ProductName"));
            product.setProductDescription(rs.getString("ProductDescription"));
            product.setPicture((InputStream) rs.getBlob("Picture"));
            product.setPrice(rs.getDouble("Price"));
            product.setCategoryID(rs.getInt("CategoryID"));
            productList.add(product);
        }
        return productList;
    }

    @Override
    public List<Product> findAll(Connection con) throws SQLException {
        List<Product> productList = new ArrayList<Product>();
        String sql = "select * from Product";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("ProductID"));
            product.setProductName(rs.getString("ProductName"));
            product.setProductDescription(rs.getString("ProductDescription"));
            //product.setPicture((InputStream) rs.getBlob("Picture"));
            product.setPrice(rs.getDouble("Price"));
            product.setCategoryID(rs.getInt("CategoryID"));
            productList.add(product);
        }
        return productList;
    }

    @Override
    public List<Product> findByProductName(String productName, Connection con) throws SQLException {
        List<Product> productList = new ArrayList<Product>();
        String sql = "select * from Product where ProductName=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, productName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("ProductID"));
            product.setProductName(rs.getString("ProductName"));
            product.setProductDescription(rs.getString("ProductDescription"));
            product.setPicture((InputStream) rs.getBlob("Picture"));
            product.setPrice(rs.getDouble("Price"));
            product.setCategoryID(rs.getInt("CategoryID"));
            productList.add(product);
        }
        return productList;
    }

    @Override
    public List<Product> getPicture(Integer productId, Connection con) throws SQLException {
        List<Product> productList = new ArrayList<Product>();
        String sql = "select * from Product where ProductID=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            if (rs.getBlob("Picture") != null)
                product.setPicture((InputStream) rs.getBlob("Picture"));
            productList.add(product);
        }
        return productList;
    }


    public byte[] getPictureById(int productId, Connection con) throws SQLException {
        byte[] imgByte = null;
        String sql = "select picture from product where productID=?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, productId);
        ResultSet rs = pt.executeQuery();
        while (rs.next()) {
            Blob blob = rs.getBlob("picture");
            imgByte = blob.getBytes(1, (int) blob.length());
        }
        return imgByte;
    }
}