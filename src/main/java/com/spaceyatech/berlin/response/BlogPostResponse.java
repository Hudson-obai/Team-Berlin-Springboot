package com.spaceyatech.berlin.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPostResponse {
    private String blogPostTitle;
    private String blogPostContent;
    private String blogPostDescription;
}