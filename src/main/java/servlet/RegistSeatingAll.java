package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import beans.ClassDef; //beansに入れた方がいいのかしら
import beans.Student;
import beans.User;
import service.StudentService;
import service.ClassService;
import service.SeatingService;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utility.*;
import beans.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistSeatingAll")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistSeatingAll extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        System.out.println("いまdoGet");
        HttpSession session = request.getSession();

        // // 「生徒座席一覧(studentSeatingArrList)」に座らせた生徒と座席の情報を入れる
        // List<StudentSeatingArr> studentSeatingArrList = new
        // ArrayList<StudentSeatingArr>();
        // if ((List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList")
        // != null) {
        // studentSeatingArrList = (List<StudentSeatingArr>)
        // session.getAttribute("StudentSeatingArrList");
        // }

        // // SeatingServiceオブジェクトの生成
        // SeatingService seatingService = new SeatingService();
        // // 座席を登録
        // seatingService.registStudentSeatingArr(studentSeatingArrList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/registSeatingInfo.jsp");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        System.out.println("いまPost");

        // // 入力された座席情報を取得
        // String seatname = request.getParameter("seatname");
        // String startdate = request.getParameter("startdate");
        // String enddate = request.getParameter("enddate");
        // HttpSession session = request.getSession();
        // User user = (User) session.getAttribute("User");
        // ClassDef classdef = (ClassDef) session.getAttribute("ClassDef");
        // Date createdDate = new Date();// 現在時刻＝作成日
        // String createddate = new SimpleDateFormat("yyyy-MM-dd").format(createdDate);
        // System.out.println(seatname + ":" + startdate + ":" + enddate + ":" +
        // classdef.getClass_id() + ":"
        // + user.getId() + ":" + createddate);

        // // registSeatingArrangementsオブジェクトに座席情報を入れる
        // SeatingArrangements seatingArrangements = new SeatingArrangements();
        // seatingArrangements.setClassId(classdef.getClass_id());
        // seatingArrangements.setName(seatname);
        // seatingArrangements.setStartDate(startdate);
        // seatingArrangements.setEndDate(enddate);
        // seatingArrangements.setCreatedDate(createddate);
        // // seatingArrangements.setUserId(user.getId());

        // System.out.println(seatingArrangements.getName() + ":" +
        // seatingArrangements.getStartDate() + ":"
        // + seatingArrangements.getEndDate() + seatingArrangements.getClassId() + ":"
        // + seatingArrangements.getUserId() + ":" +
        // seatingArrangements.getCreatedDate());

        HttpSession session = request.getSession();

        // 座席配置情報をセッションから取得
        SeatingArrangements setseatingArrangements = new SeatingArrangements();
        setseatingArrangements = (SeatingArrangements) session.getAttribute("SeatingArrangements");
        // SeatingServiceオブジェクトの生成
        SeatingService seatingService = new SeatingService();
        // 座席配置情報を登録・取得
        setseatingArrangements = seatingService.registSeatingArrangements(setseatingArrangements);
        System.out.println("座席配置情報登録完了");

        // 「生徒座席一覧(studentSeatingArrList)」の情報を取得
        List<StudentSeatingArr> studentSeatingArrList = new ArrayList<StudentSeatingArr>();
        if ((List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList") != null) {
            studentSeatingArrList = (List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList");
        }
        // 座席配置IDをすべての座席に登録
        for (int i = 0; i < studentSeatingArrList.size(); i++) {
            studentSeatingArrList.get(i).setSeatingArrangementId(setseatingArrangements.getId());
        }
        // 座席を登録
        seatingService.registStudentSeatingArr(studentSeatingArrList);
        System.out.println("座席（生徒）登録完了");

        // // request,sessionで座席配置情報を送る
        // request.setAttribute("SeatingArrangements", seatingArrangements);
        // session.setAttribute("SeatingArrangements", seatingArrangements);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/registSeatingcomplete.jsp");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        dispatcher.forward(request, response);
    }

}