package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {

    ProductHolder productHolder;

    @Override
    public void init() throws ServletException {
        this.productHolder = new ProductHolder();
        this.productHolder.insert(new Product("Glue", 10.0));
        this.productHolder.insert(new Product("Lemon Tree", 25.0));
        this.productHolder.insert(new Product("Table", 22.0));
        this.productHolder.insert(new Product("Apple", 5.0));
        this.productHolder.insert(new Product("Orange", 6.0));
        this.productHolder.insert(new Product("Smartphone", 99.99));
        this.productHolder.insert(new Product("Notebook", 199.00));
        this.productHolder.insert(new Product("Chair", 15.00));
        this.productHolder.insert(new Product("Cucumber", 3.0));
        this.productHolder.insert(new Product("Onion", 2.0));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() != null) {
            String path = req.getPathInfo().replace("/","");
            if(this.checkValid(path)){
                Long id = Long.valueOf(path);
                PrintWriter wr = resp.getWriter();
                wr.println("<table>");
                wr.println("<tr>");
                wr.println("<th>Id</th>");
                wr.println("<th>Product Title</th>");
                wr.println("<th>Cost</th>");
                wr.println("</tr>");
                wr.println(productHolder.findById(id).toString());
                resp.getWriter().println("</table>");
                return;
            }
        }
        PrintWriter wr = resp.getWriter();
        wr.println("<table>");
        wr.println("<tr>");
        wr.println("<th>Id</th>");
        wr.println("<th>Product Title</th>");
        wr.println("<th>Cost</th>");
        wr.println("</tr>");

        for (Product product : productHolder.findAll()) {
            wr.println(product.toString());
        }
        resp.getWriter().println("</table>");
    }

    public boolean checkValid(String str) {
        try {
            Long.valueOf(str);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }
}

