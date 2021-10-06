package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/smartphone", "/tablet", "/smartwatch", "/laptop", "/desktop"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        int categoryId = getCategoryId(req);
        context.setVariable("category", productService.getProductCategory(categoryId));
        context.setVariable("products", productService.getProductsForCategory(categoryId));
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/category_products.html", context, resp.getWriter());
    }

    private int getCategoryId(HttpServletRequest req) {
        String servletPath = req.getServletPath();
        int categoryId;
        switch (servletPath) {
            case "/tablet": categoryId = 2;
            break;
            case "/smartwatch": categoryId = 3;
                break;
            case "/laptop": categoryId = 4;
                break;
            case "/desktop": categoryId = 5;
                break;
            default: categoryId = 1;
        }
        return categoryId;
    }

}
