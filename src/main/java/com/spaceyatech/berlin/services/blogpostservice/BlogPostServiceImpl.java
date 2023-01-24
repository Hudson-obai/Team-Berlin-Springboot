package com.spaceyatech.berlin.services.blogpostservice;

import com.spaceyatech.berlin.error.BlogPostNotFoundException;
import com.spaceyatech.berlin.models.BlogPost;
import com.spaceyatech.berlin.repository.BlogPostRepository;
import com.spaceyatech.berlin.requests.BlogPostRequest;
import com.spaceyatech.berlin.response.BlogPostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogPostServiceImpl implements BlogPostService {
    @Autowired
    BlogPostRepository blogPostRepository;

    @Override
    public BlogPostResponse saveBlog(BlogPostRequest blogPostRequest) {
        BlogPost blogPost = BlogPost.builder()
                .post_title(blogPostRequest.getBlogPostTitle())
                .content(blogPostRequest.getBlogPostContent())
                .blog_description(blogPostRequest.getBlogPostDescription())
                .build();

        BlogPost blogPostDb = blogPostRepository.save(blogPost);

        return BlogPostResponse.builder()
                .blogPostTitle(blogPostDb.getPost_title())
                .blogPostContent(blogPostDb.getContent())
                .blogPostDescription(blogPostDb.getBlog_description())
                .build();
    }

    @Override
    public BlogPostResponse fetchOneBlog(UUID blogPostId) throws BlogPostNotFoundException {
        Optional<BlogPost> blogPost = blogPostRepository.findById(blogPostId);
        if (!blogPost.isPresent()) {
            throw new BlogPostNotFoundException("Blog post not found");
        }
        return BlogPostResponse.builder()
                .blogPostTitle(blogPost.get().getPost_title())
                .blogPostContent(blogPost.get().getContent())
                .blogPostDescription(blogPost.get().getBlog_description())
                .build();
    }

    @Override
    public List<BlogPostResponse> fetchAllBlogs() {
        List<BlogPost> blogPosts = blogPostRepository.findAll();
        List<BlogPostResponse> blogPostResponses = new ArrayList<>();
        for (BlogPost blogPost: blogPosts) {
            BlogPostResponse blogPostResponse = BlogPostResponse.builder()
                    .blogPostTitle(blogPost.getPost_title())
                    .blogPostDescription(blogPost.getBlog_description())
                    .blogPostContent(blogPost.getContent())
                    .build();

            blogPostResponses.add(blogPostResponse);
        }
        return blogPostResponses;
    }

    @Override
    public BlogPostResponse updateBlogPost(UUID blogPostId, BlogPostRequest blogPostRequest) {
        BlogPost blogPostDB = blogPostRepository.findById(blogPostId).get();

        if (Objects.nonNull(blogPostRequest.getBlogPostTitle()) &&
                !"".equalsIgnoreCase(blogPostRequest.getBlogPostTitle())) {
            blogPostDB.setPost_title(blogPostRequest.getBlogPostTitle());
        }

        if (Objects.nonNull(blogPostRequest.getBlogPostContent()) &&
                !"".equalsIgnoreCase(blogPostRequest.getBlogPostContent())) {
            blogPostDB.setPost_title(blogPostRequest.getBlogPostTitle());
        }

        if (Objects.nonNull(blogPostRequest.getBlogPostDescription()) &&
                !"".equalsIgnoreCase(blogPostRequest.getBlogPostDescription())) {
            blogPostDB.setPost_title(blogPostRequest.getBlogPostDescription());
        }

        return BlogPostResponse.builder()
                .blogPostTitle(blogPostDB.getPost_title())
                .blogPostContent(blogPostDB.getContent())
                .blogPostDescription(blogPostDB.getBlog_description())
                .build();
    }

    @Override
    public String deleteBlogPost(UUID blogPostId) throws BlogPostNotFoundException {
        Optional<BlogPost> blogPost = blogPostRepository.findById(blogPostId);

        if (!blogPost.isPresent()) {
            throw new BlogPostNotFoundException("Blog post not found");
        }

        blogPostRepository.deleteById(blogPostId);
        return "Deleted successfully";
    }
}
