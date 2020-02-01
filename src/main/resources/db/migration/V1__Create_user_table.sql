create table USER
(
    ID         INT auto_increment,
    NAME       VARCHAR(50),
    ACCOUNTID  VARCHAR(50),
    TOKEN      VARCHAR(50),
    GMTCREATE  BIGINT,
    GMTMODFIED BIGINT,
    constraint USER_PK
        primary key (ID)
);