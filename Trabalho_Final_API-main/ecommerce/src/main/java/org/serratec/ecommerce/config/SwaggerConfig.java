package org.serratec.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket ecommerceApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.serratec.ecommerce"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());
    }

//    @Bean
//    public Docket clienteApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("Cliente")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("org.serratec.ecommerce.cliente"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(metaInfo());
//    }

//    @Bean
//    public Docket usuarioApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("Usuario")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("org.serratec.ecommerce.usuario"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(metaInfo());
//    }

//    @Bean
//    public Docket pedidoApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("Pedido")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("org.serratec.ecommerce.pedido"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(metaInfo());
//    }
//
//    @Bean
//    public Docket produtoApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("Produto")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("org.serratec.ecommerce.produto"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(metaInfo());
//    }
//
//    @Bean
//    public Docket categoriaApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("Categoria")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("org.serratec.ecommerce.categoria"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(metaInfo());
//    }

    private ApiInfo metaInfo() {

        return new ApiInfo(
                "Projeto API-ECommerce",
                "Trabalho Final de API-Restful - Residência Serratec - 2021.2",
                "1.0",
                "Terms of Service",
                new Contact("Desenvolvido por: Nathan Guimarães, Higor Henrique, Leandro Fita, Adrian Menezes e Karina Leal", "https://github.com/leandrofita/Trabalho_Final_API",
                        ""),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<>()
        );
    }
}