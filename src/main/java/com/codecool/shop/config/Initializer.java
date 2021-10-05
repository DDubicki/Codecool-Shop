package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier apple = new Supplier("Apple", "Consumer electronics and computer software");
        supplierDataStore.add(apple);
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier sony = new Supplier("SONY", "Electronic products");
        supplierDataStore.add(sony);
        Supplier dell = new Supplier("DELL", "Computers");
        supplierDataStore.add(dell);

        //setting up a new product category
        ProductCategory smartphone = new ProductCategory("Smartphone", "Phones and accessories", "A portable device that combines mobile telephone and computing functions into one unit.");
        productCategoryDataStore.add(smartphone);
        ProductCategory tablet = new ProductCategory("Tablet", "Phones and accessories", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory smartwatch = new ProductCategory("Smartwatch", "Phones and accessories", "A wearable computer in the form of a watch.");
        productCategoryDataStore.add(smartwatch);
        ProductCategory laptop = new ProductCategory("Laptop", "Computer", "A small, portable personal computer (PC) with a screen and alphanumeric keyboard.");
        productCategoryDataStore.add(laptop);
        ProductCategory desktopComputer = new ProductCategory("Desktop computer", "Computer", "A personal computer (PC) designed for regular use at a single location on or near a desk due to its size and power requirements.");
        productCategoryDataStore.add(desktopComputer);


        //setting up products and printing it

        // Smartphone
        productDataStore.add(new Product("IPHONE XS MAX", new BigDecimal("606.7"), "USD", "Large 6.5-inch iPhone XS Max display with an OLED panel, A12 Bionic processor, 8-core Neural Engine system.", smartphone, apple));
        productDataStore.add(new Product("Black SONY Xperia 10 III", new BigDecimal("435.2"), "USD","OLED screen with a diagonal of 6 inches and FHD + resolution.", smartphone, sony));
        productDataStore.add(new Product("LENOVO K12 PRO", new BigDecimal("196"), "USD", "6.8 inch screen, 720x1640. 4GB RAM, 64GB ROM. Qualcomm Snapdragon 662 processor, 4x2.0GHz + 4x1.8GHz. LTE B20 800MHz.", smartphone, lenovo));
        productDataStore.add(new Product("iPhone 12 Pro", new BigDecimal("1182.7"), "USD", "System version: iOS 14. RAM: 6 GB. Built-in memory [GB]: 128. Dual SIM. 5G. Processor: Apple A14 Bionic, 6-core.", smartphone, apple));
        productDataStore.add(new Product("iPhone 13 Pro", new BigDecimal("1812"), "USD", "Built-in memory: 256 GB. RAM memory: 6 GB. Operating system: iOS. Battery capacity: 3095 mAh.", smartphone, apple));

        // Tablet
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Apple iPad 9-gen 10,2 64GB Wi-Fi Silver", new BigDecimal("440"), "USD", "Built-in memory of 64 GB. Warranty 12 months manufacturer's warranty. Screen diagonal 10.2", tablet, apple));
        productDataStore.add(new Product("Tablet LENOVO Tab M10 10.1 WiFi 32GB Black", new BigDecimal("138.2"), "USD", "Display: 10.1 \", 1280 x 800px, IPS. Built-in memory [GB]: 32. RAM memory size [GB]: 2.", tablet, lenovo));


//        productDataStore.add(new Product());
//        productDataStore.add(new Product());
//        productDataStore.add(new Product());
//        productDataStore.add(new Product());
//        productDataStore.add(new Product());
//
//        productDataStore.add(new Product());
//        productDataStore.add(new Product());
//        productDataStore.add(new Product());
//        productDataStore.add(new Product());
//        productDataStore.add(new Product());
//
//        productDataStore.add(new Product());
//        productDataStore.add(new Product());
//        productDataStore.add(new Product());
//        productDataStore.add(new Product());
//        productDataStore.add(new Product());
//
//
    }
}
