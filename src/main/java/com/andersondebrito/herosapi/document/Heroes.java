package com.andersondebrito.herosapi.document;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Data
@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "Heroes_Table")
public class Heroes {

    @Id
    @DynamoDBHashKey(attributeName = "id")
    private String id;
    @DynamoDBAttribute(attributeName = "name")
    private String name;
    @DynamoDBAttribute(attributeName = "universe")
    private String universe;
    @DynamoDBAttribute(attributeName = "movies")
    private int movies;

    public Heroes(String id, String name, String universe, int movies) {
        this.id = id;
        this.name = name;
        this.universe = universe;
        this.movies = movies;
    }
}
