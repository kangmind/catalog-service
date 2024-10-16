CREATE TABLE book
(
    id                 BIGSERIAL PRIMARY KEY NOT NULL, --　表中的主键。数据库会将其生成为数字的序列（bigserial类型）
    author             varchar(255)          NOT NULL,
    isbn               varchar(255) UNIQUE   NOT NULL, --　UNIQUE约束确保同一个ISBN只能分配给一本书
    price              float8                NOT NULL,
    title              varchar(255)          NOT NULL, --　NOT NULL约束确保相关的列必须分配一个值
    created_date       timestamp             NOT NULL, --　实体创建的时间，以时间戳的形式存储
    last_modified_date timestamp             NOT NULL, --　实体最后更新的时间，以时间戳的形式存储
    version            integer               NOT NULL  --　实体的版本号，存储为整数
);