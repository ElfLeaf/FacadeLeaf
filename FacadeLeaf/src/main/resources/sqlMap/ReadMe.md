规约1：
查询数据库，必须写出数据库名，例如查询"mysql"数据库的db表，写为mysql.db
规约2:
sqlMap的命名空间，必须是DAO层入口泛型类的bean类名,例如extends AMyBatisBaseDAO<User>那么命名空间就必须是User