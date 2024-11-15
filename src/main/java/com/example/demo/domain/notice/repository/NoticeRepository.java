package com.example.demo.domain.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.notice.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
