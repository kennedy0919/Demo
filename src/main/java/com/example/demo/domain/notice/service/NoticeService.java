package com.example.demo.domain.notice.service;

import com.example.demo.domain.notice.entity.Notice;
import java.util.List;

public interface NoticeService {
    List<Notice> findAll();
    Notice findById(Long id);
    Notice save(Notice notice);
    void deleteById(Long id);
}
