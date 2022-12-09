package com.reddit.redditclone.repository;

import com.reddit.redditclone.model.SubReddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubRedditRepository extends JpaRepository<SubReddit, Long> {
}
