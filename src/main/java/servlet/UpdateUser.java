//--------------------------------
//	RegistInfo.java
//--------------------------------
//　自分が格納されているフォルダ名
package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import control.UserManager;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/UpdateUser")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class UpdateUser extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UpdateUser:Get");
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // セッションの作成・取得
        HttpSession session = request.getSession();
        User user = new User();
        user = (User) session.getAttribute("User");

        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Users/updateUser.jsp");
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UpdateUser:Post");

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // ➀セッションの作成・取得
        HttpSession session = request.getSession();

        User userU = new User();

        userU = (User) session.getAttribute("User");

        String id = userU.getId();
        String password = userU.getPassword();

        // requestオブジェクトから登録情報の取り出し
        String passwordP = request.getParameter("passwordP");
        String passwordU = request.getParameter("passwordU");
        String passwordU2 = request.getParameter("passwordU2");

        // コンソールに確認するために出力
        System.out.println("(UpdateUser)取得した文字列は" + passwordP + "です！");
        System.out.println("(UpdateUser)取得した文字列は" + passwordU + "です！");
        System.out.println("(UpdateUser)取得した文字列は" + passwordU2 + "です！");

        //
        UserManager manager = new UserManager();

        String tourl = null;// 遷移先

        // パスワードが半角英数字を含み、８～１５文字であるかどうか
        if (checkPass(passwordU) == false) {
            tourl = "WEB-INF/Users/updateUserPassOK.jsp";
            // RequestDispatcher dispatcher =
            // request.getRequestDispatcher("WEB-INF/Users/updateUserPassOK.jsp");
            // dispatcher.forward(request, response);
        } else {
            // 現在のパスワード確認
            try {
                if (manager.checkPassword(id, passwordP) == false) {
                    tourl = "WEB-INF/Users/updateUserPassNot.jsp";
                    // RequestDispatcher dispatcher =
                    // request.getRequestDispatcher("WEB-INF/Users/updateUserPassNot.jsp");
                    // dispatcher.forward(request, response);
                } else {
                    // パスワード変更
                    if (passwordU.equals(passwordU2)) {
                        // 確認画面を表示する
                        // response.sendRedirect("/StuInfo/RegistInfo");
                        tourl = "WEB-INF/Users/updateUserSuccess.jsp";
                        // RequestDispatcher dispatcher = request
                        // .getRequestDispatcher("WEB-INF/Users/updateUserSuccess.jsp");

                        // DBに更新内容を反映させる
                        // try {
                        manager.updatePassword(id, passwordU);
                        // } catch (NoSuchAlgorithmException e) {
                        // // TODO Auto-generated catch block
                        // e.printStackTrace();
                        // }

                        User user = new User(id, passwordU, passwordU2);
                        request.setAttribute("User", user);
                        // dispatcher.forward(request, response);
                    } else {
                        // response.sendRedirect("/se21g1/RegistUser");
                        tourl = "WEB-INF/Users/updateUserPass.jsp";
                        // RequestDispatcher dispatcher =
                        // request.getRequestDispatcher("WEB-INF/Users/updateUserPass.jsp");
                        // dispatcher.forward(request, response);
                    }
                }
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(tourl);
        dispatcher.forward(request, response);
    }

    public boolean checkID(String id) {
        int len = id.length();
        if (len < 6) {
            System.out.println("IDが短い");
            return false;
        }
        return true;
    }

    public boolean checkPass(String pass) {
        char[] c = pass.toCharArray();
        int len = pass.length();
        // boolean s = false;
        // boolean l = false;
        boolean e = false;
        boolean n = false;
        boolean ok = false;

        if (len < 8) {
            return false;
        }

        for (int i = 0; i < len; i = i + 1) {
            int c2 = c[i];
            if (c2 >= 97 && c2 <= 122) {
                e = true;
                break;
            }
        }
        for (int i = 0; i < len; i = i + 1) {
            int c2 = c[i];
            if (c2 >= 65 && c2 <= 90) {
                e = true;
                break;
            }
        }
        for (int i = 0; i < len; i = i + 1) {
            int c2 = c[i];
            if (c2 >= 48 && c2 <= 57) {
                n = true;
                break;
            }
        }
        if (e == true && n == true) {
            System.out.println("このパスワードは使ってよし");
            ok = true;
        }

        return ok;
    }
}
