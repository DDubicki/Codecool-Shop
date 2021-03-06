package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.order.Order;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Currency;
import java.util.HashMap;
import java.util.LinkedList;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore);
        SupplierDao supplierStore = SupplierDaoMem.getInstance();
        Order order = new Order();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String filter_by = req.getParameter("filter_by");

        String servletPath = req.getServletPath();

        if (!servletPath.equals("/")) {
            String direction = servletPath.split("/")[2];
            if (direction.equals("productId")){
                addProductToCart(servletPath);
            }
        }

        makeOrder(order, context, productDataStore, engine, resp);

        if (filter_by != null) {
            engineProductFilter(resp, productDataStore, productService, productCategoryDataStore, supplierStore, engine, context, filter_by);
        } else {
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

    private void addProductToCart(String servletPath) {
        int productId = Integer.parseInt(servletPath.split("/")[3]);
        Order.setData(productId);
    }

    private void engineProductFilter(HttpServletResponse resp, ProductDao productDataStore, ProductService productService, ProductCategoryDao productCategoryDataStore, SupplierDao supplierStore, TemplateEngine engine, WebContext context, String filter_by) throws IOException {
        switch (filter_by) {
            case "smartphone":
            case "tablet":
            case "smartwatch":
            case "laptop":
            case "desktop":
                filterByCategories(resp, productService, productCategoryDataStore, supplierStore, engine, context, filter_by);
                break;
            case "apple":
            case "amazon":
            case "lenovo":
            case "sony":
            case "dell":
                filterBySuppliers(resp, productDataStore, productCategoryDataStore, supplierStore, engine, context, filter_by);
                break;
        }
    }

    private void engineIndexHTML(HttpServletResponse resp, ProductDao productDataStore, ProductCategoryDao productCategoryDataStore, SupplierDao supplierStore, TemplateEngine engine, WebContext context) throws IOException {
        context.setVariable("allProducts", productDataStore.getAll());
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierStore.getAll());
        engine.process("product/index.html", context, resp.getWriter());
    }

    private void filterByCategories(HttpServletResponse resp, ProductService productService, ProductCategoryDao productCategoryDataStore, SupplierDao supplierStore, TemplateEngine engine, WebContext context, String filter_by) throws IOException {
        int categoryId = getCategorySupplierId(filter_by);
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierStore.getAll());
        context.setVariable("category", productService.getProductCategory(categoryId));
        context.setVariable("products", productService.getProductsForCategory(categoryId));
        engine.process("product/filter_products.html", context, resp.getWriter());
    }

    private void filterBySuppliers(HttpServletResponse resp, ProductDao productDataStore, ProductCategoryDao productCategoryDataStore, SupplierDao supplierStore, TemplateEngine engine, WebContext context, String filter_by) throws IOException {
        int supplierId = getCategorySupplierId(filter_by);
        Supplier supplier = supplierStore.find(supplierId);
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierStore.getAll());
        context.setVariable("supplier", supplier);
        context.setVariable("products", productDataStore.getBy(supplier));
        engine.process("product/filter_products.html", context, resp.getWriter());
    }

    private void makeOrder(Order order, WebContext context, ProductDao productDataStore, TemplateEngine engine, HttpServletResponse resp) throws IOException {
        HashMap<Integer, Integer> data = order.getData();
        LinkedList<Product> orderProducts = new LinkedList<>();
        double totalPrice = 0;
        Currency currency = null;
        int orderCount = 0;
        for (int key : data.keySet()) {
            int quantity = data.get(key);
            Product product = productDataStore.find(key);
            product.setOrderQuantity(quantity);
            totalPrice += product.getDefaultPrice().doubleValue() * quantity;
            currency = product.getDefaultCurrency();
            orderProducts.add(product);
            orderCount += quantity;
        }
        context.setVariable("orderCount", orderCount);
        context.setVariable("currency", currency);
        context.setVariable("totalPrice", totalPrice);
        context.setVariable("orderProducts", orderProducts);
    }

    private int getCategorySupplierId(String filter_by) {
        int categoryId;
        switch (filter_by) {
            case "tablet":
            case "amazon":
                categoryId = 2;
                break;
            case "smartwatch":
            case "lenovo":
                categoryId = 3;
                break;
            case "laptop":
            case "sony":
                categoryId = 4;
                break;
            case "desktop":
            case "dell":
                categoryId = 5;
                break;
            default:
                categoryId = 1;
        }
        return categoryId;
    }
}
