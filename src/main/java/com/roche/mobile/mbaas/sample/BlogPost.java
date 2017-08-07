package com.roche.mobile.mbaas.sample;

import software.amazon.awssdk.services.dynamodb.datamodeling.DynamoDbAttribute;
import software.amazon.awssdk.services.dynamodb.datamodeling.DynamoDbHashKey;
import software.amazon.awssdk.services.dynamodb.datamodeling.DynamoDbRangeKey;
import software.amazon.awssdk.services.dynamodb.datamodeling.DynamoDbTable;

import java.io.Serializable;

/**
 * Created by User on 2017-07-16.
 */
@DynamoDbTable(tableName = "TableName")
public class BlogPost implements Serializable {

    @DynamoDbHashKey (attributeName = "id")
    private String id;

    @DynamoDbRangeKey (attributeName = "version id")
    private String versionId;

    @DynamoDbAttribute
    private String author;

    @DynamoDbAttribute
    private String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
}
