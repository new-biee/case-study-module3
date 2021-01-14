package helper;

public class Query {
    public static final String SELECT_ADMIN_FORM_USER = "select username, password from user where role_id = 2";
    public static final String INSERT_CATEGORY_SQL = "insert into category(category_name, category_desc) values " + "(?,?);";
    public static final String SELECT_ALL_CATEGORIES = "select * from category where status = 1";
    public static final String SELECT_CATEGORY_BY_NAME = "select * from category where category_name like ? and status = 1";
    public static final String SElECT_CATEGORY_BY_ID = "select * from category where id = ? and status = 1";
    public static final String UPDATE_CATEGORY_SQL = "update category set category_name = ?, category_desc = ? where id = ? and status = 1";
    public static final String REMOVE_CATEGORY_SQL = "update category set status = ? where id = ? and status = 1";
    public static final String SELECT_ALL_PRODUCT = "select * from product where status = 1";
    public static final String SELECT_PRODUCT_BY_CODE = "select * from product where product_code = ? and status = 1";
    public static final String INSERT_PRODUCT_SQL = "insert into product(product_code, category_id, product_name, product_desc, price) values " + "(?,?,?,?,?)";
    public static final String UPDATE_PRODUCT_SQL = "update product set product_name = ?, product_desc = ?, price = ?, category_id = ? where product_code = ? and status = 1";
    public static final String SELECT_PRODUCT_BY_NAME = "select * from product where product_name like ? and status = 1";
    public static final String REMOVE_PRODUCT_SQL = "update product set status = ? where product_code = ? and status = 1";
    public static final String SELECT_PRODUCT_BY_CATEGORY = "select * from product where category_id = ? and status = 1";
}