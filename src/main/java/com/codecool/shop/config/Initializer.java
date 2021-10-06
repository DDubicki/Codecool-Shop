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
        ProductCategory desktopComputer = new ProductCategory("Desktop", "Computer", "A personal computer (PC) designed for regular use at a single location on or near a desk due to its size and power requirements.");
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

        // Smartwatch
        productDataStore.add(new Product("Sony Smartwatch 3 SWR50", new BigDecimal("50.3"), "USD", "Solution for dynamically living users who want to have quick access to the functionality of their mobile phone.", smartwatch, sony));
        productDataStore.add(new Product("Smartband M5", new BigDecimal("16.4"), "USD", "Help with losing unnecessary kilograms, careful monitoring of the heart and pulse.", smartwatch, amazon));
        productDataStore.add(new Product("Apple Watch 5", new BigDecimal("400"), "USD", "A smartwatch that will allow 18 hours of use, you can successfully take to work and constantly analyze your activity while performing your daily duties.", smartwatch, apple));
        productDataStore.add(new Product("HONOR MAGIC WATCH 2 GPS", new BigDecimal("160"), "USD", "Up to 14 days of battery life! 15 different sport modes. 316L stainless steel housing.", smartwatch, lenovo));
        productDataStore.add(new Product("Huawei Honor band 6", new BigDecimal("57.8"), "USD", "Equipped with a heart rate monitor, blood oxygen sensor. Up to 14 days of normal use.", smartwatch, lenovo));

        // Laptop
        productDataStore.add(new Product("Apple MacBook Air M1/8GB/256/Mac OS",  new BigDecimal("905.9"), "USD","Our thinnest and lightest notebook has been completely transformed by the Apple M1 chip. It even has a 3.5x faster CPU.", laptop, apple));
        productDataStore.add(new Product("DELL E6520",  new BigDecimal("350.4"), "USD","Elegant appearance. Less worry and attractive details. Performance and durability wherever you go.", laptop, dell));
        productDataStore.add(new Product("HP Pavilion 15 Ryzen 7",  new BigDecimal("842.9"), "USD","The Pavilion 15 laptop gives you more performance in a smaller device, so you can get more done wherever you go.", laptop, amazon));
        productDataStore.add(new Product("Lenovo Chromebook S330",  new BigDecimal("125.6"), "USD","You will find space for programs, movies, photos, games and music files in mp3 format on a 64GB disk", laptop, lenovo));
        productDataStore.add(new Product("HP Pavilion i5-1135G7",  new BigDecimal("704"), "USD","RAM size: 8 GB. Disk capacity: 512 GB. Processor cores: 4.", laptop, amazon));

        // Desktop computer
        productDataStore.add(new Product("I7 16GB RTX 2060 SSD COMPUTER", new BigDecimal("1193"), "USD", "GAMING COMPUTER with Processor: Intel Core i7 2600 - 4x3.40GHz TURBO up to 4x3.80GHz.", desktopComputer, amazon));
        productDataStore.add(new Product("DELL i5 8GB COMPUTER", new BigDecimal("155.8"), "USD", "Series / Model: Dell OptiPlex. Processor: Intel Core i5 - 2400, 6M Cache, 4 x 3.40 GHz (turbo), 4 x 3.10 GHz.", desktopComputer, dell));
        productDataStore.add(new Product("Gaming computer - Intel i7 GTX 1050Ti", new BigDecimal("749.8"), "USD", "We present the results in games! We give the possibility to modify and extend the set. We guarantee the lowest price.", desktopComputer, lenovo));
        productDataStore.add(new Product("DELL 7060 TINY I7 8GEN COMPUTER", new BigDecimal("578.6"), "USD", "Dell OptiPlex 7060 desktops are the most secure, manageable and reliable systems designed for uncompromising performance.", desktopComputer, dell));
        productDataStore.add(new Product("Gaming computer - Intel i5 GTX 1050Ti", new BigDecimal("674.3"), "USD", "I5 2400 processor, 4 cores clocked at 3.4Ghz TURBO! NVIDIA GeForce GTX 1050Ti 4GB graphics card.", desktopComputer, lenovo));
    }
}
