package com.lab3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(name = "CalServlet",value = "/CalServlet")
public class CalServlet extends HttpServlet {
    //todo 1: create a method to add(int firstVal, int secondVal) two number
    private int add(int firstValue,int secondValue) {
        return firstValue+secondValue;
    }
    //todo 2: create a method to subtract two number
    private int sub(int firstValue,int secondValue) {
        return firstValue-secondValue;
    }
    //todo 3: create a method to multiply two number
    private int mul(int firstValue,int secondValue) {
        return firstValue*secondValue;
    }
    //todo 4: create a method to divide two number
    private int div(int firstValue,int secondValue) {
        return firstValue/secondValue;
    }
    //todo 5: create a method to computeLength of a string
    private int calLen(String name) {
        return name.length();
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
    public  static boolean isWord(String str) {
        boolean isWords=str.matches("[a-zA-Z]+");
        return isWords;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //todo 6: get all request parameters- firstValue , secondValue,name,action
        String firstValue=request.getParameter("firstValue");
        String secondValue=request.getParameter("secondValue");
        String name=request.getParameter("name");

        String addBtn=request.getParameter("add");
        String subBtn=request.getParameter("sub");
        String mulBtn=request.getParameter("mul");
        String divBtn=request.getParameter("div");
        String lenBtn=request.getParameter("calLen");


        boolean isAdd=false,isSub=false,isMul=false,isDiv=false,isLen=false,isVali=false;
        int result=0,length=0;

        if(addBtn!=null || subBtn!=null || mulBtn!=null || divBtn!=null) {
            if(firstValue.equals("") || !isInteger(firstValue) ) {
                request.setAttribute("Efir", "First value is not number");
                request.getRequestDispatcher("lab3/cal.jsp").forward(request,response);
            }
            else if(secondValue.equals("") || !isInteger(secondValue)) {
                request.setAttribute("Esec", "Second value is not number");
                request.getRequestDispatcher("lab3/cal.jsp").forward(request,response);
            }
            else isVali=true;
        }

        if(isVali) {
            int a = Integer.parseInt(firstValue), b = Integer.parseInt(secondValue);
            //todo 7: use if else to determine action is add or subtract or multiply or divide or computerLength
            if (addBtn != null) {
                //todo 8 : call method add, subtract , multiply, divide or computeLength based on action and get the calculated result
                isAdd = true;
                result = add(a, b);
            } else if (subBtn != null) {
                isSub = true;
                result = sub(a, b);
            } else if (mulBtn != null) {
                isMul = true;
                result = mul(a, b);
            } else if (divBtn != null) {
                isDiv = true;
                if (b == 0) {
                    request.setAttribute("Ediv", "second value can't be 0");
                    request.getRequestDispatcher("lab3/cal.jsp").forward(request,response);
                }
                else result = div(a, b);
            }

            //todo 9: if action =add or subtract or multiply or divide
            if (isAdd || isSub || isMul || isDiv) {
                //todo 10 :create 3 cookie name cFirstValue, cSecondValue,cResult and set the value of cookie
                Cookie cFirstValue = new Cookie("cFirstValue", firstValue);
                Cookie cSecondValue = new Cookie("cSecondValue", secondValue);
                Cookie cResult = new Cookie("cResult", ""+result);

                cFirstValue.setMaxAge(500);
                cSecondValue.setMaxAge(500);
                cResult.setMaxAge(500);

                //todo 11 : add 3 cookies into response
                response.addCookie(cFirstValue);
                response.addCookie(cSecondValue);
                response.addCookie(cResult);
            }
        }

        if (lenBtn != null) {
            isLen = true;
            if(isWord(name))
                length = calLen(name);
            else {
                request.setAttribute("Ename","Name is no valid");
                request.getRequestDispatcher("lab3/cal.jsp").forward(request,response);
            }
        }
//          todo 12: if action =computeLength
            if (isLen) {
//          todo 13 :create 2 cookies name cName, cLength and set the value
                Cookie cName = new Cookie("cName", name);
                Cookie cLength = new Cookie("cLength", ""+length);

                cName.setMaxAge(500);
                cLength.setMaxAge(500);
//          todo 14 : add 2 cookies into response
                response.addCookie(cName);
                response.addCookie(cLength);
            }
//      todo 15 : send redirect to cal.jsp
        response.sendRedirect("CalServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("lab3/cal.jsp").forward(request,response);
    }
}
