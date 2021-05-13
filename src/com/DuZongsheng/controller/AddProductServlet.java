package com.DuZongsheng.controller;

import com.DuZongsheng.dao.ProductDao;
import com.DuZongsheng.model.Category;
import com.DuZongsheng.model.Product;
import org.omg.CORBA.INTERNAL;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AddProductServlet",value = "/admin/addProduct")
@MultipartConfig(maxFileSize = 16177215)
public class AddProductServlet extends HttpServlet {
    Connection con=null;

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName=request.getParameter("productName");
        Double price=request.getParameter("price")!=null?Double.parseDouble(request.getParameter("price")):0.0;
        int categoryId=request.getParameter("categoryId")!=null?Integer.parseInt(request.getParameter("categoryId")):0;
        String ProductDescription=request.getParameter("productDescription");
        InputStream inputStream=null;
        Part filePart=request.getPart("picture");
        if(filePart!=null){
            System.out.println("file name:"+filePart.getName()+" size"+filePart.getSize()+"file type"+filePart.getContentType());
            inputStream=filePart.getInputStream();
        }
        Product product=new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setCategoryID(categoryId);
        product.setProductDescription(ProductDescription);
        product.setPicture(inputStream);

        ProductDao dao=new ProductDao();
        try {
            int i=dao.save(product,con);
            if(i>0) response.sendRedirect("productList");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList=Category.findAllCategory(con);
        request.setAttribute("categoryList",categoryList);
        request.getRequestDispatcher("/WEB-INF/views/admin/addProduct.jsp").forward(request,response);
    }
}
