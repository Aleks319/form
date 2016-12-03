package ua.kiev.prog.form;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class FormServlet extends HttpServlet {

    static final String RESP1 = "<!DOCTYPE html>" +
            "<html>\n" +
            "  <head>\n" +
            "<meta content=\"text/html\"; charset=\"utf-8\">" +
            "    <title>form.com.ua</title>\n" +
            "  </head>\n" +
            "  <body bgcolor=\"#CCCCFF\">\n" +
            "  <div align=\"center\">\n" +
            "  <h3><b> Для отправки формы нужно заполнить все поля и выбрать все ответы!</b></h3>\n" +
            "  \n" +
            "  <a href=\"../index.html\">Назад к форме</a>\n" +
            "\n" +
            "\t  </div>\n" +
            "  </body>\n" +
            "</html>";

    static final String RESP2 = "<!DOCTYPE html>" +
            "<html>\n" +
            "  <head>\n" +
            "<meta content=\"text/html\"; charset=\"utf-8\">" +
            "    <title>form.com.ua</title>\n" +
            "  </head>\n" +
            "  <body bgcolor=\"#CCCCFF\">\n" +
            "  <div align=\"center\">\n" +
            "  <h1><b> СТАТИСТИКА:</b></h1>\n" +
            "  <p><b>Всего опрошено людей: %d</b></p>\n" +
            "  <i><b>(Средний возраст опрошенных: %d лет.)</b></i>  <br/>\n" +
            "  <p><b>Мужчин: %d</b></p>\n" +
            "  <p><b>Женщин: %d</b></p>\n" +
            "  <br/>\n" +
            "  <p><b>Опыт в программировании:</b></p>\n" +
            "  <table border = \"3\">\n" +
            "\t\t\t\t<tr><td>Нет опыта</td><td>%d</td></tr>\n" +
            "\t\t\t\t<tr><td>До 1 года</td><td>%d</td></tr>\n" +
            "\t\t\t\t<tr><td>1-3 года</td><td>%d</td></tr>\n" +
            "\t\t\t\t<tr><td>3-5 лет</td><td>%d</td></tr>\n" +
            "\t\t\t\t<tr><td>Более 5 лет</td><td>%d</td></tr>\n" +
            "\t\t   </table>\n" +
            "\t<br/>\t\n" +
            "    <p><b>Предпочтения в языках программирования:</b></p>\n" +
            "     <table border = \"3\">\n" +
            "\t\t\t\t<tr><td>Java</td><td>%d</td></tr>\n" +
            "\t\t\t\t<tr><td>C++</td><td>%d</td></tr>\n" +
            "\t\t\t\t<tr><td>C</td><td>%d</td></tr>\n" +
            "\t\t\t\t<tr><td>PHP</td><td>%d</td></tr>\n" +
            "\t\t\t\t<tr><td>JavaScript</td><td>%d</td></tr>\n" +
            "\t\t\t\t<tr><td>Другой язык</td><td>%d</td></tr>\n" +
            "\t\t   </table>\n" +
            "  \n" +
            "\t<br/>\n" +
          //  "  <a href=\"http://localhost:8080/\">Заполнить еще.</a>\n" +
            "  <a href=\"../index.html\">Заполнить еще.</a>\n" +
            "\n" +
            "\t  </div>\n" +
            "  </body>\n" +
            "</html>";

    static List<String> names = new ArrayList<>();
    static List<String> lnames = new ArrayList<>();
    static List<Integer> ages = new ArrayList<>();

    static int avgage;
    static int man;
    static int woman;

    static int v0;
    static int v1;
    static int v3;
    static int v5;
    static int v5p;

    static int j;
    static int cpp;
    static int c;
    static int php;
    static int js;
    static int oth;


    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try {
            if (req.getParameter("name").equals(null) || req.getParameter("lastname").equals(null) ||
                    req.getParameter("age").equals(null) || req.getParameter("question1").equals(null) ||
                    req.getParameter("question2").equals(null) || req.getParameter("question3").equals(null) ) {
                resp.getWriter().println(RESP1);
            } else {
                String name = req.getParameter("name");
                String lastname = req.getParameter("lastname");
                int age = Integer.parseInt(req.getParameter("age"));

                String question1 = req.getParameter("question1");
                String question2 = req.getParameter("question2");
                String question3 = req.getParameter("question3");

                names.add(name);
                lnames.add(lastname);
                ages.add(age);

                if (question1.equals("man")) {
                    man++;
                } else if (question1.equals("woman")) {
                    woman++;
                }


                if (question2.equals("v0")) {
                    v0++;
                } else if (question2.equals("v1")) {
                    v1++;
                } else if (question2.equals("v3")) {
                    v3++;
                } else if (question2.equals("v5")) {
                    v5++;
                } else if (question2.equals("v5p")) {
                    v5p++;
                }

                if (question3.equals("j")) {
                    j++;
                } else if (question3.equals("cpp")) {
                    cpp++;
                } else if (question3.equals("c")) {
                    c++;
                } else if (question3.equals("php")) {
                    php++;
                } else if (question3.equals("js")) {
                    js++;
                } else if (question3.equals("oth")) {
                    oth++;
                }

                avgage = 0;
                for (int a : ages) {
                    avgage += a;
                }

                avgage = avgage / ages.size();

                resp.getWriter().println(String.format(RESP2, ages.size(), avgage, man, woman, v0, v1, v3, v5, v5p, j, cpp, c, php, js, oth));

            }
        } catch (NullPointerException | NumberFormatException e) {
            resp.getWriter().println(RESP1);
        }
    }
}