package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.config.DatabaseAuditingConfig;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

//标识这是一个关注Spring Data JDBC组件的测试类
@DataJdbcTest
//导入数据配置，需要启用审计功能
@Import(DatabaseAuditingConfig.class)
//禁用依赖嵌入式测试数据库的默认行为，因为我们使用的是Testcontainers
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
//启用"integration"profile，以便于从application-integration.yml中加载配置
@ActiveProfiles("integration")
@Slf4j
class BookRepositoryTest {

    @Resource
    BookRepository bookRepository;

    @Resource
    JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void findByIsbn() {
        String bookIsbn = "1234561237";
        Book book = Book.of(bookIsbn, "Title", "Author", 12.90, "");
        jdbcAggregateTemplate.insert(book);

        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);
        Assertions.assertTrue(actualBook.isPresent());
        Assertions.assertTrue(actualBook.get().isbn().equals(bookIsbn));
        log.info("[X] actualBook - {}", actualBook);
    }

    @Test
    void existsByIsbn() {
    }

    @Test
    void deleteByIsbn() {
    }
}