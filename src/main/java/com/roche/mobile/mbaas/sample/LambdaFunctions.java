package com.roche.mobile.mbaas.sample;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDBClient;
import software.amazon.awssdk.services.dynamodb.datamodeling.DynamoDbMapper;
import software.amazon.awssdk.services.dynamodb.datamodeling.DynamoDbScanExpression;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by User on 2017-07-16.
 */
public class LambdaFunctions {

    private static final Logger log = Logger.getLogger(LambdaFunctions.class.getName());

    public List<BlogPost> getAllBlogPosts(Map<String,String> values) {
        DynamoDBClient client = DynamoDBClient.builder()
                                    .region(Region.EU_WEST_1)
                                    .build();
        DynamoDbMapper mapper = new DynamoDbMapper(client);

        log.info("handling request");
        return mapper.scan(BlogPost.class, new DynamoDbScanExpression());
    }

    public BlogPost getBlogPostById(Map<String,String> values) {
        DynamoDBClient client = DynamoDBClient.builder()
                .region(Region.EU_WEST_1)
                .build();
        String id = values.get("postId");
        String versionId = values.get("versionId");
        DynamoDbMapper mapper = new DynamoDbMapper(client);
        log.info("handling request");
        StringBuilder builder = new StringBuilder();
        for (String key : values.keySet()) {
            log.info("(K, V) == (" + key +", " + values.get(key) + ")");
            builder.append(values.get(key) + " ");
        }
        log.info(builder.toString());
        return mapper.load(BlogPost.class, id, versionId);
    }

    public BlogPost createBlogPost(BlogPost post) {
        DynamoDBClient client = DynamoDBClient.builder()
                .region(Region.EU_WEST_1)
                .build();
        post.setVersionId("1");
        post.setId(UUID.randomUUID().toString());
        DynamoDbMapper mapper = new DynamoDbMapper(client);
        log.info("handling request");
        mapper.save(post);
        return post;
    }

//    public void uploadToWjadro(String fileName) {
//        S3Client client = S3Client.builder().build();
//        client.putObject(PutObjectRequest.builder()
//                            .bucket("wjadro")
//                            .key("klucz")
////                            .
//                            .build()
//        );
//    }

    public void deleteBlogPost(BlogPost post) {
        DynamoDBClient client = DynamoDBClient.builder()
                .region(Region.EU_WEST_1)
                .build();
        DynamoDbMapper mapper = new DynamoDbMapper(client);
        log.info("handling request");
        mapper.delete(post);
    }
}
