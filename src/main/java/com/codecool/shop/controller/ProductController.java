package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/",
        "/smartphone", "/tablet", "/smartwatch", "/laptop", "/desktop",
        "/apple", "/amazon", "/lenovo", "/sony", "/dell",})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore);
        SupplierDao supplierStore = SupplierDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String servletPath = req.getServletPath();

        switch (servletPath) {
            case "/smartphone":
            case "/tablet":
            case "/smartwatch":
            case "/laptop":
            case "/desktop":
                engineCategoryProductsHTML(resp, productService, engine, context, servletPath);
                break;
            case "/apple":
            case "/amazon":
            case "/lenovo":
            case "/sony":
            case "/dell":
                engineSupplierProductsHTML(resp, productDataStore, supplierStore, engine, context, servletPath);
                break;
            default:
                engineIndexHTML(resp, productDataStore, productCategoryDataStore, supplierStore, engine, context);
        }

        /*
          Alternative setting of the template context
          Map<String, Object> params = new HashMap<>();
                   params.put("category", productCategoryDataStore.find(1));
                   params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
                   context.setVariables(params);
         */
    }

    private void engineIndexHTML(HttpServletResponse resp, ProductDao productDataStore, ProductCategoryDao productCategoryDataStore, SupplierDao supplierStore, TemplateEngine engine, WebContext context) throws IOException {
        context.setVariable("allProducts", productDataStore.getAll());
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierStore.getAll());
        engine.process("product/index.html", context, resp.getWriter());
    }

    private void engineCategoryProductsHTML(HttpServletResponse resp, ProductService productService, TemplateEngine engine, WebContext context, String servletPath) throws IOException {
        int categoryId = getCategorySupplierId(servletPath);
        context.setVariable("category", productService.getProductCategory(categoryId));
        context.setVariable("products", productService.getProductsForCategory(categoryId));
        engine.process("product/filter_products.html", context, resp.getWriter());
    }

    private void engineSupplierProductsHTML(HttpServletResponse resp, ProductDao productDataStore, SupplierDao supplierStore, TemplateEngine engine, WebContext context, String servletPath) throws IOException {
        int supplierId = getCategorySupplierId(servletPath);
        Supplier supplier = supplierStore.find(supplierId);
        context.setVariable("supplier", supplier);
        context.setVariable("products", productDataStore.getBy(supplier));
        engine.process("product/filter_products.html", context, resp.getWriter());
    }

    private int getCategorySupplierId(String servletPath) {
        int categoryId;
        switch (servletPath) {
            case "/tablet":
            case "/amazon":
                categoryId = 2;
                break;
            case "/smartwatch":
            case "/lenovo":
                categoryId = 3;
                break;
            case "/laptop":
            case "/sony":
                categoryId = 4;
                break;
            case "/desktop":
            case "/dell":
                categoryId = 5;
                break;
            default:
                categoryId = 1;
        }
        return categoryId;
    }
}
