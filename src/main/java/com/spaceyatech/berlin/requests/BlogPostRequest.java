package com.spaceyatech.berlin.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostRequest {
    @NotBlank
    @Size(min = 10, max = 200)
    private String blogPostTitle;
    @Size(min = 20, max = 200)
    private String blogPostDescription;
    @NotBlank
    @Size(min = 500, max = 2500)
    private String blogPostContent;
}