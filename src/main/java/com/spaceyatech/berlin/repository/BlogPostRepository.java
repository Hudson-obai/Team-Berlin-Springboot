package com.spaceyatech.berlin.repository;

import com.spaceyatech.berlin.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogPostRepository extends JpaRepository<BlogPost, UUID> {
}
