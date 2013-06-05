package cn.go.service;

import java.util.List;
import java.util.Map;

import cn.go.util.DbOptEnum;
import cn.go.util.Pager;

/**
 * 服务层抽象接口: 定义通用的方法
 * 
 * @author qingtian
 * 
 *         2013-2-27 上午10:16:33
 */
public interface BaseService<T> {

	/**
	 * 添加对象
	 * 
	 * @param t
	 *            待添加的对象
	 * @return 生成的主键值（id）
	 */
	int add(T t);

	/**
	 * 更新对象
	 * 
	 * @param t
	 *            待更新的对象
	 * @return 更新影响的行数
	 */
	int update(T t);

	/**
	 * 删除对象
	 * 
	 * @param t
	 *            待删除的对象（通过id完成删除）
	 * @return 删除影响的行数
	 */
	int delete(T t);

	/**
	 * 单属性值获取对象
	 * 
	 * @param property
	 *            属性名称
	 * @param value
	 *            属性值
	 * @param order
	 *            排序规则
	 * @return 对象或null
	 */
	T get(String property, Object value, Map<String, String> order);

	/**
	 * 批量操作对象（添加，更新，删除）
	 * 
	 * @param data
	 *            待批量操作的对象集
	 * @param doe
	 *            批量操作的方式（添加或更新或删除）
	 * @return 实际操作完成的对象集
	 */
	List<T> batchHandle(List<T> data, DbOptEnum doe);

	/**
	 * 使用自定义SQL来统计总记录数
	 * 
	 * @param sql
	 *            自定义SQL
	 * @param args
	 *            自定义SQL需要设置的参数
	 * @return 总记录数
	 */
	int totalCount(String sql, Object... args);

	/**
	 * 使用自定义SQL来查询数据（支持分页）
	 * 
	 * @param sql
	 *            自定义SQL
	 * @param pager
	 *            分页对象
	 * @param args
	 *            自定义SQL需要设置的参数
	 * @return 查询结果数据集
	 */
	List<T> list(String sql, Pager pager, Object... args);

	/**
	 * 使用自定义SQL来获取以MAP映射一行的数据集（支持分页）
	 * 
	 * @param sql
	 *            自定义SQL
	 * @param pager
	 *            分页对象
	 * @param args
	 *            自定义SQL需要设置的参数
	 * @return 查询结果数据集（MAP，键为列名）
	 */
	List<Map<String, Object>> listForMap(String sql, Pager pager,
			Object... args);
}
