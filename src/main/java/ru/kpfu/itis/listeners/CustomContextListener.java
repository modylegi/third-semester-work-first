package ru.kpfu.itis.listeners;


import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.kpfu.itis.dao.base.OrderRepository;
import ru.kpfu.itis.dao.base.PizzaRepository;
import ru.kpfu.itis.dao.base.UserRepository;

import ru.kpfu.itis.dao.impl.OrderRepositoryImpl;
import ru.kpfu.itis.dao.impl.PizzaRepositoryImpl;
import ru.kpfu.itis.dao.impl.UserRepositoryImpl;
import ru.kpfu.itis.services.base.*;
import ru.kpfu.itis.services.impl.*;
import ru.kpfu.itis.services.validation.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebListener
public class CustomContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("No properties");
        }
        String DB_USERNAME = (String) properties.get("db.username");
        String DB_PASSWORD = (String) properties.get("db.password");
        String DB_URL = (String) properties.get("db.url");
        String DB_DRIVER = (String) properties.get("db.driverClassName");
//        String IMAGES_STORAGE_PATH = (String) properties.get("storage.images");

        ServletContext servletContext = sce.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);
//        System.out.println(dataSource.i);

        UserRepository userRepository = new UserRepositoryImpl(dataSource);
        PizzaRepository pizzaRepository = new PizzaRepositoryImpl(dataSource);
        OrderRepository orderRepository = new OrderRepositoryImpl(dataSource);




        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        SignInService signInService = new SignInServiceImpl(userRepository, passwordEncoder);
        Validator validator = new ValidatorImpl(userRepository);
        SignUpService signUpService = new SignUpServiceImpl(userRepository, passwordEncoder, validator);
        PizzaService pizzaService = new PizzaServiceImpl(pizzaRepository);
        UserService userService = new UserServiceImpl();
        CartProducts cartProducts = new CartProductsImpl(pizzaRepository);

        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("pizzaService", pizzaService);
        servletContext.setAttribute("passwordEncoder", passwordEncoder);
        servletContext.setAttribute("cartProducts", cartProducts);
        servletContext.setAttribute("pizzaRepository",pizzaRepository);
        servletContext.setAttribute("orderRepository",orderRepository);
        servletContext.setAttribute("userService",userService);
        servletContext.setAttribute("userRepository",userRepository);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
