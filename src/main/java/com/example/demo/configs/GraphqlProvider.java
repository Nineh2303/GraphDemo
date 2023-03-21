package com.example.demo.configs;

import com.example.demo.fetchers.HomeFetcher;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;


import java.io.File;
import java.io.IOException;
@Component
public class GraphqlProvider {
    @Value("classpath:graphql/schema.graphqls")
    private Resource schemaResource ;

    GraphQL graphQL;

    @Autowired
    HomeFetcher homeFetcher;
    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    private void initGraphQL() throws IOException {
       try {
           File schemaFile = schemaResource.getFile() ;
           TypeDefinitionRegistry registry =  new SchemaParser().parse(schemaFile);
           RuntimeWiring wiring = runtimeWiring();
           GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry,wiring);
           graphQL = GraphQL.newGraphQL(schema).build() ;
//           ExecutionResult executionResult = graphQL.execute("{Hello}");
//
//           System.out.println(executionResult.getData().toString());
       }
       catch (Exception e) {
           System.out.println(e);
       }

    }
    private RuntimeWiring runtimeWiring(){
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query").dataFetcher("Hello",homeFetcher.getStringFetcher()))
                .type(newTypeWiring("Query").dataFetcher("getAllProducts",homeFetcher.getAllProducts()))
                .build();
    }
}
